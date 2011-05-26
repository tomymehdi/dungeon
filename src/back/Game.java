package back;

import loadAndSave.LoadGame;
import loadAndSave.SaveGame;
import parser.BoardParser;

public class Game {

	public static Integer LEVEL = 3;
	static Integer LIFE = 10;
	static Integer STRENGTH = 5;

	private Player player;
	private String boardPath;
	private BoardParser boardParser;
	private GameListener gameListener;
	//private NameListener nameListener;

	public Game(String boardPath) {
		this.boardPath = boardPath;
		boardParser = new BoardParser(boardPath);
		//TODO
		player = new Player("Tomas"/*nameListener.nameRequest()*/,
				boardParser.getPlayerPosition(), LIFE, STRENGTH);
	}
	
	public Game(LoadGame loadgame){
		
	}

	public void receibeStroke(MoveTypes keyPressed) {
		movePlayer(keyPressed);
	}

	public void movePlayer(MoveTypes moveType) {
		
		Point nextPlayerPosition = player.getPosition().add(moveType.getDirection());
		
		if (boardParser.getBoardElem(nextPlayerPosition).allowMovement(this)) {
			player.move(moveType);
			//TODO
			//gameListener.executeWhenPlayerMoves();
			boardParser.getBoardElem(player.getPosition()).standOver(this);
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
	
	public void resetGame(){
		boardParser = new BoardParser(boardPath);
		player = new Player( player.getName(), boardParser.getPlayerPosition(), LIFE, STRENGTH);
	}
	
	public void saveGame(){
		new SaveGame(this);
	}
	
	public BoardParser getBoardParser() {
		return boardParser;
	}

	public void winned() {
		//TODO
		//gameListener.executeWhenGameWinned();
	}
}
