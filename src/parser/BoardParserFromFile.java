package parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import back.BoardObtainer;
import back.Bonus;
import back.Floor;
import back.Monster;
import back.Point;
import back.Putable;
import back.Wall;

/**
 * @author tomas Class full dedicated to read a file and transform it to a
 *         board.
 */
public class BoardParserFromFile implements BoardObtainer {

	private BufferedReader inputBoard;
	private Point boardDimension;
	private String boardName;
	private Point playerPosition;
	private Putable[][] board;
	private File inputFile;

	public BoardParserFromFile(File file) {
		try {
			inputFile = file;
			inputBoard = new BufferedReader(new FileReader(file));
			obtainBoard();
		} catch (IOException e) {
			throw new CorruptedFileException();
		}
	}

	public void obtainBoard() throws IOException {

		boolean dimensionFlag = false;
		boolean nameFlag = false;
		boolean playerFlag = false;
		String line;

		while ((line = inputBoard.readLine()) != null) {

			line = line.replace(" ", "").replace("\t", "").replace("\n", "")
					.split("#")[0];

			if (!line.isEmpty()) {
				if (!dimensionFlag) {
					parseDimension(line);
					dimensionFlag = true;
				} else if (!nameFlag) {
					parseBoardName(line);
					nameFlag = true;
				} else {
					if (line.startsWith("1")) {
						if (playerFlag == true) {
							throw new CorruptedFileException();
						}
						parsePlayer(line);
						playerFlag = true;
					} else {

						BoardLine cell = new BoardLine(line, boardDimension);
						Point point = (new Point(cell.getData(1),
								cell.getData(2))).add(new Point(1, 1));

						if (cell.isWallLine()) {
							parseWall(point);
						} else if (cell.isMonsterLine()) {
							parseMonster(point, cell);
						} else if (cell.isBonusLine()) {
							parseBonus(point, cell);
						} 
					}
				}
			}
		}

		if (!nameFlag || !playerFlag || !dimensionFlag) {
			throw new CorruptedFileException();
		}

		protectionWalls();
		putFloor();
		if (board[getPlayerPosition().x][getPlayerPosition().y].getClass() != Floor.class) {
			throw new CorruptedFileException();
		}

	}

	private void parseBonus(Point point, BoardLine cell) {
		Bonus bonus = new Bonus(point,cell.getData(0), cell.getData(5));
		board[point.x][point.y] = bonus;
	}

	public void parsePlayer(String line) {
		BoardLine cell = new BoardLine(line, boardDimension);
		Point point = (new Point(cell.getData(1), cell.getData(2)))
				.add(new Point(1, 1));
		playerPosition = point;
	}

	private void parseMonster(Point point, BoardLine cell) {
		Monster monster = new Monster(point, cell.getData(3), cell.getData(4));
		board[point.x][point.y] = monster;

	}

	public void parseWall(Point point) {
		board[point.x][point.y] = new Wall();

	}

	public void parseBoardName(String line) {
		BoardNameLine boardNameLine = new BoardNameLine(line);
		this.boardName = boardNameLine.getName();
	}

	public void parseDimension(String line) {
		BoardDimensionLine boardDimensionLine = new BoardDimensionLine(line);
		boardDimension = boardDimensionLine.getBoardDimension().add(
				new Point(2, 2));
		board = new Putable[boardDimension.x][boardDimension.y];

	}

	public void putFloor() {
		for (int i = 1; i < boardDimension.x - 1; i++) {
			for (int j = 1; j < boardDimension.y - 1; j++) {
				if (board[i][j] == null) {
					board[i][j] = new Floor();
				}
			}
		}
	}

	public void protectionWalls() {
		for (int i = 0; i < boardDimension.x; i++) {
			board[i][0] = new Wall();
			board[0][i] = new Wall();
			board[i][boardDimension.y - 1] = new Wall();
			board[boardDimension.x - 1][i] = new Wall();
		}

	}

	public Point getBoardDimension() {
		return boardDimension;
	}

	public String getBoardName() {
		return boardName;
	}

	public Point getPlayerPosition() {
		return playerPosition;
	}

	public Putable[][] getBoard() {
		return board;
	}

	public int getBoardRows() {
		return boardDimension.x;
	}

	public int getBoardColums() {
		return boardDimension.y;
	}

	public Putable getBoardElem(Point position) {
		return board[position.x][position.y];
	}

	@Override
	public File getFile() {
		return inputFile;
	}

}
