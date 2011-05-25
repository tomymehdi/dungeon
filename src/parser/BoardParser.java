package parser;

import java.io.BufferedReader;
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
 * @author tomas
 * Class full dedicated to read a file and transform it to a board.
 */
public class BoardParser {

	private BufferedReader inputBoard;
	private Point boardDimension; //cantidad de filas x cantidad de columnas
	private String boardName;
	private Point playerPosition;
	private Putable[][] board;  //si tengo un elemento en 1,7 es fila 1 columna 7

	public BoardParser(String path) {

		try {
			inputBoard = new BufferedReader(new FileReader(path));
			fileParser();
		} catch (IOException e) {
			throw new CorruptedFileException();
		}
	}

	private void fileParser() throws IOException {

		boolean dimensionFlag = false;
		boolean nameFlag = false;
		boolean playerFlag = false;
		String line;

		while ((line = inputBoard.readLine()) != null) {

			line = line.replace(" ", "").replace("\t", "").replace("\n", "")
					.split("#")[0];

			if (!line.isEmpty()) {
				if (!dimensionFlag) {
					BoardDimensionLine boardDimensionLine = new BoardDimensionLine(
							line);
					this.boardDimension = boardDimensionLine
							.getBoardDimension();
					board = new Putable[this.boardDimension.x][this.boardDimension.y];
					dimensionFlag = true;
				} else if (!nameFlag) {
					BoardNameLine boardNameLine = new BoardNameLine(line);
					this.boardName = boardNameLine.getName();
					nameFlag = true;
				} else {
					BoardLine cell = new BoardLine(line, boardDimension);

					if (cell.isPlayerLine() && playerFlag == true) {
						throw new CorruptedFileException();
					}

					Point point = new Point(cell.getData(1), cell.getData(2));

					if (cell.isPlayerLine()) {
						playerPosition = point;
						playerFlag = true;

						// TODO PREGUNTAR POR MANERA DE TENER UNA LISTA DE
						// CLASES Y PODER CREAR UNA INSTANCIA DE UNA DE LAS CLASES DE
						// ESA LISTA
					} else if (cell.isWallLine()) {
						board[point.x][point.y] = new Wall();
					} else if (cell.isMonsterLine()) {
						Monster monster = new Monster(point,cell.getData(3), cell.getData(4));
						board[point.x][point.y] = monster;
					} else if (cell.isLifeBonusLine()) {
						board[point.x][point.y] = new LifeBonus(cell.getData(5));
					} else {
						board[point.x][point.y] = new StrengthBonus(cell.getData(5));
					}
				}
			}
		}
		
		for(int i = 0 ; i < boardDimension.x ; i++){
			for(int j = 0 ; j < boardDimension.y ; j++){
				if(board[i][j] == null){
					board[i][j] = new Floor();
				}
			}
		}
		
		if( board[playerPosition.x][playerPosition.y].getClass() != Floor.class ){
			throw new CorruptedFileException();
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
	
	public int getBoardColums(){
		return boardDimension.y;
	}

	public Putable getBoardElem(Point position) {
		return board[position.x][position.y];
	}
}
