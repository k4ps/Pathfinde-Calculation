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
	public PfCharacter(String name, int bab, int str, int dex) {
		this.name = name;
		this.bab = bab;
		this.str = str;
		this.dex = dex;
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
	 * @param feat the feat
	 */
	public void addFeat(String feat){
		feats.add(feat);
	}

	/**
	 * Adds the weapon.
	 *
	 * @param weapon
	 *            the weapon
	 */
	public void addWeapon(Weapon weapon) {
		weapons.add(weapon);
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

}
