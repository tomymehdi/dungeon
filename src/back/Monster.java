package back;

public class Monster extends Character implements Putable {

	private MonsterTypes monsterType;

	public Monster(Point position, int numberMonsterType, int level) {
		super(MonsterTypes.getMonsterType(numberMonsterType).getName(), level,
				position);
		monsterType = MonsterTypes.getMonsterType(numberMonsterType);
		setMaxHealth(monsterType.getMaxLife(level));
		setStrength(monsterType.getStrength(level));
		setHealth(getMaxHealth());
	}

	public MonsterTypes getMonsterType() {
		return monsterType;
	}

	@Override
	public String toString() {
		return monsterType.getName();
	}

	@Override
	public boolean allowMovement(DungeonGame game) {
		game.getPlayer().fightAnotherCharacter(this);
		game.fightEnd(this);
		if (this.isDead()) {
			if (this.getLevel() == DungeonGame.LEVEL) {
				game.winned();
			}
		}
		return false;
	}

	@Override
	public void standOver(DungeonGame game) {}

}
