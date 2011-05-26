package parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import back.Floor;
import back.LifeBonus;
import back.Monster;
import back.Point;
import back.Putable;
import back.StrengthBonus;
import back.Wall;

/**
 * @author tomas Class full dedicated to read a file and transform it to a
 *         board.
 */
public class BoardParser {

	private BufferedReader inputBoard;
	private Point boardDimension; // cantidad de filas x cantidad de columnas
	private String boardName;
	private Point playerPosition;
	private Putable[][] board; // si tengo un elemento en 1,7 es fila 1 columna

	// 7

	public BoardParser(File file) {

		try {
			inputBoard = new BufferedReader(new FileReader(file));
			fileParser();
		} catch (IOException e) {
			// TODO NO OLVIDAR AGARRARLA DSPS
			throw new CorruptedFileException();
		}
	}

	public void fileParser() throws IOException {

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
					BoardLine cell = new BoardLine(line, boardDimension);

					if (cell.isPlayerLine() && playerFlag == true) {
						throw new CorruptedFileException();
					}

					Point point = (new Point(cell.getData(1), cell.getData(2)))
							.add(new Point(1, 1));

					if (cell.isPlayerLine()) {
						parsePlayer(cell,point);
						playerFlag = true;

						// TODO PREGUNTAR POR MANERA DE TENER UNA LISTA DE
						// CLASES Y PODER CREAR UNA INSTANCIA DE UNA DE LAS
						// CLASES DE
						// ESA LISTA
					} else if (cell.isWallLine()) {
						parseWall(point);
					} else if (cell.isMonsterLine()) {
						parseMonster(point, cell);
					} else if (cell.isLifeBonusLine()) {
						parseLifeBonus(point, cell);
					} else {
						parseStrengthBonus(point, cell);

					}
				}
			}
		}

		protectionWalls();
		putFloor();

		if (board[playerPosition.x][playerPosition.y].getClass() != Floor.class) {
			throw new CorruptedFileException();
		}

	}

	public void parsePlayer(BoardLine cell, Point point) {
		playerPosition = point;
	}

	public void parseStrengthBonus(Point point, BoardLine cell) {
		board[point.x][point.y] = new StrengthBonus(cell.getData(5));
	}

	public void parseLifeBonus(Point point, BoardLine cell) {
		board[point.x][point.y] = new LifeBonus(cell.getData(5));
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
}
