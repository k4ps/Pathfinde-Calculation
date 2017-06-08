/* This Program calculates the DPR (Damage per Round) of a Character from the fantasy role-playing game Pathfinder.
 * Copyright (C) 2017  Konrad Schön
 * This file is subject to the terms and conditions defined in file 'COPYING.txt', which is part of this source code package. 
 */

package characters;

import java.util.ArrayList;

import attacks.Attack;
import attacks.FullRound;
import weapons.Weapon;

// TODO: Auto-generated Javadoc
/**
 * The Class PfCharacter.
 */
public class PfCharacter {

	/** The name. */
	private String name;

	/** The bab. */
	private int bab;

	/** The str. */
	private int str;

	/** The dex. */
	private int dex;

	/** The full rounds. */
	private ArrayList<FullRound> fullRounds = new ArrayList<>();

	/** The weapons. */
	private ArrayList<Weapon> weapons = new ArrayList<>();

	/** The feats. */
	private ArrayList<String> feats = new ArrayList<>();

	/**
	 * The size of the character. -4==fine, -3==Diminutive, -2==Tiny, -1==Small,
	 * 0==Medium, 1==Large, 2==Huge, 3==Gargantuan, 4==Colossal
	 */
	private int size=5;

	/**
	 * Instantiates a new pf character.
	 *
	 * @param name
	 *            the name
	 * @param bab
	 *            the bab
	 * @param str
	 *            the str
	 * @param dex
	 *            the dex
	 */
	public PfCharacter(String name, int bab, int dex, int str) {
		this.name = name;
		this.bab = bab;
		this.str = str;
		this.dex = dex;
	}

	public PfCharacter() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Adds the full round.
	 *
	 * @param name
	 *            the name
	 * @param usedWeapons
	 *            the used weapons
	 * @param fullRound
	 *            the full round
	 */
	public void addFullRound(String name, ArrayList<Weapon> usedWeapons, boolean fullRound) {
		FullRound newFullRound = new FullRound(name);
		for (Weapon weapon : usedWeapons) {
			Attack attack = new Attack(this, weapon, fullRound);
			newFullRound.addAttack(attack);
		}

		fullRounds.add(newFullRound);
	}

	/**
	 * Adds the buff.
	 *
	 * @param buffname
	 *            the buffname
	 */
	public void addBuff(String buffname) {
		switch (buffname) {
		case ("Haste"): {
			for (FullRound fullround : fullRounds) {
				for (Attack attack : fullround.getAttacks()) {
					if (attack.isFullRound())
						attack.addFullBabAttack();
				}
			}

		}
		}
	}

	/**
	 * Adds the feat.
	 *
	 * @param feat
	 *            the feat
	 */
	public void addFeat(String feat) {
		feats.add(feat);
		if (feat.contains("Weapon Focus")) {
			for (Weapon weapon : weapons) {
				if (("Weapon Focus " + weapon.getName()).equals(feat))
					weapon.setHitBonus(weapon.getHitBonus() + 1);
			}
		}
		if (feat.contains("Improved Critical")) {
			for (Weapon weapon : weapons) {
				if (("Improved Critical " + weapon.getName()).equals(feat) && !(weapon.getSpecials().contains("Keen")))
					weapon.setCritRange(weapon.getCritRange() * 2);
			}
		}
	}

	/**
	 * Adds the weapon.
	 *
	 * @param weapon
	 *            the weapon
	 */
	public void addWeapon(Weapon weapon) {
		weapon = adaptWeaponbySize(weapon, size);
		weapons.add(weapon);
	}

