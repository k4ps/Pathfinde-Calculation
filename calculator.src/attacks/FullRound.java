/* This Program calculates the DPR (Damage per Round) of a Character from the fantasy role-playing game Pathfinder.
 * Copyright (C) 2017  Konrad Schön
 * This file is subject to the terms and conditions defined in file 'COPYING.txt', which is part of this source code package. 
 */

package attacks;

import java.util.ArrayList;

import characters.PfCharacter;
import weapons.Weapon;

// TODO: Auto-generated Javadoc
// TODO: Implement feats here, not in Attack.java
/**
 * The Class FullRound.
 */
public class FullRound {
	
	/** The attacks. */
	private ArrayList<Attack> attacks = new ArrayList<>();
	
	/** The name. */
	private String name;
	
	/** Integer describing what kind of fullround it is. 
	 * 0=with a 2h weapon(+natural),
	 * 1=only a 1h weapon
	 * 2=two 1h
	 * 3=ranged
	 * 4=only naturals
	 */
	private int fullRoundType;
	
	/**
	 * Instantiates a new full round.
	 *
	 * @param name the name
	 */
	public FullRound(String name){
		this.name=name + " mit: ";
	}
	
	public FullRound(String name, PfCharacter character, ArrayList<Weapon> usedWeapons, boolean fullRound, boolean ifTwoHandedPossibleDoTwoHanded){
		this.name=name + " mit: ";
		determineFullRoundType(usedWeapons);
		for(Weapon weapon:usedWeapons){
			if(fullRoundType == 1 && weapon.getType()==1 && ifTwoHandedPossibleDoTwoHanded) weapon.setDmgMod(1.5);
			this.addAttack(new Attack(character, weapon, fullRound));
		}
		
	}
	
	/**
	 * Adds the attack.
	 *
	 * @param attack the attack
	 */
	public void addAttack(Attack attack){
		if(!attacks.isEmpty()) name = name + ", ";
		attacks.add(attack);
		name = name+attack.getWeaponName();
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

	public int getFullRoundType() {
		return fullRoundType;
	}

	public void determineFullRoundType(ArrayList<Weapon> usedWeapons) {
		int tempType=-1;
		for(Weapon weapon:usedWeapons){
			switch(weapon.getType()){
			case(0):{
				if(tempType>0&&tempType<4){
					//TODO: return ERROR
				}else tempType=0;
				break;
			}
			case(1):{
				if(tempType==0 || tempType>=2 ){
					//TODO: return ERROR
				}
				else if(tempType==4) tempType=1;
				else if(tempType==1) tempType=2; weapon.setType(2);
				break;
			}
			case(2):{
				if(tempType==0 || tempType==2 || tempType==3){
					//TODO: return ERROR
				}
				else tempType=2;
				break;
			}
			case(3):{
				if(tempType!=-1){
					//TODO: return ERROR
				}
				else tempType=3;
				break;
			}
			case(4):
			case(5):{
				if(tempType==3){
					//TODO: return ERROR
				} else if(tempType==-1) tempType=4;
			}
			}
		}
		fullRoundType=tempType;
	}

}
