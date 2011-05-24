package back;

public abstract class Character {
	String name;
	Integer level;
	Integer life;
	Integer strength;

	public Character(String name, Integer initialLife, Integer initialStrength) {
		this.name = name;
		this.life = initialLife;
		this.strength = initialStrength;

	}

}
