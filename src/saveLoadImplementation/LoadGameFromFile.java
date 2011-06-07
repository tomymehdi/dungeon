package saveLoadImplementation;

import java.io.File;

import parser.BoardLine;
import parser.BoardParserFromFile;
import parser.CorruptedFileException;
import parser.SavedBoardPlayerLine;
import back.BloodyFloor;
import back.BoardObtainer;
import back.Floor;
import back.Game;
import back.GameListener;
import back.LoadGame;
import back.Monster;
import back.Player;
import back.PlayerData;
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
		loadedLevel = playerData.getData(8);
		playerName = playerData.getPlayerName();

	}

	private void setBoardCellVisivility(Point point, int num) {
		if (num == 0) {
			getBoardElem(point).setVisible();
		} else {
			getBoardElem(point).setNotVisible();
		}
	}

	@Override
	public void parseWall(Point point, BoardLine cell) {
		if (cell.getData(3) == 2) {
			putCell(point, new BloodyFloor());
		} else if (cell.getData(3) == 1) {
			putCell(point, new Floor());
		} else {
			super.parseWall(point, cell);
		}
		setBoardCellVisivility(point, cell.getData(5));
	};

	@Override
	public void parseBonus(Point point, BoardLine cell) {
		super.parseBonus(point, cell);
		setBoardCellVisivility(point, cell.getData(4));
	}

	@Override
	public void parseMonster(Point point, BoardLine cell) {
		putCell(point.x, point.y, new Monster(point, cell.getData(3), cell
				.getData(4), Math.abs(cell.getData(5))));
		if (cell.getData(5) < 0) {
			setBoardCellVisivility(point, 0);
		} else if (cell.getData(5) > 0) {
			setBoardCellVisivility(point, 1);
		}
	}

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
					GameListener.class).newInstance(this, listener);
		} catch (Exception e) {
			e.printStackTrace();
			throw new CorruptedFileException();
		}
		return game;
	}

	@Override
	public int getPlayerLoadedLevel() {
		return loadedLevel;
	}

	@Override
	public String getPlayerName() {
		return playerName;
	}

	@Override
	public Player getLoadedPlayer() {
		PlayerData playerData = new PlayerData(playerName, loadedLevel,
				playerLoadedExperience, playerLoadedHealth,
				playerLoadedMaxHealth, playerLoadedStrength,
				playerLoadedPosition, playerLoadedSteps);
		return new Player(playerData);
	}

}
