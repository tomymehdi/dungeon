package back;

public class DungeonGameImp implements Game {

	public static Integer LEVEL = 3;
	static Integer LIFE = 10;
	static Integer STRENGTH = 5;

	private String boardName;
	private Player player;
	private Point boardDimension;
	private Putable[][] board;
	private GameListener gameListener;
	private BoardObtainer boardObtainer;

	public DungeonGameImp(BoardObtainer boardObtainer,
			GameListener gameListener, String playerName) {
		this.boardObtainer = boardObtainer;
		this.gameListener = gameListener;
		boardName = boardObtainer.getBoardName();
		boardDimension = boardObtainer.getBoardDimension();
		board = boardObtainer.getBoard();
		PlayerData playerData = new PlayerData(boardObtainer.getPlayerName(),
				0, 0, LIFE, LIFE, STRENGTH, boardObtainer.getPlayerPosition());
		if (!(boardObtainer instanceof LoadGame)) {
			player = new Player(playerData);
		} else {
			playerData.setHealth(boardObtainer.getPlayerLoadedHealth());
			playerData.setMaxHealth(boardObtainer.getPlayerLoadedMaxHealth());
			playerData.setStrength(boardObtainer.getPlayerLoadedStrength());
			playerData.setExperience(0);
			player = new Player(playerData);
		}
	}

	public DungeonGameImp(BoardObtainer boardObtainer, GameListener gameListener) {
		this(boardObtainer, gameListener, gameListener.playerNameRequest());
	}

	@Override
	public void receiveStroke(Strokes keyPressed) {
		movePlayer((MoveTypes) keyPressed);
	}

	public void movePlayer(MoveTypes moveType) {

		Point nextPlayerPosition = player.getPosition().add(
				moveType.getDirection());

		if (board[nextPlayerPosition.x][nextPlayerPosition.y]
				.allowMovement(this)) {
			gameListener.executeWhenPlayerMoves(player.move(moveType));
			board[nextPlayerPosition.x][nextPlayerPosition.y].standOver(this);
		}
	}

	@Override
	public Player getPlayer() {
		return player;
	}

	@Override
	public void winned() {
		gameListener.executeWhenGameWinned();
	}

	@Override
	public void loosed() {
		gameListener.executeWhenGameLoosed();
	}

	public void fightEnd(Monster monster) {
		if (monster.isDead()) {
			Point point = new Point(monster.getPosition().x, monster
					.getPosition().y);
			board[point.x][point.y] = new BloodyFloor();
			gameListener.executeWhenCharacterDie(point);

		}
		if (player.isDead()) {
			Point point = new Point(player.getPosition().x, player
					.getPosition().y);
			board[point.x][point.y] = new BloodyFloor();
			gameListener.executeWhenCharacterDie(point);
			loosed();
		}
		gameListener.executeWhenFight();

	}

	@Override
	public Putable[][] getBoard() {
		return board;
	}

	@Override
	public Point getBoardDimension() {
		return boardDimension;
	}

	@Override
	public String getBoardName() {
		return boardName;
	}

	@Override
	public GameListener getGameListener() {
		return gameListener;
	}

	@Override
	public void addGameListener(GameListener d) {
		gameListener = d;
	}

	@Override
	public BoardObtainer getBoardObtainer() {
		return boardObtainer;
	}

	@Override
	public void restart() {
		board = boardObtainer.getBoard();
		PlayerData playerData = new PlayerData(boardObtainer.getPlayerName(),
				0, 0, LIFE, LIFE, STRENGTH, boardObtainer.getPlayerPosition());
		player = new Player(playerData);
	}

}
