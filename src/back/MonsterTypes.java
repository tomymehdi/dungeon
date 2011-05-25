package back;

public enum MonsterTypes {
	
	
	GOLEM("Golem", new Algoritms() {

		@Override
		public Integer lifeAlgoritm(int level) {
			return (int) Math.floor((((level + 3) * (level + 3)) - 10) * GOLEMLIFE);
		}

		@Override
		public Integer strengthAlgoritm(int level) {
			return (int) Math.floor(((level * level) + 5 * level) * 0.5 * GOLEMSTRENGTH);
		}

	}), DRAGON("Dragon", new Algoritms() {

		@Override
		public Integer lifeAlgoritm(int level) {
			return (int) Math.floor((((level + 3) * (level + 3)) - 10) * DRAGONLIFE);
		}

		@Override
		public Integer strengthAlgoritm(int level) {
			return (int) Math.floor(((level * level) + 5 * level) * 0.5 * DRAGONSTRENGTH);
		}
	}), SNAKE("Snake", new Algoritms() {

		@Override
		public Integer lifeAlgoritm(int level) {
			return (int) Math.floor((((level + 3) * (level + 3)) - 10) * SNAKELIFE);
		}

		@Override
		public Integer strengthAlgoritm(int level) {
			return (int) Math.floor(((level * level) + 5 * level) * 0.5 * SNAKESTRENGTH);
		}
		
	});

	private static double GOLEMLIFE = 1;
	private static double GOLEMSTRENGTH = 0.7;
	private static double DRAGONLIFE = 1.35;
	private static double DRAGONSTRENGTH = 1;
	private static double SNAKELIFE = 1;
	private static double SNAKESTRENGTH = 1;
	
	private String name;
	private Algoritms lifeStrengthAlgoritms;

	private MonsterTypes(String name, Algoritms lifeStrengthAlgoritms) {
		this.name = name;
		this.lifeStrengthAlgoritms = lifeStrengthAlgoritms;
	}

	public Integer getMaxLife(int level) {
		return lifeStrengthAlgoritms.lifeAlgoritm(level);
	}

	public Integer getStrength(int level) {
		return lifeStrengthAlgoritms.strengthAlgoritm(level);
	}

	public static MonsterTypes getMonsterType(int data) {
		switch (data) {
		case (1):
			return MonsterTypes.GOLEM;
		case (2):
			return MonsterTypes.DRAGON;
		default:
			return MonsterTypes.SNAKE;
		}
	}

	public String getName() {
		return name;
	}
}
