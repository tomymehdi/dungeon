package back;

public class Bonus extends Cell implements Putable {

	private BonusTypes bonusType;

	public Bonus(Point position, int numberBonusType, int bonusAmount) {
		bonusType = BonusTypes.getBonusType(numberBonusType);
		bonusType.setBonusAmount(bonusAmount);
	}

	public void giveBonus(Character character) {
		bonusType.giveBonus(character);
	}

	@Override
	public boolean allowMovement(DungeonGameImp game) {
		return true;
	}

	public void standOver(DungeonGameImp game) {
		giveBonus(game.getPlayer());
		Point point = new Point(game.getPlayer().getPosition().x, game
				.getPlayer().getPosition().y);

		Floor f = new Floor();
		f.setVisible();
		game.getBoard()[point.x][point.y] = f;

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
