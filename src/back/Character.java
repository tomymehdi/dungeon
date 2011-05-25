package back;

public abstract class Character {

	private String name;
	private Integer level;
	private Integer maxHealth;
	private Integer health;
	private Integer strength;
	private Point position;

	Character(String name, Integer level, Point position) {
		this.name = name;
		this.level = level;
		this.position = position;
	}

	public abstract void winFight(Character character);

	public void fightAnotherCharacter(Character character) {
		this.hited(character.getStrength());
		if (!this.isDead()) {
			character.hited(this.getStrength());
			if (character.isDead()) {
				this.winFight(character);
			}
		} else {
			character.winFight(this);
		}

	}

	private void hited(Integer strength) {
		setHealth(health - strength);
	}

	public String getName() {
		return name;
	}

	public boolean isDead() {
		return health <= 0;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getLevel() {
		return level;
	}

	public void increaseLevel() {
		this.level += 1;
	}

	public Integer getMaxHealth() {
		return maxHealth;
	}

	public void setMaxHealth(Integer maxHealth) {
		this.maxHealth = maxHealth;
	}

	public Integer getHealth() {
		return health;
	}

	public void setHealth(Integer health) {
		if (health > maxHealth) {
			this.health = maxHealth;
		} else {
			this.health = health;
		}
	}

	public Integer getStrength() {
		return strength;
	}

	public void setStrength(Integer strength) {
		this.strength = strength;
	}

	public Point getPosition() {
		return position;
	}

	public void setPosition(Point position) {
		this.position = position;
	}

	@Override
	public String toString() {
		String resp;
		resp = "Name=" + getName();
		resp += "Level=" + getLevel();
		resp += "MaxHealth=" + getMaxHealth();
		resp += "Health=" + getHealth();
		resp += "Strength=" + getStrength();
		resp += "Position=" + getPosition();
		return resp;
	}
}
