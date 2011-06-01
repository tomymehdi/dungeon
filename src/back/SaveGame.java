package back;

public interface SaveGame<T extends Game> {
	public void save() throws Exception;
}
