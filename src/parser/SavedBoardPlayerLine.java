package parser;

import back.Point;

public class SavedBoardPlayerLine extends Lines {

	private static int elemsCuantity = 10;
	private Point boardDimension;

	public SavedBoardPlayerLine(String line, Point boardDimension) {
		super(elemsCuantity, line);
		this.boardDimension = boardDimension;
		lineProcess();
		lineCheck();
	}

	@Override
	protected void lineCheck() {

		// TODO AGREGAR MAS VALIDACIONES
		if (data[1] < 0 || data[1] >= boardDimension.x || data[2] < 0
				|| data[2] >= boardDimension.y || data[3] < 0 || data[3] >= boardDimension.x || data[4] < 0
				|| data[4] >= boardDimension.y){
			throw new CorruptedFileException();
		}
	}

}
