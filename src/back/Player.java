package back;

public class Player extends Character {

	private static Integer EXPERIENCECONSTANT = 5;

	private Integer experience;
	private Integer experienceToLevelUp;
	private Integer steps = 0;

	public Player(PlayerData playerData) {
		super(playerData.getName(), playerData.getLevel(), playerData.getPosition());
		this.experienceToLevelUp = EXPERIENCECONSTANT * getLevel();
		this.experience = playerData.getExperience();
		setMaxHealth(playerData.getMaxHealth());
		setHealth(playerData.getHealth());
		setStrength(playerData.getStrength());
		
	}

	public MoveTypes move(MoveTypes moveType) {
		setPosition(getPosition().add(moveType.getDirection()));
		steps++;
		return moveType;
	}

	public void winExperience(Integer experience) {
		if ((this.experience + experience) >= experienceToLevelUp) {
			levelUp();
		} else {
			this.experience += experience;
		}
	}

	private void levelUp() {
		increaseLevel();
		this.experience = 0;
		this.experienceToLevelUp = EXPERIENCECONSTANT * getLevel();
		setMaxHealth(getLevel() * DungeonGameImp.LIFE);
		setStrength(getStrength() + DungeonGameImp.STRENGTH);
	}

	public Integer getExperience() {
		return experience;
	}

	public void winFight(Character character) {
		winExperience(character.getLevel());
	}

	@Override
	public String toString() {
		String resp;
		resp = super.toString();
		resp += "Exp=" + experience;
		resp += "ExpNeeded=" + experienceToLevelUp;
		return resp;
	}

	public Integer getSteps() {
		return steps;
	}

	public Integer getExperienceToLevelUp() {
		return experienceToLevelUp;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((experience == null) ? 0 : experience.hashCode());
		result = prime
				* result
				+ ((experienceToLevelUp == null) ? 0 : experienceToLevelUp
						.hashCode());
		result = prime * result + ((steps == null) ? 0 : steps.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Player other = (Player) obj;
		if (experience == null) {
			if (other.experience != null)
				return false;
		} else if (!experience.equals(other.experience))
			return false;
		if (experienceToLevelUp == null) {
			if (other.experienceToLevelUp != null)
				return false;
		} else if (!experienceToLevelUp.equals(other.experienceToLevelUp))
			return false;
		if (steps == null) {
			if (other.steps != null)
				return false;
		} else if (!steps.equals(other.steps))
			return false;
		return true;
	}

}
