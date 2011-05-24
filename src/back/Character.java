package back;

public abstract class Character {
	String name;
	Integer level;
	Integer life;
	Integer strength;
	Integer experience;

	public Character(String name, Integer initialLife, Integer initialStrength) {
		this.name = name;
		this.level = 1;
		this.life = initialLife;
		this.strength = initialStrength;
		this.experience = 0;

	}

}
