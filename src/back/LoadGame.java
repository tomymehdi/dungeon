package back;

public interface LoadGame<T extends Game> {

	public T getGame(Class<T> gameImpClass, GameListener listener);
	
	public Integer getPlayerLoadedSteps();
	
	Integer getPlayerLoadedExperience();

	Integer getPlayerLoadedStrength();
	
	public int getPlayerLoadedLevel();
	
	public Integer getPlayerLoadedHealth();
	
	public Integer getPlayerLoadedMaxHealth();
	
	public String getPlayerName();

}
