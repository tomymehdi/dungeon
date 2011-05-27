package back;

public abstract class Bonus implements Putable {

	public abstract void giveBonus(Character character);

	@Override
	public boolean allowMovement(Game game) {
		giveBonus(game.getPlayer());
		game.getBoard()[game.getPlayer().getPosition().x][game
				.getPlayer().getPosition().y] = new Floor();
		// TODO
		// game.getGameListener().executeWhenBonusGrabed();
		return true;
	}

}
