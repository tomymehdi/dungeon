package back;

public interface GameListener {
	
	public void executeWhenPlayerMoves(MoveTypes moveType);
	
	public void executeWhenFight();

	public void executeWhenBonusGrabed(Point pos);
	
	public void executeWhenCharacterDie(Point pos);
	
	public void executeWhenGameLoosed();
	
	public void executeWhenGameWinned();

	public String playerNameRequest();

	void executeWhenLevelUp();

}
