/* This Program calculates the DPR (Damage per Round) of a Character from the fantasy role-playing game Pathfinder.
 * Copyright (C) 2017  Konrad Schön
 * This file is subject to the terms and conditions defined in file 'COPYING.txt', which is part of this source code package. 
 */

package weapons;

import java.util.ArrayList;

public class Weapon {
	private String name;
	private int[] dmgDice = new int[2];
	private ArrayList<int[]> precisionDmgDice = new ArrayList<>();
	private ArrayList<String> specials = new ArrayList<>();
	private int critMultiplier;
	private int critRange;
	private int hitModificator;
	private int dmgModificator;
	private int type;

	public Weapon(String name, int[] dmgDice, ArrayList<String> specials, int critMultiplier, int critRange,
			int enhancement) {
		this.name = name;
		this.dmgDice = dmgDice;
		this.specials = specials;
		this.critMultiplier = critMultiplier;
		this.critRange = critRange;
		this.hitModificator = enhancement;
		this.dmgModificator = enhancement;
		determineSpecialsEffect();
	}

	public Weapon(String name, int[] dmgDice, ArrayList<String> specials, int critMultiplier, int critRange,
			int hitModificator, int dmgModificator) {
		this.name = name;
		this.dmgDice = dmgDice;
		this.specials = specials;
		this.critMultiplier = critMultiplier;
		this.critRange = critRange;
		this.hitModificator = hitModificator;
		this.dmgModificator = dmgModificator;
		determineSpecialsEffect();
	}

	private void determineSpecialsEffect() {
		// TODO Auto-generated method stub
		for (String special : specials) {
			switch (special) {

			}
		}

	}

	public int getHitModificator() {
		return hitModificator;
	}

	public String getName() {
		return name;
	}

	public int[] getDmgDice() {
		return dmgDice;
	}

	public ArrayList<int[]> getPrecisionDmgDice() {
		return precisionDmgDice;
	}

	public int getCritMultiplier() {
		return critMultiplier;
	}

	public int getCritRange() {
		return critRange;
	}

	public int getDmgModificator() {
		return dmgModificator;
	}

	/*
	 * @return: Number describing the Type of Weapon. 0=2H, 1=1H-Main-Hand,
	 * 2=1H-Off-Hand, 3=Ranged, 4=Primary Natural, 5=Secondary Natural
	 */
	public int getType() {
		return type;
	}

}
