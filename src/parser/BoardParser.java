package parser;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import back.Cell;

public class BoardParser {

	private BufferedReader inputBoard;
	private Point boardDimension;
	private String boardName;
	private Point playerPosition;
	private Cell[][] board;

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

			line = line.replace(" ", "").replace("\t", "").replace("\n", "").split("#")[0];

			if (!line.isEmpty()) {
				if (!dimensionFlag) {
					BoardDimensionLine boardDimensionLine = new BoardDimensionLine(line);
					this.boardDimension = boardDimensionLine
							.getBoardDimension();
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

					if (cell.isPlayerLine()) {
						playerPosition = new Point(cell.getData(1), cell.getData(2));
						playerFlag = true;
					}
				}
			}
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

	public Cell[][] getBoard() {
		return board;
	}
}
