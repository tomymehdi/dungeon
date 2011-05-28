package back;

public interface DungeonGameListener {
	
	public void executeWhenPlayerMoves(MoveTypes moveType);

	public void executeWhenBonusGrabed(Point pos);
	
	public void executeWhenCharacterDie(Point pos);
	
	public void executeWhenGameLoosed();
	
	public void executeWhenGameWinned();
	
	public String playerNameRequest();
	
}
