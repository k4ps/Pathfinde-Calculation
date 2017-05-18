package attacks;

import java.util.ArrayList;

public class FullRound {
	private ArrayList<Attack> attacks = new ArrayList<>();
	private String name;
	
	public void FullRound(String name){
		this.name=name + " mit: ";
	}
	
	public void addAttack(Attack attack){
		attacks.add(attack);
		name = name+attack.getWeaponName()+", ";
	}
	
	public String getName(){
		return name;
	}

}
