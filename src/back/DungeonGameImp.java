package back;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author tomas Back end most important class. It contents all the data to play
 *         a Dungeon Game. This class implements Game.
 */
public class DungeonGameImp implements Game {

	final static Integer LEVEL = 3;
	final static Integer LIFE = 10;
	final static Integer STRENGTH = 5;

	private String boardName;
	private Player player;
	private Point boardDimension;
	private Putable[][] board;
	private GameListener gameListener;
	private BoardObtainer boardObtainer;

	public DungeonGameImp(BoardObtainer boardObtainer, GameListener gameListener) {
		this.boardObtainer = boardObtainer;
		this.gameListener = gameListener;
		boardName = boardObtainer.getBoardName();
		boardDimension = boardObtainer.getBoardDimension();
		board = boardObtainer.getBoard();
		setPlayer();
		firstDiscoveredCells();
	}

	private void setPlayer() {
		if (!(boardObtainer instanceof LoadGame)) {
			PlayerData playerData = new PlayerData(gameListener
					.playerNameRequest(), 1, 0, LIFE, LIFE, STRENGTH,
					boardObtainer.getPlayerPosition(), boardObtainer
							.getPlayerSteps());
			player = new Player(playerData);
		} else {
			player = ((LoadGame)boardObtainer).getLoadedPlayer();
		}

	}

	private void firstDiscoveredCells() {
		Point p = player.getPosition();

		board[p.x][p.y].setVisible();

		board[p.x + 1][p.y - 1].setVisible();
		board[p.x + 1][p.y].setVisible();
		board[p.x + 1][p.y + 1].setVisible();

		board[p.x][p.y - 1].setVisible();
		board[p.x][p.y].setVisible();
		board[p.x][p.y + 1].setVisible();

		board[p.x - 1][p.y - 1].setVisible();
		board[p.x - 1][p.y].setVisible();
		board[p.x - 1][p.y + 1].setVisible();
	}

	/**
	 * @see back.Game#receiveMoveStroke(back.MoveTypes) Is't allow the game to
	 *      receive a Stroke. In this case a MoveTypes stroke. Before this the
	 *      player moves.
	 **/
	@Override
	public void receiveMoveStroke(MoveTypes moveType) {
		Point nextPlayerPosition = player.getPosition().add(
				moveType.getDirection());
		int playerLevelBeforeFight = player.getLevel();
		if (board[nextPlayerPosition.x][nextPlayerPosition.y]
				.allowMovement(this)) {
			MoveTypes moveMade = player.move(moveType);
			dicoverBoard(nextPlayerPosition, moveType);
			gameListener.executeWhenPlayerMoves(moveMade);
			board[nextPlayerPosition.x][nextPlayerPosition.y].standOver(this);
		}
		if (player.getLevel() != playerLevelBeforeFight) {
			gameListener.executeWhenLevelUp();
		}
	}

	/**
	 * When player moves exist the possibility of discover undiscovered board
	 * parts. When this happen the game have to give life to characters on the
	 * parts of the board already discovered. This amount is equals of the
	 * character level.
	 */
	private void dicoverBoard(Point pos, MoveTypes dir) {
		int countDiscover = 0;
		List<Point> points = new ArrayList<Point>();
		points.add(pos.add(dir.getDirection()));
		if (dir == MoveTypes.LEFT || dir == MoveTypes.RIGHT) {
			points.add(pos.add(1, 0).add(dir.getDirection()));
			points.add(pos.sub(1, 0).add(dir.getDirection()));
		} else {
			points.add(pos.add(0, 1).add(dir.getDirection()));
			points.add(pos.sub(0, 1).add(dir.getDirection()));
		}

		for (Point poo : points) {
			if (!board[poo.x][poo.y].isVisible()) {
				countDiscover++;
				board[poo.x][poo.y].setVisible();
			}
		}

		if (countDiscover > 0) {
			player.winLife(countDiscover * player.getLevel());
			for (int i = 1; i < boardDimension.x - 1; i++) {
				for (int j = 1; j < boardDimension.y - 1; j++) {
					if (board[i][j].isVisible()
							&& board[i][j] instanceof Character) {
						((Character) board[i][j]).winLife(countDiscover
								* ((Character) board[i][j]).getLevel());
					}
				}
			}
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

	/**
	 * @param character
	 *            Method executed when a fight end. It's validate if a character
	 *            died. If any died execute a listener was provided by the
	 *            front.
	 */
	public void fightEnd(Character character) {
		if (character.isDead()) {
			Point point = new Point(character.getPosition().x, character
					.getPosition().y);
			BloodyFloor bf = new BloodyFloor();
			bf.setVisible();
			board[point.x][point.y] = bf;
			gameListener.executeWhenCharacterDie(point);

		}
		if (player.isDead()) {
			Point point = new Point(player.getPosition().x, player
					.getPosition().y);
			BloodyFloor bf = new BloodyFloor();
			bf.setVisible();
			board[point.x][point.y] = bf;
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

	/**
	 * @see back.Game#restart() The desition of making restart a method of a
	 *      game and not a class like loadGame is that a restart game need the
	 *      same boardObtainer that the instance of the game. Then is have no
	 *      sense make a new instance.
	 **/
	@Override
	public void restart() {
		File file = boardObtainer.getFile();
		try {
			board = boardObtainer.getClass().getConstructor(File.class)
					.newInstance(file).getBoard();
		} catch (Exception e) {
		}
		PlayerData playerData = new PlayerData(player.getName(), 0, 0, LIFE,
				LIFE, STRENGTH, boardObtainer.getPlayerPosition(), player
						.getSteps());
		player = new Player(playerData);
	}

}
