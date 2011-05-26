package back;

public interface GameListener {
	
	public void executeWhenPlayerMoves();

	public void executeWhenBonusGrabed();
	
	public void executeWhenCharacterDie();
	
	public void executeWhenGameLoosed();
	
	public void executeWhenGameWinned();
	
}
