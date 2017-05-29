/* This Program calculates the DPR (Damage per Round) of a Character from the fantasy role-playing game Pathfinder.
 * Copyright (C) 2017  Konrad Schön
 * This file is subject to the terms and conditions defined in file 'COPYING.txt', which is part of this source code package. 
 */

package attacks;

import java.util.ArrayList;

// TODO: Auto-generated Javadoc
/**
 * The Class FullRound.
 */
public class FullRound {
	
	/** The attacks. */
	private ArrayList<Attack> attacks = new ArrayList<>();
	
	/** The name. */
	private String name;
	
	/**
	 * Instantiates a new full round.
	 *
	 * @param name the name
	 */
	public FullRound(String name){
		this.name=name + " mit: ";
	}
	
	/**
	 * Adds the attack.
	 *
	 * @param attack the attack
	 */
	public void addAttack(Attack attack){
		attacks.add(attack);
		name = name+attack.getWeaponName()+", ";
	}
	
	/**
	 * Gets the attacks.
	 *
	 * @return the attacks
	 */
	public ArrayList<Attack> getAttacks() {
		return attacks;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName(){
		return name;
	}

}
