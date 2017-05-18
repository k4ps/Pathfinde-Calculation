package attacks;

import weapons.*;

public class Attack {
	private double[] attackModi;
	private double[] damageModi;
	private Weapon weapon;
	private String weaponName;

	public Attack(int hitStatMod, int damageStatMod, int bab, Weapon weapon, boolean fullRound) {
		int arraySize = determineArraySize(bab);
		this.weapon=weapon;
		double hitBonus = bab + hitStatMod + weapon.getHitModificator();
		double damageBonus = damageStatMod * weapon.getDamageMultiplier();
		weaponName=weapon.getName();

		if (fullRound)
			initaliseFullRound(arraySize, hitBonus, damageBonus);
		else{
			initaliseSingleAttack(hitBonus, damageBonus);
		}
	}

	private void initaliseSingleAttack(double hitBonus, double damageBonus) {
		attackModi = new double[]{hitBonus};
		damageModi = new double[]{damageBonus};
		
		
	}

	private void initaliseFullRound(int arraySize, double hitBonus, double damageBonus) {
		attackModi = new double[arraySize];
		damageModi = new double[arraySize];

		for (int i = 0; i < arraySize - 1; i++) {
			attackModi[i] = hitBonus - 5 * i;
			damageModi[i] = damageBonus;
		}
		
	}

	public void addStaticAttackModi(double staticAttackBonus) {
		for (int i = 0; i < attackModi.length; i++) {
			attackModi[i] += staticAttackBonus;
		}
	}

	public void addStaticDamageModi(double staticDamageBonus) {
		for (int i = 0; i < damageModi.length; i++) {
			damageModi[i] += staticDamageBonus;
		}
	}
	
	public void addFullBabAttack(){
		double[] tempAttack = attackModi;
		double[] tempDamage = damageModi;
		attackModi=new double[tempAttack.length+1];
		damageModi=new double[tempDamage.length+1];
		
		attackModi[0]=tempAttack[0];
		damageModi[0]=tempDamage[0];
		
		for(int i=1; i<attackModi.length; i++){
			attackModi[i]=tempAttack[i-1];
			damageModi[i]=tempDamage[i-1];
		}
		
	}

	public Weapon getWeapon() {
		return weapon;
	}

	public double[] getAttackModi() {
		return attackModi;
	}

	public double[] getDamageModi() {
		return damageModi;
	}

	private int determineArraySize(int bab) {
		if (bab < 5)
			return 1;
		if (bab >= 5 && bab < 10)
			return 2;
		if (bab >= 10 && bab < 15)
			return 3;
		return 4;
	}

	public String getWeaponName() {
		return weaponName;
	}

}
