package back;

public abstract class Bonus implements Putable {

	public abstract void giveBonus(Character character);

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

}
