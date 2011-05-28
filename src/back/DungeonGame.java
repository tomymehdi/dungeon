package back;

import parser.CorruptedFileException;

public class DungeonGame {

	public static Integer LEVEL = 3;
	static Integer LIFE = 10;
	static Integer STRENGTH = 5;

	private String boardName;
	private Player player;
	private Point boardDimension;
	private Putable[][] board;
	private DungeonGameListener gameListener;
	private BoardObtainer boardObtainer;

	public DungeonGame(BoardObtainer boardObtainer) {
		this.boardObtainer = boardObtainer;
		boardName = boardObtainer.getBoardName();
		boardDimension = boardObtainer.getBoardDimension();
		board = boardObtainer.getBoard();
		player = new Player(gameListener.playerNameRequest(),
				boardObtainer.getPlayerPosition(), LIFE, STRENGTH);
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
		try {
			boardObtainer.obtainBoard();
		} catch (Exception e) {
			throw new CorruptedFileException();
		}
		board = boardObtainer.getBoard();
		player = new Player(player.getName(), boardObtainer.getPlayerPosition(),
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
			Point point = new Point(player.getPosition().x,
					player.getPosition().y);
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
