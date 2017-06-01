package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

import attacks.Attack;
import attacks.FullRound;
import characters.PfCharacter;
import weapons.Weapon;

public class Filesystem {

	public static PfCharacter load(String filename) {
		Path filepath = FileSystems.getDefault().getPath(filename);
		PfCharacter character = new PfCharacter();
		try (BufferedReader reader = Files.newBufferedReader(filepath)) {
			character = readCharacter(reader);
			while (reader.readLine().equals("#"))
				character.addWeapon(readWeapon(reader));
			character.setFullRounds(readFullRounds(reader));
		} catch (IOException x) {
			System.err.format("IOException: %s%n", x);
		}
		return character;
	}

	private static ArrayList<FullRound> readFullRounds(BufferedReader reader) {

		String line;
		try {
			FullRound fullround = new FullRound(reader.readLine());
			while ((line = reader.readLine()).equals("|")) {
				fullround.addAttack(readAttack(reader));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	private static Attack readAttack(BufferedReader reader) {
		String line;
		String precision;
		Attack attack = new Attack();
		try {
			double[] attackModi = new double[Integer.parseInt(reader.readLine())];
			double[] dmgModi = new double[attackModi.length];
			for (int i = 0; i < attackModi.length; i++) {
				attackModi[i] = Integer.parseInt(reader.readLine());
			}
			int dmgMod = Integer.parseInt(reader.readLine());
			for (int i = 0; i < dmgModi.length; i++) {
				dmgModi[i] = dmgMod;
			}
			attack.setAttackModi(attackModi);
			attack.setDamageModi(dmgModi);
			attack.setFullRound(reader.readLine());
			attack.setWeapon(readWeapon(reader));
			if (!((precision = reader.readLine()).equals("+"))) {
				int[] precisionDmg = new int[Integer.parseInt(precision)];
				for (int i = 0; i < precisionDmg.length; i++) {
					precisionDmg[i] = Integer.parseInt(reader.readLine());
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return attack;
	}

	private static Weapon readWeapon(BufferedReader reader) {
		Weapon weapon = new Weapon();
		int[] dmgDice;
		try {
			weapon.setName(reader.readLine());
			dmgDice = new int[Integer.parseInt(reader.readLine())];
			int diceType = Integer.parseInt(reader.readLine());
			for (int i = 0; i < dmgDice.length; i++) {
				dmgDice[i] = diceType;
			}
			weapon.setCritMultiplier(Integer.parseInt(reader.readLine()));
			weapon.setCritRange(Integer.parseInt(reader.readLine()));
			weapon.setType(Integer.parseInt(reader.readLine()));
			String special;
			while (!(special = reader.readLine()).equals("*")) {
				weapon.addSpecial(special);

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return weapon;
	}

	private static PfCharacter readCharacter(BufferedReader reader) {
		PfCharacter character = new PfCharacter();
		try {
			character.setName(reader.readLine());
			character.setBab(Integer.parseInt(reader.readLine()));
			character.setDex(Integer.parseInt(reader.readLine()));
			character.setStr(Integer.parseInt(reader.readLine()));
			String feat;
			while (!(feat = reader.readLine()).equals("*")) {
				character.addFeat(feat);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return character;
	}
}
