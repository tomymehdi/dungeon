package back;

public abstract class Bonus implements Putable {

	public abstract void giveBonus(Character character);

	@Override
	public boolean allowMovement(Game game) {
		return true;
	}

	@Override
	public void standOver(Game game) {
		giveBonus(game.getPlayer());
		game.getBoardParser().getBoard()[game.getPlayer().getPosition().x][game
				.getPlayer().getPosition().y] = new Floor();
		// TODO
		// game.getGameListener().executeWhenBonusGrabed();
	}

}
