/* This Program calculates the DPR (Damage per Round) of a Character from the fantasy role-playing game Pathfinder.
 * Copyright (C) 2017  Konrad Schön
 * This file is subject to the terms and conditions defined in file 'COPYING.txt', which is part of this source code package. 
 */

package characters;

import java.util.ArrayList;

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
