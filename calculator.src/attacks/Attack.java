/* This Program calculates the DPR (Damage per Round) of a Character from the fantasy role-playing game Pathfinder.
 * Copyright (C) 2017  Konrad Schön
 * This file is subject to the terms and conditions defined in file 'COPYING.txt', which is part of this source code package. 
 */

package attacks;

import java.util.ArrayList;

import characters.PfCharacter;
import weapons.*;

// TODO: Auto-generated Javadoc
/**
 * The Class Attack.
 */
public class Attack {
	
	/** The attack modi. */
	private int[] attackModi;
	
	/** The damage modi. */
	private int[] damageModi;
	
	/** The weapon. */
	private Weapon weapon;
	
	/** The weapon name. */
	private String weaponName;
	
	/** The full round. */
	private boolean fullRound;
	
	/** The precision dmg dice. */
	private ArrayList<int[]> precisionDmgDice = new ArrayList<>();

	/**
	 * Instantiates a new attack.
	 *
	 * @param character the character
	 * @param weapon the weapon
	 * @param fullRound the full round
	 */
	public Attack(PfCharacter character, Weapon weapon, boolean fullRound) {
		double hitMod = determineHitMod(character);
		int arraySize = determineArraySize(character);
		this.weapon = weapon;
		this.fullRound = fullRound;
		int hitBonus = determineHitBonus(character, weapon, hitMod);
		int damageBonus = determineDmgBonus(character, weapon, hitMod);
		weaponName = weapon.getName();

		if (fullRound)
			initaliseFullRound(arraySize, hitBonus, damageBonus);
		else {
			initaliseSingleAttack(hitBonus, damageBonus);
		}
	}

	public Attack() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Determine dmg bonus.
	 *
	 * @param character the character
	 * @param weapon2 the weapon 2
	 * @param hitMod the hit mod
	 * @return the double
	 */
	private int determineDmgBonus(PfCharacter character, Weapon weapon2, double hitMod) {
		double standardDmgBonus = weapon.getDmgBonus();
		switch (weapon.getType()) {
		case (0): {
			standardDmgBonus += ((character.getStr() - 10) / 2) * weapon.getDmgMod();
			for (String feat : character.getFeats()) {
				switch (feat) {
				case ("Power Attack"): {
					standardDmgBonus = weapon.getDmgBonus() + 2 * weapon.getDmgMod() * (1 + character.getBab() / 4);
				}
				case (""): {

				}
				}
			}
		}
		case (1): {
			standardDmgBonus += ((character.getStr() - 10) / 2) * weapon.getDmgMod();
			for (String feat : character.getFeats()) {
				switch (feat) {
				}
			}

		}
		case (2): {
			standardDmgBonus += ((character.getStr() - 10) / 2) * weapon.getDmgMod();
			for (String feat : character.getFeats()) {
				switch (feat) {
				}
			}
		}
		case (3): {
			for (String feat : character.getFeats()) {
				switch (feat) {
				case("Point-Blank Shot"):{
					standardDmgBonus++;
					break;
				}
				}
			}
		}
		case (4): {
			standardDmgBonus += ((character.getStr() - 10) / 2) * weapon.getDmgMod();
			for (String feat : character.getFeats()) {
				switch (feat) {
				}
			}
		}
		case (5): {
			standardDmgBonus += ((character.getStr() - 10) / 2) * weapon.getDmgMod();
			for (String feat : character.getFeats()) {
				switch (feat) {
				}
			}
		}
		}
		return (int) standardDmgBonus;
	}

	/**
	 * Determine hit bonus.
	 *
	 * @param character the character
	 * @param weapon2 the weapon 2
	 * @param hitMod the hit mod
	 * @return the double
	 */
	private int determineHitBonus(PfCharacter character, Weapon weapon2, double hitMod) {
		double standardHitBonus = character.getBab() + hitMod + weapon.getHitBonus();
		switch (weapon.getType()) {
		case (0): {
			for (String feat : character.getFeats()) {
				switch (feat) {
				case ("Power Attack"): {
					standardHitBonus += - 1 - character.getBab() / 4;
					break;
				}
				case (""):
					;
				}
			}
		}
		case (1): {
			for (String feat : character.getFeats()) {
				switch (feat) {
				}
			}

		}
		case (2): {
			for (String feat : character.getFeats()) {
				switch (feat) {
				}
			}
		}
		case (3): {
			for (String feat : character.getFeats()) {
				switch (feat) {
				case ("Rapid Shot"): {
					standardHitBonus += -2;
					break;
				}
				case ("Point-Blank Shot"): {
					standardHitBonus++;
					break;
				}
				}
			}

		}
		case (4): {
			for (String feat : character.getFeats()) {
				switch (feat) {
				}
			}
		}
		case (5): {
			for (String feat : character.getFeats()) {
				switch (feat) {
				}
			}
		}
		}
		return (int) standardHitBonus;
	}

