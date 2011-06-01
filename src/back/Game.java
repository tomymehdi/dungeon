package back;

public interface Game {
	
	public void winned();

	public void loosed();
	
	public Player getPlayer();
	
	public Putable[][] getBoard();
	
	public Point getBoardDimension();
	
	public String getBoardName();
	
	public GameListener getGameListener();
	
	public void addGameListener(GameListener d);

	public BoardObtainer getBoardObtainer();
	
	

}
