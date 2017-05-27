/* This Program calculates the DPR (Damage per Round) of a Character from the fantasy role-playing game Pathfinder.
 * Copyright (C) 2017  Konrad Schön
 * This file is subject to the terms and conditions defined in file 'COPYING.txt', which is part of this source code package. 
 */

package main;

import java.util.ArrayList;

import attacks.FullRound;
import calculations.Calculation;
import characters.PfCharacter;
import weapons.Weapon;

// TODO: Auto-generated Javadoc
/**
 * The Class Main.
 */
public class Main {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PfCharacter testChar=new PfCharacter("testerino", 14, 26, 12);
		testChar.addFeat("Power Attack");
		testChar.addWeapon(new Weapon("Falchion", new int[]{2,4}, 2, 6, 2, 0));
		ArrayList<Weapon> weapons = new ArrayList<>();
		weapons=testChar.getWeapons();
		testChar.addFullRound("Test", weapons, true);
		
		for(FullRound fullRound:testChar.getFullRounds()){
			System.out.println("Dpr von " + fullRound.getName() + " = " + Calculation.calcDPR(fullRound, 20));
		}
	}

}
