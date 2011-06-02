package back;

public class PlayerData {

	String name;
	int level;
	int experience;
	int maxHealth;
	int health;
	int strength;
	Point position;

	public PlayerData(String name, int level, int experience, int health,
			int maxHealth, int strength, Point position) {
		this.name = name;
		this.level = level;
		this.experience = experience;
		this.health = health;
		this.maxHealth = maxHealth;
		this.strength = strength;
		this.position = position;
	}

	public int getExperience() {
		return experience;
	}

	public void setExperience(int experience) {
		this.experience = experience;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public String getName() {
		return name;
	}

	public int getMaxHealth() {
		return maxHealth;
	}

	public Point getPosition() {
		return position;
	}

	public int getStrength() {
		return strength;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setMaxHealth(int maxHealth) {
		this.maxHealth = maxHealth;
	}

	public void setPosition(Point position) {
		this.position = position;
	}

	public void setStrength(int strength) {
		this.strength = strength;
	}

}
