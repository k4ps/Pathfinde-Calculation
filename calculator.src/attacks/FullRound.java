/* This Program calculates the DPR (Damage per Round) of a Character from the fantasy role-playing game Pathfinder.
 * Copyright (C) 2017  Konrad Schön
 * This file is subject to the terms and conditions defined in file 'COPYING.txt', which is part of this source code package. 
 */

package attacks;

import java.util.ArrayList;

public class FullRound {
	private ArrayList<Attack> attacks = new ArrayList<>();
	private String name;
	
	public FullRound(String name){
		this.name=name + " mit: ";
	}
	
	public void addAttack(Attack attack){
		attacks.add(attack);
		name = name+attack.getWeaponName()+", ";
	}
	
	public ArrayList<Attack> getAttacks() {
		return attacks;
	}

	public String getName(){
		return name;
	}

}
