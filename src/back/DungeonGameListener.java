package back;

public interface DungeonGameListener {
	
	public void executeWhenPlayerMoves();

	public void executeWhenBonusGrabed();
	
	public void executeWhenCharacterDie();
	
	public void executeWhenGameLoosed();
	
	public void executeWhenGameWinned();
	
	public String playerNameRequest();
	
}
