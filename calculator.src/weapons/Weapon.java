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
	private int hitBonus;
	private int dmgBonus;
	private double dmgMod;
	private int type;

	public Weapon(String name, int[] dmgDice, ArrayList<String> specials, int critMultiplier, int critRange,
			int enhancement, int type) {
		this.name = name;
		this.dmgDice = dmgDice;
		this.specials = specials;
		this.critMultiplier = critMultiplier;
		this.critRange = critRange;
		this.hitBonus = enhancement;
		this.dmgBonus = enhancement;
		this.type = type;
		determineDmgMod();
		determineSpecialsEffect();
	}
	
	public Weapon(String name, int[] dmgDice, int critMultiplier, int critRange,
			int enhancement, int type) {
		this.name = name;
		this.dmgDice = dmgDice;
		this.critMultiplier = critMultiplier;
		this.critRange = critRange;
		this.hitBonus = enhancement;
		this.dmgBonus = enhancement;
		this.type = type;
		determineDmgMod();
	}

	public Weapon(String name, int[] dmgDice, ArrayList<String> specials, int critMultiplier, int critRange,
			int hitModificator, int dmgModificator, int type) {
		this.name = name;
		this.dmgDice = dmgDice;
		this.specials = specials;
		this.critMultiplier = critMultiplier;
		this.critRange = critRange;
		this.hitBonus = hitModificator;
		this.dmgBonus = dmgModificator;
		this.type = type;
		determineDmgMod();
		determineSpecialsEffect();
	}

	private void determineSpecialsEffect() {
		// TODO Auto-generated method stub
		for (String special : specials) {
			switch (special) {

			}
		}

	}

	private void determineDmgMod() {
		if (type == 0)
			dmgMod = 1.5;
		else
			dmgMod = 1.0;
	}

	public int getHitBonus() {
		return hitBonus;
	}

	public String getName() {
		return name;
	}
	
	/*
	 * @return int[2] containing infos about the dmg dice.
	 * @return [0]=amountOfDice, [1]=typeOfDice
	 */
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

	public int getDmgBonus() {
		return dmgBonus;
	}

	/*
	 * @return: Number describing the Type of Weapon. 0=2H, 1=1H-Main-Hand,
	 * 2=1H-Off-Hand, 3=Ranged, 4=Primary Natural, 5=Secondary Natural
	 */
	public int getType() {
		return type;
	}

	public double getDmgMod() {
		return dmgMod;
	}

	public void setDmgMod(double dmgMod) {
		this.dmgMod = dmgMod;
	}

}
