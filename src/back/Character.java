package back;

public abstract class Character {

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

	public void winFight(Character character){}

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

	public void grabLifeBonus(Integer bonusAmount) {
		if(health + bonusAmount > maxHealth){
			health = maxHealth;
		} else {
			health += bonusAmount;
		}
	}

	public void grabStrengthBonus(Integer bonusAmount) {
		strength += bonusAmount;
	}
	
	//TODO sacarlo dsps, es solo para los tests
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
}
