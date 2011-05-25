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
	public void winFight(Character character) {
		// TODO
		// game.getGameListener().executeWhenPlayerDie();

	}

	@Override
	public String toString() {
		return monsterType.getName();
	}

	@Override
	public boolean allowMovement(Game game) {
		if (!this.isDead()) {
			game.getPlayer().fightAnotherCharacter(this);
			if (this.isDead()) {
				// TODO
				// game.getGameListener().executeAtAFightEnd();
				if (this.getLevel() == Game.LEVEL) {
					game.winned();
				}
			}
			return false;
		} else {
			return true;
		}
	}

	@Override
	public void standOver(Game game) {
	}

}
