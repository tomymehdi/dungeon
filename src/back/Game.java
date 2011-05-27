package back;

import java.io.File;

import loadAndSave.LoadGame;
import loadAndSave.SaveGame;
import parser.BoardParser;

public class Game {

	public static Integer LEVEL = 3;
	static Integer LIFE = 10;
	static Integer STRENGTH = 5;

	private String boardPath;
	private String boardName;
	private Player player;
	private Point startingPlayerPosition;
	private Point boardDimension;
	private Putable[][] board;
	private GameListener gameListener;
	// private NameListener nameListener;

	public Game(String boardPath) {
		this.boardPath = boardPath;
		BoardParser boardParser = new BoardParser(new File(boardPath));
		boardName = boardParser.getBoardName();
		startingPlayerPosition = boardParser.getPlayerPosition();
		boardDimension = boardParser.getBoardDimension();
		board = boardParser.getBoard();
		// TODO
		player = new Player("Tomas"/* nameListener.nameRequest() */,
				boardParser.getPlayerPosition(), LIFE, STRENGTH);
	}

	public Game(LoadGame loadgame) {
		// TODO HACER
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
			// TODO
			// gameListener.executeWhenPlayerMoves();
			board[player.getPosition().x][player.getPosition().y]
					.standOver(this);
		}
	}

	public void addListener(GameListener gameListener) {
		this.gameListener = gameListener;
	}

	public Player getPlayer() {
		return player;
	}

	public GameListener getGameListener() {
		return gameListener;
	}

	public void resetGame() {
		BoardParser boardParser = new BoardParser(new File(boardPath));
		board = boardParser.getBoard();
		player = new Player(player.getName(), boardParser.getPlayerPosition(),
				LIFE, STRENGTH);
	}

	public void saveGame() {
		new SaveGame(this);
	}

	public void saveGame(File placeToSave) {
		new SaveGame(this, placeToSave);
	}

	public void loadGame(File placeToLoad) {
		LoadGame loadGame = new LoadGame(placeToLoad);
		boardName = loadGame.getBoardName();
		startingPlayerPosition = loadGame.getPlayerPosition();
		boardDimension = loadGame.getBoardDimension();
		board = loadGame.getBoard();
		// TODO
		player = new Player("Tomas"/* nameListener.nameRequest() */,
				loadGame.getPlayerLoadedPosition(), LIFE, STRENGTH);
		player.setExperience(loadGame.getPlayerLoadedExperience());
		player.setHealth(loadGame.getPlayerLoadedHealth());
		player.setMaxHealth(loadGame.setPlayerLoadedMaxHealth());
		player.setStrength(loadGame.getPlayerLoadedStrength());
		player.setSteps(loadGame.getPlayerLoadedSteps());
		
	}

	public void winned() {
		// TODO
		// gameListener.executeWhenGameWinned();
	}

	public void loosed() {
		// TODO
		// gameListener.executeWhenGameLoosed();
	}

	public void fightEnd(Monster monster) {
		if (monster.isDead()) {
			board[monster.getPosition().x][monster.getPosition().y] = new BloodyFloor();
			// TODO
			// gameListener.executeWhenCharacterDie();
		}
		if (player.isDead()) {
			board[player.getPosition().x][player.getPosition().y] = new BloodyFloor();
			// TODO
			// gameListener.executeWhenCharacterDie();
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

}
