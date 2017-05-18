package calculations;

import attacks.Attack;
import weapons.Weapon;

public class Calculation {
	static private Attack attack;
	static private Weapon weapon;
	static int ac;

	public Calculation(Attack attack, int ac){
		this.attack=attack;
		this.weapon=attack.getWeapon();
		this.ac=ac;
	}
	
	public static double calcDPR(Attack attack, int ac){
		Calculation calc = new Calculation(attack, ac);
		double[] attackModifiers = attack.getAttackModi();
		double[] damageModifiers = attack.getDamageModi();
		double hitchance ;
		double critchance=weapon.getCritRange()/20.00;
		double averageDiceDamage=calcAverageDamage(weapon);
		double precisionDamage=weapon.getPrecisionDamage()+attack.getPrecisionDamage;
		int critmultiplier=weapon.getCritMultiplier();
		double bonusCritDamage=attack.getBonusCritDamage();
		double dpr=0;
		
		for(int i=0; i<attackModifiers.length;i++){
			hitchance=(20.00-(ac-attackModifiers[i]-1))/20.00;
			double averageDamage = averageDiceDamage+damageModifiers[i];
			dpr += hitchance*(averageDamage+precisionDamage)+critchance*hitchance*(averageDamage*critmultiplier+bonusCritDamage);
		}
		
		return dpr;
	}
	

}
