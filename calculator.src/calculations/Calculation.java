/* This Program calculates the DPR (Damage per Round) of a Character from the fantasy role-playing game Pathfinder.
 * Copyright (C) 2017  Konrad Schön
 * This file is subject to the terms and conditions defined in file 'COPYING.txt', which is part of this source code package. 
 */

package calculations;

import attacks.Attack;
import attacks.FullRound;
import weapons.Weapon;

// TODO: Auto-generated Javadoc
/**
 * The Class Calculation.
 */
public class Calculation {
	
	/** The full round. */
	static private FullRound fullRound;
	
	/** The weapon. */
	static private Weapon weapon;
	
	/** The ac. */
	static int ac;

	/**
	 * Instantiates a new calculation.
	 *
	 * @param chosenFullRound the chosen full round
	 * @param ac the ac
	 */
	public Calculation(FullRound chosenFullRound, int ac) {
		Calculation.fullRound = chosenFullRound;
		Calculation.ac = ac;
	}

	/**
	 * Calc DPR.
	 *
	 * @param chosenFullRound the chosen full round
	 * @param givenAc the given ac
	 * @return the double
	 */
	/*
	 * calculates the dpr using the following formula(old): dpr += hitchance *
	 * (averageDamage + precisionDamage) + critchance * hitchance *
	 * (averageDamage * critmultiplier + bonusCritDamage);
	 */
	public static double calcDPR(FullRound chosenFullRound, int givenAc) {
		new Calculation(chosenFullRound, givenAc);
		double dpr = 0;
		for (Attack attack : fullRound.getAttacks()) {
			Calculation.weapon = attack.getWeapon();
			double[] attackModifiers = attack.getAttackModi();
			double[] damageModifiers = attack.getDamageModi();
			double hitchance;
			double critchance = weapon.getCritRange() / 20.00;
			double averageDiceDamage = calcAverageDiceDamage(weapon);
			double precisionDamage = calcAveragePrecisionDamge(weapon, attack);
			int critmultiplier = weapon.getCritMultiplier();
			double bonusCritDamage = calcAverageBonusCritDamage(attack);

			for (int i = 0; i < attackModifiers.length; i++) {
				hitchance = determineHitChance(ac, attackModifiers[i]);
				double averageDamage = averageDiceDamage + damageModifiers[i];
				dpr += hitchance * ((1 - critchance) * (averageDamage + precisionDamage)
						+ critchance * (1 - hitchance) * (averageDamage + precisionDamage)
						+ critchance * hitchance * (critmultiplier * averageDamage + bonusCritDamage));
			}
		}

		return dpr;
	}

	/**
	 * Determine hit chance.
	 *
	 * @param ac2 the ac 2
	 * @param attackModifier the attack modifier
	 * @return the double
	 */
	private static double determineHitChance(int ac2, double attackModifier) {
		if(attackModifier>=ac2-2) return 0.95;
		if(attackModifier+20<ac2) return 0.05;
		return (20.00 - (ac - attackModifier - 1)) / 20.00;		
	}

	/**
	 * Calc average bonus crit damage.
	 *
	 * @param attack2 the attack 2
	 * @return the double
	 */
	private static double calcAverageBonusCritDamage(Attack attack2) {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * Calc average precision damge.
	 *
	 * @param weapon2 the weapon 2
	 * @param attack2 the attack 2
	 * @return the double
	 */
	private static double calcAveragePrecisionDamge(Weapon weapon2, Attack attack2) {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * Calc average dice damage.
	 *
	 * @param weapon2 the weapon 2
	 * @return the double
	 */
	private static double calcAverageDiceDamage(Weapon weapon2) {
		int[] dmgDice = weapon2.getDmgDice();
		double averageDmg = 0.0;
		for (int i = 1; i <= dmgDice[1]; i++) {
			averageDmg += i;
		}
		return averageDmg / dmgDice[1] * dmgDice[0];
	}

}
