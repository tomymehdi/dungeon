package parser;

import back.Point;

public class BoardDimensionLine extends Lines {

	private static final int elemsCuantity = 2;
	private Point boardDimension;

	public BoardDimensionLine(String line) {
		super(elemsCuantity, line);
		lineProcess();
		boardDimension = new Point(getData(0), getData(1));
	}

	public Point getBoardDimension() {
		return boardDimension;
	}

}
