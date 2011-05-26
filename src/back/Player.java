package back;

public class Player extends Character {

	private static Integer EXPERIENCECONSTANT = 5;

	private Integer experience;
	private Integer experienceToLevelUp;
	private Integer steps;

	public Player(String name, Point position, int maxHealth,
			int startingStength) {
		super(name, 1, position);
		this.experience = 0;
		this.experienceToLevelUp = EXPERIENCECONSTANT * getLevel();
		this.steps = 0;
		setMaxHealth(maxHealth);
		setStrength(startingStength);
		setHealth(getMaxHealth());
	}

	public void move(MoveTypes moveType){
		setPosition( getPosition().add(moveType.getDirection()) );
		steps++;
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
		setMaxHealth(getLevel() * Game.LIFE);
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

}
