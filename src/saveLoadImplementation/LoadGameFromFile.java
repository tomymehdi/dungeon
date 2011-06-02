package saveLoadImplementation;

import java.io.File;

import parser.BoardLine;
import parser.BoardParserFromFile;
import parser.CorruptedFileException;
import parser.SavedBoardPlayerLine;
import back.BloodyFloor;
import back.BoardObtainer;
import back.Game;
import back.GameListener;
import back.LoadGame;
import back.Point;

public class LoadGameFromFile<T extends Game> extends BoardParserFromFile
		implements LoadGame<T> {

	private Point playerLoadedPosition;
	private Integer loadedLevel;
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
	public void parseWall(Point point, BoardLine cell) {
		if (cell.getData(5) == 1) {
			putCell(point, new BloodyFloor());
		} else {
			super.parseWall(point, cell);
		}

	};

	@Override
	public Point getPlayerPosition() {
		return playerLoadedPosition;
	}

	@Override
	public Integer getPlayerLoadedHealth() {
		return playerLoadedHealth;
	}

	@Override
	public Integer getPlayerLoadedMaxHealth() {
		return playerLoadedMaxHealth;
	}

	@Override
	public Integer getPlayerLoadedExperience() {
		return playerLoadedExperience;
	}

	@Override
	public Integer getPlayerLoadedStrength() {
		return playerLoadedStrength;
	}

	@Override
	public Integer getPlayerLoadedSteps() {
		return playerLoadedSteps;
	}

	public T getGame(Class<T> gameImpClass, GameListener listener) {
		T game;
		try {
			game = gameImpClass.getConstructor(BoardObtainer.class,
					GameListener.class, String.class).newInstance(this,
					listener, playerName);
		} catch (Exception e) {
			throw new CorruptedFileException();
		}
		return game;
	}

	@Override
	public String getPlayerName() {
		return playerName;
	}
	
	@Override
	public int getPlayerLoadedLevel() {
		return loadedLevel;
	}

}
