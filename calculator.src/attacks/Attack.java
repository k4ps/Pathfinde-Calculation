/* This Program calculates the DPR (Damage per Round) of a Character from the fantasy role-playing game Pathfinder.
 * Copyright (C) 2017  Konrad Schön
 * This file is subject to the terms and conditions defined in file 'COPYING.txt', which is part of this source code package. 
 */

package attacks;

import java.util.ArrayList;

import weapons.*;

// TODO: Auto-generated Javadoc
/**
 * The Class Attack.
 */
public class Attack {
	private double[] attackModi;
	private double[] damageModi;
	private Weapon weapon;
	private String weaponName;
	private boolean fullRound;
	private ArrayList<int[]> precisionDmgDice = new ArrayList<>();

	/**
	 * Instantiates a new attack.
	 *
	 * @param hitStatMod the hit stat mod
	 * @param damageStatMod the damage stat mod
	 * @param bab the bab
	 * @param weapon the weapon
	 * @param fullRound the full round
	 */
	public Attack(int hitStatMod, int damageStatMod, int bab, Weapon weapon, boolean fullRound) {
		int arraySize = determineArraySize(bab);
		this.weapon = weapon;
		this.fullRound=fullRound;
		double hitBonus = bab + hitStatMod + weapon.getHitBonus();
		double damageBonus = damageStatMod * weapon.getDmgMod() + weapon.getDmgBonus();
		weaponName = weapon.getName();

		if (fullRound)
			initaliseFullRound(arraySize, hitBonus, damageBonus);
		else {
			initaliseSingleAttack(hitBonus, damageBonus);
		}
	}

	private void initaliseSingleAttack(double hitBonus, double damageBonus) {
		attackModi = new double[] { hitBonus };
		damageModi = new double[] { damageBonus };

	}

	private void initaliseFullRound(int arraySize, double hitBonus, double damageBonus) {
		attackModi = new double[arraySize];
		damageModi = new double[arraySize];

		for (int i = 0; i < arraySize; i++) {
			attackModi[i] = hitBonus - 5 * i;
			damageModi[i] = damageBonus;
		}

	}

	/**
	 * Adds the static attack modi.
	 *
	 * @param staticAttackBonus the static attack bonus
	 */
	public void addStaticAttackModi(double staticAttackBonus) {
		for (int i = 0; i < attackModi.length; i++) {
			attackModi[i] += staticAttackBonus;
		}
	}

	/**
	 * Adds the static damage modi.
	 *
	 * @param staticDamageBonus the static damage bonus
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
		double[] tempAttack = attackModi;
		double[] tempDamage = damageModi;
		attackModi = new double[tempAttack.length + 1];
		damageModi = new double[tempDamage.length + 1];

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
	public double[] getAttackModi() {
		return attackModi;
	}

	/**
	 * Gets the damage modi.
	 *
	 * @return the damage modi
	 */
	public double[] getDamageModi() {
		return damageModi;
	}

	private int determineArraySize(int bab) {
		if (bab < 5)
			return 1;
		if (bab >= 5 && bab < 10)
			return 2;
		if (bab >= 10 && bab < 15)
			return 3;
		return 4;
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

	/**
	 * Checks if is full round.
	 *
	 * @return true, if is full round
	 */
	public boolean isFullRound() {
		return fullRound;
	}

}
