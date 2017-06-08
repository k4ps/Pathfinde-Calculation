/* This Program calculates the DPR (Damage per Round) of a Character from the fantasy role-playing game Pathfinder.
 * Copyright (C) 2017  Konrad Schön
 * This file is subject to the terms and conditions defined in file 'COPYING.txt', which is part of this source code package. 
 */

package calculations;

import java.util.concurrent.ThreadLocalRandom;

import javax.swing.JTextArea;

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
	 * @param chosenFullRound
	 *            the chosen full round
	 * @param ac
	 *            the ac
	 */
	public Calculation(FullRound chosenFullRound, int ac) {
		Calculation.fullRound = chosenFullRound;
		Calculation.ac = ac;
	}

	/**
	 * Calc DPR.
	 *
	 * @param chosenFullRound
	 *            the chosen full round
	 * @param givenAc
	 *            the given ac
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
			int[] attackModifiers = attack.getAttackModi();
			int[] damageModifiers = attack.getDamageModi();
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
	 * @param ac2
	 *            the ac 2
	 * @param attackModifier
	 *            the attack modifier
	 * @return the double
	 */
	private static double determineHitChance(int ac2, double attackModifier) {
		if (attackModifier >= ac2 - 2)
			return 0.95;
		if (attackModifier + 20 < ac2)
			return 0.05;
		return (20.00 - (ac - attackModifier - 1)) / 20.00;
	}

	/**
	 * Calc average bonus crit damage.
	 *
	 * @param attack2
	 *            the attack 2
	 * @return the double
	 */
	private static double calcAverageBonusCritDamage(Attack attack2) {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * Calc average precision damge.
	 *
	 * @param weapon2
	 *            the weapon 2
	 * @param attack2
	 *            the attack 2
	 * @return the double
	 */
	private static double calcAveragePrecisionDamge(Weapon weapon2, Attack attack2) {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * Calc average dice damage.
	 *
	 * @param weapon2
	 *            the weapon 2
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

	public static String simulate(FullRound fullRound, int ac) {
		String returnString = "-----Simulated damage against enemy with " + ac + "AC-----\n";
		for (Attack attack : fullRound.getAttacks()) {
			for (int i = 0; i < attack.getAttackModi().length; i++) {
				int hitvalue = attack.getAttackModi()[i];
				int rolledHit = ThreadLocalRandom.current().nextInt(1, 20);
				// critcal fail
				if (rolledHit == 1) {
					returnString = returnString + attack.getWeaponName() + " critically misses!\n";
					continue;
				}
				// possible crit
				if (((20 - rolledHit) <= attack.getWeapon().getCritRange() && hitvalue + rolledHit >= ac)
						|| rolledHit == 20) {
					rolledHit = ThreadLocalRandom.current().nextInt(1, 20);
					// not confirmed crit
					if (rolledHit == 1 || hitvalue + rolledHit < ac) {
						returnString = returnString + attack.getWeaponName() + " hits and deals "
								+ normalDamage(attack) + " damage\n";
						continue;
					}
					// confirmed crit
					if (hitvalue + rolledHit >= ac || rolledHit == 20) {
						returnString = returnString + attack.getWeaponName() + " hits critical and deals "
								+ criticalDamage(attack) + " damage\n";
						continue;
					}
				}
				// normal hit
				if (hitvalue+rolledHit >= ac) {
					returnString = returnString + attack.getWeaponName() + " hits and deals "
							+ normalDamage(attack) + " damage\n";
					continue;
				} else {
					returnString = returnString + attack.getWeaponName() + " misses\n";
					continue;
				}
			}
		}

		return returnString;
	}

	private static int criticalDamage(Attack attack) {
		int[] dmgDice = attack.getWeapon().getDmgDice();
		int dmg = 0;
		for (int j = 1; j <= attack.getWeapon().getCritMultiplier(); j++) {
			dmg += attack.getDamageModi()[0];
			for (int k = 1; k <= dmgDice[0]; k++) {
				dmg+= ThreadLocalRandom.current().nextInt(1, dmgDice[1]);
			}
		}
		return dmg;
	}

	private static int normalDamage(Attack attack) {
		int[] dmgDice = attack.getWeapon().getDmgDice();
		int dmg = attack.getDamageModi()[0];
		for (int j = 1; j <= dmgDice[0]; j++) {
			dmg+= ThreadLocalRandom.current().nextInt(1, dmgDice[1]);
		}
		return dmg;
	}

}