	/**
	 * Determine hit mod.
	 *
	 * @param character the character
	 * @return the double
	 */
	private double determineHitMod(PfCharacter character) {
		if (character.getFeats().contains("Weapon Finesse"))
			return (character.getDex() - 10) / 2;
		else
			return (character.getStr() - 10) / 2;
	}

	/**
	 * Initalise single attack.
	 *
	 * @param hitBonus the hit bonus
	 * @param damageBonus the damage bonus
	 */
	private void initaliseSingleAttack(int hitBonus, int damageBonus) {
		attackModi = new int[] { hitBonus };
		damageModi = new int[] { damageBonus };

	}

	/**
	 * Initalise full round.
	 *
	 * @param arraySize the array size
	 * @param hitBonus the hit bonus
	 * @param damageBonus the damage bonus
	 */
	private void initaliseFullRound(int arraySize, int hitBonus, int damageBonus) {
		attackModi = new int[arraySize];
		damageModi = new int[arraySize];

		for (int i = 0; i < arraySize; i++) {
			attackModi[i] = hitBonus - 5 * i;
			damageModi[i] = damageBonus;
		}

	}

	/**
	 * Adds the static attack modi.
	 *
	 * @param staticAttackBonus
	 *            the static attack bonus
	 */
	public void addStaticAttackModi(double staticAttackBonus) {
		for (int i = 0; i < attackModi.length; i++) {
			attackModi[i] += staticAttackBonus;
		}
	}

	/**
	 * Adds the static damage modi.
	 *
	 * @param staticDamageBonus
	 *            the static damage bonus
	 */
	public void addStaticDamageModi(double staticDamageBonus) {
		for (int i = 0; i < damageModi.length; i++) {
			damageModi[i] += staticDamageBonus;
		}
	}

	/**
	 * Adds the full bab attack.
	 */
	public void addFullBabAttack() {
		int[] tempAttack = attackModi;
		int[] tempDamage = damageModi;
		attackModi = new int[tempAttack.length + 1];
		damageModi = new int[tempDamage.length + 1];

		attackModi[0] = tempAttack[0];
		damageModi[0] = tempDamage[0];

		for (int i = 1; i < attackModi.length; i++) {
			attackModi[i] = tempAttack[i - 1];
			damageModi[i] = tempDamage[i - 1];
		}

	}

	/**
	 * Gets the weapon.
	 *
	 * @return the weapon
	 */
	public Weapon getWeapon() {
		return weapon;
	}

	/**
	 * Gets the attack modi.
	 *
	 * @return the attack modi
	 */
	public int[] getAttackModi() {
		return attackModi;
	}

	/**
	 * Gets the damage modi.
	 *
	 * @return the damage modi
	 */
	public int[] getDamageModi() {
		return damageModi;
	}

	/**
	 * Determine array size.
	 *
	 * @param character the character
	 * @return the int
	 */
	private int determineArraySize(PfCharacter character) {
		int arraysize = 0;
		if (character.getBab() < 5)
			arraysize = 1;
		if (character.getBab() >= 5 && character.getBab() < 10)
			arraysize = 2;
		if (character.getBab() >= 10 && character.getBab() < 15)
			arraysize = 3;
		for (String feat : character.getFeats()) {
			switch (feat) {
			case ("Rapid Shot"):
				arraysize++;
				break;
			case ("Flury of Blows"):
				arraysize += character.getBab() / 8 + 1;
				break;
			}
		}
		return arraysize;
	}

	/**
	 * Gets the weapon name.
	 *
	 * @return the weapon name
	 */
	public String getWeaponName() {
		return weaponName;
	}

	/**
	 * Gets the precision dmg dice.
	 *
	 * @return the precision dmg dice
	 */
	public ArrayList<int[]> getPrecisionDmgDice() {
		return precisionDmgDice;
	}

	public void setAttackModi(int[] attackModi) {
		this.attackModi = attackModi;
	}

	public void setDamageModi(int[] damageModi) {
		this.damageModi = damageModi;
	}

	public void setWeapon(Weapon weapon) {
		this.weapon = weapon;
		setWeaponName(weapon.getName());
	}

	public void setWeaponName(String weaponName) {
		this.weaponName = weaponName;
	}

	public void setFullRound(boolean fullRound) {
		this.fullRound = fullRound;
	}

	public void setPrecisionDmgDice(ArrayList<int[]> precisionDmgDice) {
		this.precisionDmgDice = precisionDmgDice;
	}

	/**
	 * Checks if is full round.
	 *
	 * @return true, if is full round
	 */
	public boolean isFullRound() {
		return fullRound;
	}

	public void setFullRound(String readLine) {
		if(readLine.equals("true")) fullRound=true;
		else fullRound=false;		
	}

	public String getWeaponDescription() {
		return weapon.getDescritpion();
	}

}
