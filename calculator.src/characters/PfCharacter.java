/* This Program calculates the DPR (Damage per Round) of a Character from the fantasy role-playing game Pathfinder.
 * Copyright (C) 2017  Konrad Schön
 * This file is subject to the terms and conditions defined in file 'COPYING.txt', which is part of this source code package. 
 */

package characters;

import java.util.ArrayList;

import attacks.Attack;
import attacks.FullRound;
import weapons.Weapon;

public class PfCharacter {
	private String name;
	private int bab;
	private int str;
	private int dex;
	private ArrayList<FullRound> fullRounds = new ArrayList<>();
	private ArrayList<String> feats = new ArrayList<>();
	
	public PfCharacter(String name, int bab, int str, int dex){
		this.name=name;
		this.bab=bab;
		this.str=str;
		this.dex=dex;
	}
	
	public void addFullRound(String name, ArrayList<Weapon> usedWeapons, boolean fullRound){
		FullRound newFullRound = new FullRound(name);
		int hitmod;
		int dmgmod=(str-10)/2;
		if(feats.contains("Weapon Finesse")) hitmod=(dex-10)/2;
		else hitmod=(str-10)/2;
		
		for(Weapon weapon:usedWeapons){
			Attack attack = new Attack(hitmod, dmgmod, bab, weapon, fullRound);
			newFullRound.addAttack(attack);
		}
		
		fullRounds.add(newFullRound);
	}

	public String getName() {
		return name;
	}

	public int getBab() {
		return bab;
	}

	public int getStr() {
		return str;
	}

	public int getDex() {
		return dex;
	}

	public ArrayList<FullRound> getFullRounds() {
		return fullRounds;
	}

	public ArrayList<String> getFeats() {
		return feats;
	}
	
	
}
