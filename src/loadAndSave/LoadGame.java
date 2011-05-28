package loadAndSave;

import java.io.File;

import parser.BoardParser;
import parser.SavedBoardPlayerLine;
import back.DungeonGame;
import back.Point;

public class LoadGame extends BoardParser {

	private Point playerLoadedPosition;
	private Integer playerLoadedExperience;
	private Integer playerLoadedHealth;
	private Integer playerLoadedMaxHealth;
	private Integer playerLoadedStrength;
	private Integer playerLoadedSteps;
	private String playerName;

	public LoadGame(File placeToLoad) {
		super(placeToLoad);
	}

	@Override
	public void parsePlayer(String line) {
		SavedBoardPlayerLine playerData = new SavedBoardPlayerLine(line,
				boardDimension);
		Point point = (new Point(playerData.getData(1), playerData.getData(2)))
				.add(new Point(1, 1));
		playerPosition = point;
		point = (new Point(playerData.getData(3), playerData.getData(4)))
				.add(new Point(1, 1));
		playerLoadedPosition = point;
		playerLoadedExperience = playerData.getData(5);
		playerLoadedHealth = playerData.getData(6);
		playerLoadedMaxHealth = playerData.getData(7);
		playerLoadedStrength = playerData.getData(8);
		playerLoadedSteps = playerData.getData(9);
		playerName = playerData.getPlayerName();

	}

	public Point getPlayerLoadedPosition() {
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

	public DungeonGame getGame() {
		return new DungeonGame(this);
	}

	public String getPlayerName() {
		return playerName;
	}

}
