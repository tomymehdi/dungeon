package back;

/**
 * @author tomas
 * Abstract class that extends cell. So it can ve visible or invisible in the board.
 */
public abstract class Character extends Cell {

	private String name;
	private Integer level;
	private Integer maxHealth;
	private Integer health;
	private Integer strength;
	private Point position;

	public Character(String name, Integer level, Point position) {
		this.name = name;
		this.level = level;
		this.position = position;
	}

	public void winFight(Character character) {
	}

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

	public void hited(Integer strength) {
		health -= strength;
	}

	public String getName() {
		return name;
	}

	public boolean isDead() {
		return health <= 0;
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

	public Integer getHealth() {
		return health;
	}

	public Integer getStrength() {
		return strength;
	}

	public Point getPosition() {
		return position;
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

	public void winLife(Integer bonusAmount) {
		if (health + bonusAmount > maxHealth) {
			health = maxHealth;
		} else {
			health += bonusAmount;
		}
	}

	public void grabStrengthBonus(Integer bonusAmount) {
		strength += bonusAmount;
	}

	/**
	 * Method just for tests
	 * 
	 * @param position
	 */
	public void setPosition(Point position) {
		this.position = position;
	}

	public void setMaxHealth(int maxHealth) {
		this.maxHealth = maxHealth;
	}

	public void setStrength(int strength) {
		this.strength = strength;
	}

	public void setHealth(Integer health) {
		this.health = health;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((health == null) ? 0 : health.hashCode());
		result = prime * result + ((level == null) ? 0 : level.hashCode());
		result = prime * result
				+ ((maxHealth == null) ? 0 : maxHealth.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result
				+ ((position == null) ? 0 : position.hashCode());
		result = prime * result
				+ ((strength == null) ? 0 : strength.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Character other = (Character) obj;
		if (health == null) {
			if (other.health != null)
				return false;
		} else if (!health.equals(other.health))
			return false;
		if (level == null) {
			if (other.level != null)
				return false;
		} else if (!level.equals(other.level))
			return false;
		if (maxHealth == null) {
			if (other.maxHealth != null)
				return false;
		} else if (!maxHealth.equals(other.maxHealth))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (position == null) {
			if (other.position != null)
				return false;
		} else if (!position.equals(other.position))
			return false;
		if (strength == null) {
			if (other.strength != null)
				return false;
		} else if (!strength.equals(other.strength))
			return false;
		return true;
	}

}
