package back;


public class DungeonGame implements Game {

	public static Integer LEVEL = 3;
	static Integer LIFE = 10;
	static Integer STRENGTH = 5;

	private String boardName;
	private Player player;
	private Point boardDimension;
	private Putable[][] board;
	private GameListener gameListener;
	private BoardObtainer boardObtainer;

	public DungeonGame(BoardObtainer boardObtainer, GameListener gameListener, String playerName) {
		this.boardObtainer = boardObtainer;
		this.gameListener = gameListener;
		boardName = boardObtainer.getBoardName();
		boardDimension = boardObtainer.getBoardDimension();
		board = boardObtainer.getBoard();
		player = new Player(playerName,
				boardObtainer.getPlayerPosition(), LIFE, STRENGTH);
	}
	
	public DungeonGame(BoardObtainer boardObtainer, GameListener gameListener) {
		this(boardObtainer,gameListener,gameListener.playerNameRequest());
	}

	public void receiveStroke(MoveTypes keyPressed) {
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

	public GameListener getGameListener() {
		return gameListener;
	}

	public void addGameListener(GameListener d) {
		gameListener = d;
	}

	@Override
	public BoardObtainer getBoardObtainer() {
		return boardObtainer;
	}

	public void restart() {
		board = boardObtainer.getBoard();
		player = new Player(player.getName(),
				boardObtainer.getPlayerPosition(), LIFE, STRENGTH);
	}

}
