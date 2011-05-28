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
	private Point boardDimension;
	private Putable[][] board;
	private DungeonGameListener gameListener;

	public DungeonGame(String boardPath) {
		this.boardPath = boardPath;
		BoardParser boardParser = new BoardParser(new File(boardPath));
		boardName = boardParser.getBoardName();
		boardDimension = boardParser.getBoardDimension();
		board = boardParser.getBoard();
		player = new Player(gameListener.playerNameRequest(),
				boardParser.getPlayerPosition(), LIFE, STRENGTH);
	}

	public DungeonGame(LoadGame loadGame) {
		boardName = loadGame.getBoardName();
		boardDimension = loadGame.getBoardDimension();
		board = loadGame.getBoard();
		player = new Player(loadGame.getPlayerName(),
				loadGame.getPlayerLoadedPosition(), LIFE, STRENGTH);
		player.setExperience(loadGame.getPlayerLoadedExperience());
		player.setHealth(loadGame.getPlayerLoadedHealth());
		player.setMaxHealth(loadGame.setPlayerLoadedMaxHealth());
		player.setStrength(loadGame.getPlayerLoadedStrength());
		player.setSteps(loadGame.getPlayerLoadedSteps());
		player.setName(loadGame.getPlayerName());
	}

	public void receibeStroke(MoveTypes keyPressed) {
		movePlayer(keyPressed);
	}

	public void movePlayer(MoveTypes moveType) {

		Point nextPlayerPosition = player.getPosition().add(
				moveType.getDirection());

		if (board[nextPlayerPosition.x][nextPlayerPosition.y]
				.allowMovement(this)) {
			board[nextPlayerPosition.x][nextPlayerPosition.y].standOver(this);
			gameListener.executeWhenPlayerMoves(player.move(moveType));
		}
	}

	public Player getPlayer() {
		return player;
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
			Point point = new Point(monster.getPosition().x,
					monster.getPosition().y);
			board[point.x][point.y] = new BloodyFloor();
			gameListener.executeWhenCharacterDie(point);
		}
		if (player.isDead()) {
			Point point = new Point(player.getPosition().x,player.getPosition().y);
			board[point.x][point.y] = new BloodyFloor();
			gameListener.executeWhenCharacterDie(point);
			loosed();
		}

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

	public DungeonGameListener getGameListener() {
		return gameListener;
	}

	public void addGameListener(DungeonGameListener d) {
		gameListener = d;
	}

}
