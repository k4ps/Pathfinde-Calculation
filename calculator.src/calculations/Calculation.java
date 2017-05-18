/* This Program calculates the DPR (Damage per Round) of a Character from the fantasy role-playing game Pathfinder.
 * Copyright (C) 2017  Konrad Schön
 * This file is subject to the terms and conditions defined in file 'COPYING.txt', which is part of this source code package. 
 */

package calculations;

import attacks.Attack;
import attacks.FullRound;
import weapons.Weapon;

public class Calculation {
	static private FullRound fullRound;
	// static private Attack attack;
	static private Weapon weapon;
	static int ac;

	public Calculation(FullRound chosenFullRound, int ac) {
		Calculation.fullRound = chosenFullRound;
		Calculation.ac = ac;
	}

	public static double calcDPR(FullRound chosenFullRound, int givenAc) {
		new Calculation(chosenFullRound, givenAc);
		double dpr = 0;
		for (Attack attack : fullRound.getAttacks()) {
			Calculation.weapon=attack.getWeapon();
			double[] attackModifiers = attack.getAttackModi();
			double[] damageModifiers = attack.getDamageModi();
			double hitchance;
			double critchance = weapon.getCritRange() / 20.00;
			double averageDiceDamage = calcAverageDiceDamage(weapon);
			double precisionDamage = calcAveragePrecisionDamge(weapon, attack);
			int critmultiplier = weapon.getCritMultiplier();
			double bonusCritDamage = calcAverageBonusCritDamage(attack);

			for (int i = 0; i < attackModifiers.length; i++) {
				hitchance = (20.00 - (ac - attackModifiers[i] - 1)) / 20.00;
				double averageDamage = averageDiceDamage + damageModifiers[i];
				dpr += hitchance * (averageDamage + precisionDamage)
						+ critchance * hitchance * (averageDamage * critmultiplier + bonusCritDamage);
			}
		}

		return dpr;
	}

	private static double calcAverageBonusCritDamage(Attack attack2) {
		// TODO Auto-generated method stub
		return 0;
	}

	private static double calcAveragePrecisionDamge(Weapon weapon2, Attack attack2) {
		// TODO Auto-generated method stub
		return 0;
	}

	private static double calcAverageDiceDamage(Weapon weapon2) {
		// TODO Auto-generated method stub
		return 0;
	}

}
