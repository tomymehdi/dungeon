package back;

public interface GameListener {
	
	public void executeWhenPlayerMoves();

	public void executeAtAFightEnd();

	public void executeWhenBonusGrabed();
	
	public void executeWhenPlayerDie();

	public void executeWhenGameWinned();

}
