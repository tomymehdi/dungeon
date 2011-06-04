package back;

public class Monster extends Character implements Putable {

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((monsterType == null) ? 0 : monsterType.hashCode());
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
		Monster other = (Monster) obj;
		if (monsterType == null) {
			if (other.monsterType != null)
				return false;
		} else if (!monsterType.equals(other.monsterType))
			return false;
		return true;
	}

	private MonsterTypes monsterType;

	public Monster(Point position, int numberMonsterType, int level) {
		this(position, numberMonsterType, level, MonsterTypes.getMonsterType(
				numberMonsterType).getMaxLife(level));
	}

	public Monster(Point position, int numberMonsterType, int level, int health) {
		super(MonsterTypes.getMonsterType(numberMonsterType).getName(), level,
				position);
		monsterType = MonsterTypes.getMonsterType(numberMonsterType);
		setMaxHealth(monsterType.getMaxLife(level));
		setStrength(monsterType.getStrength(level));
		setHealth(health);
	}

	public MonsterTypes getMonsterType() {
		return monsterType;
	}

	@Override
	public String toString() {
		return monsterType.getName();
	}

	@Override
	public boolean allowMovement(DungeonGameImp game) {
		game.getPlayer().fightAnotherCharacter(this);
		game.fightEnd(this);
		if (this.isDead()) {
			if (this.getLevel() == DungeonGameImp.LEVEL) {
				game.winned();
			}
		}
		return false;
	}

	@Override
	public void standOver(DungeonGameImp game) {
	}

}
