package saveLoadImplementation;

import java.io.File;

import parser.BoardParserFromFile;
import parser.CorruptedFileException;
import parser.SavedBoardPlayerLine;
import back.BoardObtainer;
import back.Game;
import back.GameListener;
import back.LoadGame;
import back.Point;

public class LoadGameFromFile<T extends Game> extends BoardParserFromFile implements BoardObtainer,LoadGame<T> {

	private Point playerLoadedPosition;
	private Integer playerLoadedExperience;
	private Integer playerLoadedHealth;
	private Integer playerLoadedMaxHealth;
	private Integer playerLoadedStrength;
	private Integer playerLoadedSteps;
	private String playerName;

	public LoadGameFromFile(File placeToLoad) {
		super(placeToLoad);
	}

	@Override
	public void parsePlayer(String line) {
		SavedBoardPlayerLine playerData = new SavedBoardPlayerLine(line,
				getBoardDimension());
		Point point = (new Point(playerData.getData(1), playerData.getData(2)))
				.add(new Point(1, 1));
		playerLoadedPosition = point;
		playerLoadedExperience = playerData.getData(3);
		playerLoadedHealth = playerData.getData(4);
		playerLoadedMaxHealth = playerData.getData(5);
		playerLoadedStrength = playerData.getData(6);
		playerLoadedSteps = playerData.getData(7);
		playerName = playerData.getPlayerName();

	}

	@Override
	public Point getPlayerPosition() {
		return playerLoadedPosition;
	}

	public Integer getPlayerLoadedHealth() {
		return playerLoadedHealth;
	}

	public Integer setPlayerLoadedMaxHealth() {
		return playerLoadedMaxHealth;
	}

	public Integer getPlayerLoadedExperience() {
		return playerLoadedExperience;
	}

	public Integer getPlayerLoadedStrength() {
		return playerLoadedStrength;
	}

	public Integer getPlayerLoadedSteps() {
		return playerLoadedSteps;
	}

	public T getGame(Class<T> gameImpClass, GameListener listener) {
		T game;
		try {
			game = gameImpClass.getConstructor(BoardObtainer.class,
					GameListener.class,String.class).newInstance(this, listener, playerName);
		} catch (Exception e) {
			throw new CorruptedFileException();
		}
		return game;
	}

	public String getPlayerName() {
		return playerName;
	}

}
