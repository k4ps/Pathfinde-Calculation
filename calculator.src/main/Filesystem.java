package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

import attacks.Attack;
import attacks.FullRound;
import characters.PfCharacter;
import weapons.Weapon;

public class Filesystem {

	public static PfCharacter load(String filename) {
		Path filepath = FileSystems.getDefault().getPath(filename);
		PfCharacter character = new PfCharacter();
		String line;
		try (BufferedReader reader = Files.newBufferedReader(filepath);) {
			character = readCharacter(reader.readLine());
			while (!((line = reader.readLine()).equals("/"))) {
				character.addWeapon(readWeapon(line));
			}
			while ((line = reader.readLine()) != null) {
				character.addFullRound(readFullround(line, reader));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return character;
	}

	private static PfCharacter readCharacter(String readLine) {
		PfCharacter character = new PfCharacter();
		String[] parts = readLine.split(";");
		character.setName(parts[0]);
		character.setBab(Integer.parseInt(parts[1]));
		character.setDex(Integer.parseInt(parts[2]));
		character.setStr(Integer.parseInt(parts[3]));
		for (int i = 4; !(parts[i].equals("#")); i++) {
			character.addFeat(parts[i]);
		}
		return character;
	}

	public static Weapon readWeapon(String line) {
		Weapon weapon = new Weapon();
		String[] parts = line.split(";");
		ArrayList<int[]> newPrecision = weapon.getPrecisionDmgDice();
		int i = 9;
		weapon.setName(parts[0]);
		weapon.setDmgDice(new int[] { Integer.parseInt(parts[1]), Integer.parseInt(parts[2]) });
		weapon.setCritMultiplier(Integer.parseInt(parts[3]));
		weapon.setCritRange(Integer.parseInt(parts[4]));
		weapon.setHitBonus(Integer.parseInt(parts[5]));
		weapon.setDmgBonus(Integer.parseInt(parts[6]));
		weapon.setDmgMod(Double.parseDouble(parts[7]));
		weapon.setType(Integer.parseInt(parts[8]));
		while (!(parts[i].equals("#"))) {
			weapon.addSpecial(parts[i]);
			i++;
		}
		while (!(parts[i].equals("#"))) {
			newPrecision.add(new int[] { Integer.parseInt(parts[i]), Integer.parseInt(parts[i + 1]) });
			i = i + 2;
			;
		}
		weapon.setPrecisionDmgDice(newPrecision);
		return weapon;
	}

	private static FullRound readFullround(String line, BufferedReader reader) {
		FullRound fullround = new FullRound(line);
		try {
			while (!((line = reader.readLine()).equals("/"))) {
				Attack attack = readAttack(line);
				attack.setWeapon(readWeapon(reader.readLine()));
				attack.setPrecisionDmgDice(readPrecisionDmg(reader.readLine()));
				fullround.addAttack(attack);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return fullround;
	}

	private static Attack readAttack(String readLine) {
		Attack attack = new Attack();
		String[] parts = readLine.split(";");
		int[] attackMods = new int[Integer.parseInt(parts[0])];
		int[] dmgMods = new int[attackMods.length];
		for (int i = 0; i < attackMods.length; i++) {
			attackMods[i] = Integer.parseInt(parts[i + 1]);
			dmgMods[i] = Integer.parseInt(parts[1 + attackMods.length]);
		}

		attack.setAttackModi(attackMods);
		attack.setDamageModi(dmgMods);
		attack.setFullRound(parts[2 + attackMods.length]);
		return attack;
	}

	private static ArrayList<int[]> readPrecisionDmg(String readLine) {
		ArrayList<int[]> precisionDmg = new ArrayList<>();
		String[] parts = readLine.split(";");
		for (int i = 0; !(parts[i].equals("#")); i += 2) {
			precisionDmg.add(new int[] { Integer.parseInt(parts[i]), Integer.parseInt(parts[i + 1]) });
		}
		return precisionDmg;
	}

	public static void save(PfCharacter character) {
		Path filepath = FileSystems.getDefault().getPath(character.getName() + ".txt");
		try (BufferedWriter writer = Files.newBufferedWriter(filepath)) {
			writer.write(writeCharacter(character));
			writer.newLine();
			if (character.getWeapons() != null) {
				for (Weapon weapon : character.getWeapons()) {
					writer.write(writeWeapon(weapon));
					writer.newLine();
				}
			}
			writer.write("/");
			writer.newLine();
			if (character.getFullRounds() != null) {
				for (FullRound fullround : character.getFullRounds()) {
					writer.write(fullround.getName());
					writer.newLine();
					for (Attack attack : fullround.getAttacks()) {
						writer.write(writeAttack(attack));
						writer.newLine();
						writer.write(writeWeapon(attack.getWeapon()));
						writer.newLine();
						writer.write(writePrecisionDmg(attack));
						writer.newLine();
					}
					writer.write("/");
					writer.newLine();
				}
			}

		} catch (IOException x) {
			System.err.format("IOException: %s%n", x);
		}
	}

	private static String writePrecisionDmg(Attack attack) {
		String returnString = "#";
		for (int[] precision : attack.getPrecisionDmgDice()) {
			returnString = precision[0] + ";" + precision[1] + ";" + returnString;
		}
		return returnString;
	}

	private static String writeAttack(Attack attack) {
		String returnString = attack.getAttackModi().length + ";";
		for (int i = 0; i < attack.getAttackModi().length; i++) {
			returnString = returnString + attack.getAttackModi()[i] + ";";
		}
		returnString = returnString + attack.getDamageModi()[0] + ";" + String.valueOf(attack.isFullRound());
		return returnString;
	}

	private static String writeWeapon(Weapon weapon) {
		String returnString = weapon.getName() + ";" + weapon.getDmgDice()[0] + ";" + weapon.getDmgDice()[1] + ";"
				+ weapon.getCritMultiplier() + ";" + weapon.getCritRange() + ";" + weapon.getHitBonus() + ";"
				+ weapon.getDmgBonus() + ";" + weapon.getDmgMod() + ";" + weapon.getType() + ";";
		for (String special : weapon.getSpecials()) {
			returnString = returnString + special + ";";
		}
		returnString = returnString + "#;";
		for (int[] precision : weapon.getPrecisionDmgDice()) {
			returnString = returnString + precision[0] + ";" + precision[1];
		}
		return returnString + "#";
	}

	private static String writeCharacter(PfCharacter character) {
		String returnString = character.getName() + ";" + character.getBab() + ";" + character.getDex() + ";"
				+ character.getStr() + ";";
		for (String feat : character.getFeats()) {
			returnString = returnString + feat + ";";
		}
		return returnString + "#";
	}
}
