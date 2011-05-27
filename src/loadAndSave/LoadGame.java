package loadAndSave;

import java.io.File;

import parser.BoardParser;
import parser.SavedBoardPlayerLine;
import back.Point;

public class LoadGame extends BoardParser {
	
	protected Point playerLoadedPosition;
	protected Integer playerLoadedExperience;
	protected Integer playerLoadedHealth;
	protected Integer playerLoadedMaxHealth;
	protected Integer playerLoadedStrength;
	protected Integer playerLoadedSteps;
	
	public LoadGame(File placeToLoad) {
		super(placeToLoad);
	}
	
	@Override
	public void parsePlayer(String line) {
		SavedBoardPlayerLine playerData = new SavedBoardPlayerLine(line, boardDimension);
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
	
	public Integer getPlayerLoadedSteps(){
		return playerLoadedSteps;
	}

}