	private Weapon adaptWeaponbySize(Weapon weapon, int newSize) {
		// TODO Auto-generated method stub
		int[][] diceArray = new int[][] { { 1, 1 }, { 1, 2 }, { 1, 3 }, { 1, 4 }, { 1, 6 }, { 1, 8 }, { 1, 10 },
				{ 2, 6 }, { 2, 8 }, { 3, 6 }, { 3, 8 }, { 4, 6 }, { 4, 8 }, { 6, 6 }, { 6, 8 }, { 8, 6 }, { 8, 8 },
				{ 12, 6 }, { 12, 8 }, { 16, 6 } };
		weapon.setHitBonus(weapon.getHitBonus()-newSize);

		if (newSize < size)
			return decreasedWeaponSize(weapon, diceArray);
		if (newSize > size)
			return increasedWeaponSize(weapon, diceArray);
		if (size < 0)
			return decreasedWeaponSize(weapon, diceArray);
		if (size > 0)
			return increasedWeaponSize(weapon, diceArray);
		return weapon;
	}

	private Weapon increasedWeaponSize(Weapon weapon, int[][] diceArray) {
		Weapon newWeapon = weapon;
		int[] weaponDice = newWeapon.getDmgDice();
		if (weaponDice == new int[] { 2, 4 })
			newWeapon.setDmgDice(diceArray[5]);
		if (weaponDice == new int[] { 1, 12 })
			newWeapon.setDmgDice(diceArray[7]);
		for (int i = 0; i < diceArray.length; i++) {
			if (diceArray[i] == weaponDice) {
				if (size < 0 || i <= 4) {
					newWeapon.setDmgDice(diceArray[i + 1]);
				} else {
					newWeapon.setDmgDice(diceArray[i + 2]);
				}

			}
		}
		return newWeapon;
	}

	private Weapon decreasedWeaponSize(Weapon weapon, int[][] diceArray) {
		// TODO Auto-generated method stub
		Weapon newWeapon = weapon;
		int[] weaponDice = newWeapon.getDmgDice();
		if (weaponDice == new int[] { 2, 4 })
			newWeapon.setDmgDice(diceArray[5]);
		if (weaponDice == new int[] { 1, 12 })
			newWeapon.setDmgDice(diceArray[7]);
		for (int i = 0; i < diceArray.length; i++) {
			if (diceArray[i] == weaponDice) {
				if (size <= 0 || i <= 5) {
					newWeapon.setDmgDice(diceArray[i - 1]);
				} else {
					newWeapon.setDmgDice(diceArray[i - 2]);
				}

			}
		}
		return newWeapon;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setBab(int bab) {
		this.bab = bab;
	}

	public void setStr(int str) {
		this.str = str;
	}

	public void setDex(int dex) {
		this.dex = dex;
	}

	public void setFullRounds(ArrayList<FullRound> fullRounds) {
		this.fullRounds = fullRounds;
	}

	public void setWeapons(ArrayList<Weapon> weapons) {
		this.weapons = weapons;
	}

	public void setFeats(ArrayList<String> feats) {
		this.feats = feats;
	}

	public void setSize(int size) {
		if (this.size != 5){
			this.str=this.str+(size-this.size)*2;
			this.dex=this.dex-(size-this.size)*2;
			for (Weapon weapon : weapons) {
				weapon = adaptWeaponbySize(weapon, size);
			}
		}
		this.size = size;
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
	 * Gets the bab.
	 *
	 * @return the bab
	 */
	public int getBab() {
		return bab;
	}

	/**
	 * Gets the str.
	 *
	 * @return the str
	 */
	public int getStr() {
		return str;
	}

	/**
	 * Gets the dex.
	 *
	 * @return the dex
	 */
	public int getDex() {
		return dex;
	}

	/**
	 * Gets the full rounds.
	 *
	 * @return the full rounds
	 */
	public ArrayList<FullRound> getFullRounds() {
		return fullRounds;
	}

	/**
	 * Gets the feats.
	 *
	 * @return the feats
	 */
	public ArrayList<String> getFeats() {
		return feats;
	}

	/**
	 * Gets the weapons.
	 *
	 * @return the weapons
	 */
	public ArrayList<Weapon> getWeapons() {
		return weapons;
	}

	public int getSize() {
		return size;
	}

	public void addFullRound(FullRound readFullRound) {
		fullRounds.add(readFullRound);

	}

}
