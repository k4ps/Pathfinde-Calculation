/* This Program calculates the DPR (Damage per Round) of a Character from the fantasy role-playing game Pathfinder.
 * Copyright (C) 2017  Konrad Schön
 * This file is subject to the terms and conditions defined in file 'COPYING.txt', which is part of this source code package. 
 */

package weapons;

import java.util.ArrayList;

// TODO: Auto-generated Javadoc
/**
 * The Class Weapon.
 */
// TODO: Auto-generated Javadoc
public class Weapon {

	/** The name. */
	private String name;

	/** The dmg dice. */
	private int[] dmgDice = new int[2];

	/** The precision dmg dice. */
	private ArrayList<int[]> precisionDmgDice = new ArrayList<>();

	/** The specials. */
	private ArrayList<String> specials = new ArrayList<>();

	/** The crit multiplier. */
	private int critMultiplier;

	/** The crit range. */
	private int critRange;

	/** The hit bonus. */
	private int hitBonus;

	/** The dmg bonus. */
	private int dmgBonus;

	/** The dmg mod. */
	private double dmgMod;

	/** The type. */
	private int type;

	/**
	 * Instantiates a new weapon.
	 *
	 * @param name
	 *            the name
	 * @param dmgDice
	 *            the dmg dice
	 * @param specials
	 *            the specials
	 * @param critMultiplier
	 *            the crit multiplier
	 * @param critRange
	 *            the crit range
	 * @param enhancement
	 *            the enhancement
	 * @param type
	 *            the type
	 */
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

	/**
	 * Instantiates a new weapon.
	 *
	 * @param name
	 *            the name
	 * @param dmgDice
	 *            the dmg dice
	 * @param critMultiplier
	 *            the crit multiplier
	 * @param critRange
	 *            the crit range
	 * @param enhancement
	 *            the enhancement
	 * @param type
	 *            the type
	 */
	public Weapon(String name, int[] dmgDice, int critMultiplier, int critRange, int enhancement, int type) {
		this.name = name;
		this.dmgDice = dmgDice;
		this.critMultiplier = critMultiplier;
		this.critRange = critRange;
		this.hitBonus = enhancement;
		this.dmgBonus = enhancement;
		this.type = type;
		determineDmgMod();
	}

	/**
	 * Instantiates a new weapon.
	 *
	 * @param name
	 *            the name
	 * @param dmgDice
	 *            the dmg dice
	 * @param specials
	 *            the specials
	 * @param critMultiplier
	 *            the crit multiplier
	 * @param critRange
	 *            the crit range
	 * @param hitModificator
	 *            the hit modificator
	 * @param dmgModificator
	 *            the dmg modificator
	 * @param type
	 *            the type
	 */
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

	/**
	 * Instantiates a new weapon.
	 */
	public Weapon() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Determine specials effect.
	 */
	private void determineSpecialsEffect() {
		// TODO Auto-generated method stub
		for (String special : specials) {
			switch (special) {

			}
		}

	}

	/**
	 * Determine dmg mod.
	 */
	private void determineDmgMod() {
		if (type == 0)
			dmgMod = 1.5;
		else
			dmgMod = 1.0;
	}

	/**
	 * Gets the hit bonus.
	 *
	 * @return the hit bonus
	 */
	public int getHitBonus() {
		return hitBonus;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Gets the dmg dice.
	 *
	 * @return the dmg dice
	 */
	/*
	 * @return int[2] containing infos about the dmg dice.
	 * 
	 * @return [0]=amountOfDice, [1]=typeOfDice
	 */
	public int[] getDmgDice() {
		return dmgDice;
	}

	/**
	 * Gets the precision dmg dice.
	 *
	 * @return the precision dmg dice
	 */
	public ArrayList<int[]> getPrecisionDmgDice() {
		return precisionDmgDice;
	}

	public ArrayList<String> getSpecials() {
		return specials;
	}

	/**
	 * Gets the crit multiplier.
	 *
	 * @return the crit multiplier
	 */
	public int getCritMultiplier() {
		return critMultiplier;
	}

	/**
	 * Gets the crit range.
	 *
	 * @return the crit range
	 */
	public int getCritRange() {
		return critRange;
	}

	/**
	 * Gets the dmg bonus.
	 *
	 * @return the dmg bonus
	 */
	public int getDmgBonus() {
		return dmgBonus;
	}

	/**
	 * Gets the type.
	 *
	 * @return Number describing the Type of Weapon. 0=2H, 1=1H-Main-Hand,
	 *         2=1H-Off-Hand, 3=Ranged, 4=Primary Natural, 5=Secondary Natural
	 */
	public int getType() {
		return type;
	}

	/**
	 * Gets the dmg mod.
	 *
	 * @return the dmg mod
	 */
	public double getDmgMod() {
		return dmgMod;
	}
	
	public String getDescritpion(){
		String description;
		if(dmgBonus==0&&hitBonus==1) description="M ";
		else if(dmgBonus==0&&hitBonus==0) description="";
		else description="+"+String.valueOf(dmgBonus)+" ";
		for(String special:specials) description= description+special+", ";
		return description+getName();
	}

	/**
	 * Sets the hit bonus.
	 *
	 * @param hitBonus
	 *            the new hit bonus
	 */
	public void setHitBonus(int hitBonus) {
		this.hitBonus = hitBonus;
	}

	/**
	 * Sets the dmg bonus.
	 *
	 * @param dmgBonus
	 *            the new dmg bonus
	 */
	public void setDmgBonus(int dmgBonus) {
		this.dmgBonus = dmgBonus;
	}

	/**
	 * Sets the dmg mod.
	 *
	 * @param dmgMod
	 *            the new dmg mod
	 */
	public void setDmgMod(double dmgMod) {
		this.dmgMod = dmgMod;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDmgDice(int[] dmgDice) {
		this.dmgDice = dmgDice;
	}

	public void setPrecisionDmgDice(ArrayList<int[]> precisionDmgDice) {
		this.precisionDmgDice = precisionDmgDice;
	}

	public void setSpecials(ArrayList<String> specials) {
		this.specials = specials;
	}

	public void setCritMultiplier(int critMultiplier) {
		this.critMultiplier = critMultiplier;
	}

	public void setCritRange(int critRange) {
		this.critRange = critRange;
	}

	/**
	 * Adds the special.
	 *
	 * @param special
	 *            the special
	 * @return true, if successful
	 */
	public boolean addSpecial(String special) {
		if (specials.contains(special))
			return false;
		specials.add(special);
		return true;
	}

	public void setType(int type) {
		this.type = type;
	}

	public void setEnhancement(int enhancement) {
		setHitBonus(enhancement);
		setDmgBonus(enhancement);
		if (enhancement == 0) {
			setHitBonus(1);
			setDmgBonus(0);
		} else if (enhancement == -1) {
			setHitBonus(0);
			setDmgBonus(0);
		}
	}

}
