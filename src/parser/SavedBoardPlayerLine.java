package parser;

import back.Point;

public class SavedBoardPlayerLine extends Lines {

	private static int elemsCuantity = 10;
	private Point boardDimension;
	private String playerName;

	public SavedBoardPlayerLine(String line, Point boardDimension) {
		super(elemsCuantity, line);
		this.boardDimension = boardDimension;
		lineProcess();
		lineCheck();
	}

	@Override
	protected void lineProcess() {
		data = new int[elemsCuantity];
		int k = 0;
		String[] arrayString;

		arrayString = getLine().split(",");

		if (arrayString.length == elemsCuantity) {
			for (k = 0; k < elemsCuantity - 1; k++) {
				try {
					data[k] = Integer.valueOf(arrayString[k]);
				} catch (NumberFormatException e) {
					throw new CorruptedFileException();
				}
			}
			playerName = arrayString[elemsCuantity - 1];
		} else {
			throw new CorruptedFileException();
		}
	}

	@Override
	protected void lineCheck() {

		if (data[1] < 0 || data[1] >= boardDimension.x - 2 || data[2] < 0
				|| data[2] >= boardDimension.y || data[3] < 0
				|| data[3] > data[4] || data[5] < 0) {
			throw new CorruptedFileException();
		}
	}

	public String getPlayerName() {
		return playerName;
	}

}
