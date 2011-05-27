package back;

import java.io.File;

import loadAndSave.LoadGame;
import parser.BoardParser;

public class DungeonGame {

	public static Integer LEVEL = 3;
	static Integer LIFE = 10;
	static Integer STRENGTH = 5;

	private String boardPath;
	private String boardName;
	private Player player;
	private Point startingPlayerPosition;
	private Point boardDimension;
	private Putable[][] board;
	private DungeonGameListener gameListener;

	public DungeonGame(DungeonGameListener gameListener, String boardPath) {
		this.boardPath = boardPath;
		this.gameListener = gameListener;
		BoardParser boardParser = new BoardParser(new File(boardPath));
		boardName = boardParser.getBoardName();
		startingPlayerPosition = boardParser.getPlayerPosition();
		boardDimension = boardParser.getBoardDimension();
		board = boardParser.getBoard();
		player = new Player(gameListener.playerNameRequest(), boardParser
				.getPlayerPosition(), LIFE, STRENGTH);
	}

	public DungeonGame(DungeonGameListener gameListener, LoadGame loadGame) {
		boardName = loadGame.getBoardName();
		startingPlayerPosition = loadGame.getPlayerPosition();
		boardDimension = loadGame.getBoardDimension();
		board = loadGame.getBoard();
		player = new Player(gameListener.playerNameRequest(), loadGame
				.getPlayerLoadedPosition(), LIFE, STRENGTH);
		player.setExperience(loadGame.getPlayerLoadedExperience());
		player.setHealth(loadGame.getPlayerLoadedHealth());
		player.setMaxHealth(loadGame.setPlayerLoadedMaxHealth());
		player.setStrength(loadGame.getPlayerLoadedStrength());
		player.setSteps(loadGame.getPlayerLoadedSteps());
	}

	public void receibeStroke(MoveTypes keyPressed) {
		movePlayer(keyPressed);
	}

	public void movePlayer(MoveTypes moveType) {

		Point nextPlayerPosition = player.getPosition().add(
				moveType.getDirection());

		if (board[nextPlayerPosition.x][nextPlayerPosition.y]
				.allowMovement(this)) {
			player.move(moveType);
			gameListener.executeWhenPlayerMoves();
		}
	}

	public void addListener(DungeonGameListener gameListener) {
		this.gameListener = gameListener;
	}

	public Player getPlayer() {
		return player;
	}

	public DungeonGameListener getGameListener() {
		return gameListener;
	}

	public void restartGame() {
		BoardParser boardParser = new BoardParser(new File(boardPath));
		board = boardParser.getBoard();
		player = new Player(player.getName(), boardParser.getPlayerPosition(),
				LIFE, STRENGTH);
	}

	public void winned() {
		gameListener.executeWhenGameWinned();
	}

	public void loosed() {

		gameListener.executeWhenGameLoosed();
	}

	public void fightEnd(Monster monster) {
		if (monster.isDead()) {
			board[monster.getPosition().x][monster.getPosition().y] = new BloodyFloor();
			gameListener.executeWhenCharacterDie();
		}
		if (player.isDead()) {
			board[player.getPosition().x][player.getPosition().y] = new BloodyFloor();
			gameListener.executeWhenCharacterDie();
			loosed();
		}

	}

	public Point getStartingPlayerPosition() {
		return startingPlayerPosition;
	}

	public Putable[][] getBoard() {
		return board;
	}

	public Point getBoardDimension() {
		return boardDimension;
	}

	public String getBoardName() {
		return boardName;
	}

	public void addGameListener(DungeonGameListener d) {
		gameListener = d;
	}

}
