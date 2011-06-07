package back;

public class PlayerData {

	private String name;
	private int level;
	private int experience;
	private int maxHealth;
	private int health;
	private int strength;
	private Point position;
	private int steps;

	public PlayerData(String name, int level, int experience, int health,
			int maxHealth, int strength, Point position, int steps) {
		this.level = level;
		this.name = name;
		this.experience = experience;
		this.health = health;
		this.maxHealth = maxHealth;
		this.strength = strength;
		this.position = position;
		this.steps = steps;
		
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

	public int getLevel() {
		return level;
	}

	public int getSteps() {
		return steps;
	}

}
