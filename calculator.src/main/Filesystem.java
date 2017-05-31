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
	
	public static void load(String filename){
		Path filepath = FileSystems.getDefault().getPath(filename);		
		try (BufferedReader reader = Files.newBufferedReader(filepath)) {
			PfCharacter character = readCharacter(reader);
			character.setWeapons(readWeapons(reader));
			character.setFullRounds(readFullRounds(reader));
		} catch (IOException x) {
		    System.err.format("IOException: %s%n", x);
		}
	}

	private static ArrayList<FullRound> readFullRounds(BufferedReader reader) {

		String line;
		try {
			FullRound fullround = new FullRound(reader.readLine());
			while((line=reader.readLine())!=null){
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
		try {
			while((line=reader.readLine())!="|"){
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	private static ArrayList<Weapon> readWeapons(BufferedReader reader) {
		ArrayList<Weapon> weaponlist = new ArrayList<>();
		Weapon weapon = new Weapon();
		int[] dmgDice;
		try {
			String line;
			while((line = reader.readLine()) != "<"){
				weapon.setName(line);
				dmgDice=new int[Integer.parseInt(reader.readLine())];
				for(int i=0; i<dmgDice.length; i++){
					dmgDice[i]=Integer.parseInt(reader.readLine());
				}
				weapon.setCritMultiplier(Integer.parseInt(reader.readLine()));
				weapon.setCritRange(Integer.parseInt(reader.readLine()));
				weapon.setType(Integer.parseInt(reader.readLine()));
				String special;
				while((special = reader.readLine()) != ("#")){
					weapon.addSpecial(special);
				}
				weaponlist.add(weapon);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return weaponlist;
	}

	private static PfCharacter readCharacter(BufferedReader reader) {
		PfCharacter character = new PfCharacter();
		try {
			character.setName(reader.readLine());
			character.setBab(Integer.parseInt(reader.readLine()));
			character.setDex(Integer.parseInt(reader.readLine()));
			character.setStr(Integer.parseInt(reader.readLine()));
			String feat;
			while((feat = reader.readLine()) != ("#")){
				character.addFeat(feat);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return character;
	}
}
