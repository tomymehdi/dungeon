package back;

public interface LoadGame<T extends Game> {

	public T getGame(Class<T> gameImpClass, GameListener listener);

}
