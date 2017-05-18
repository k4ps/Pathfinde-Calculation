/* This Program calculates the DPR (Damage per Round) of a Character from the fantasy role-playing game Pathfinder.
 * Copyright (C) 2017  Konrad Schön
 * This file is subject to the terms and conditions defined in file 'COPYING.txt', which is part of this source code package. 
 */

package main;

import attacks.FullRound;
import calculations.Calculation;
import characters.PfCharacter;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PfCharacter testChar=new PfCharacter("testerino", 14, 26, 12);
		for(FullRound fullRound:testChar.getFullRounds()){
			System.out.println("Dpr von" + fullRound.getName() + "= " + Calculation.calcDPR(fullRound, 25));
		}
	}

}
