package back;

public class Bonus implements Putable {

	private BonusTypes bonusType;

	public Bonus(Point position, int numberBonusType, int bonusAmount) {
		bonusType = BonusTypes.getBonusType(numberBonusType);
		bonusType.setBonusAmount(bonusAmount);
	}

	public void giveBonus(Character character) {
		bonusType.giveBonus(character);
	}

	@Override
	public boolean allowMovement(DungeonGame game) {
		giveBonus(game.getPlayer());
		return true;
	}

	public void standOver(DungeonGame game) {

		Point point = new Point(game.getPlayer().getPosition().x, game
				.getPlayer().getPosition().y);

		game.getBoard()[point.x][point.y] = new Floor();

		game.getGameListener().executeWhenBonusGrabed(
				new Point(point.x, point.y));
	}

	public BonusTypes getBonusType() {
		return bonusType;
	}

	public int getAmountBonus() {
		return bonusType.getBonusAmount();
	}
	
	@Override
	public String toString() {
		return "Bonus";
	}

}
