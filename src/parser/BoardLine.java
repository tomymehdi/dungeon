package parser;

import back.Point;

public class BoardLine extends Lines {

	private static final int elemsCuantity = 6;
	private Point boardDimension;

	public BoardLine(String line, Point boardDimension) {
		super(elemsCuantity, line);
		this.boardDimension = boardDimension;
		lineProcess();
		lineCheck();
	}

	/**
	 * This methods Checks which type of cell the parsed line is, and sets the
	 * cell into the board.
	 */

	@Override
	protected void lineCheck() {
		switch (data[0]) {

		case 1:
			// Player
			if (data[1] < 0 || data[1] >= boardDimension.x || data[2] < 0
					|| data[2] >= boardDimension.y || data[3] != 0
					|| data[4] != 0 || data[5] != 0) {
				throw new CorruptedFileException();
			}
			break;

		case 2:
			// Wall
			if (data[1] < 0 || data[1] >= boardDimension.x || data[2] < 0
					|| data[2] >= boardDimension.y || data[3] != 0
					|| data[4] != 0 || data[5] != 0) {
				throw new CorruptedFileException();
			}
			break;

		case 3:
			// Monster
			if (data[1] < 0 || data[1] >= boardDimension.x || data[2] < 0
					|| data[2] >= boardDimension.y || data[3] <= 0 || data[3] > 3
					|| data[4] <= 0 || data[4] > 3 || data[5] != 0) {
				throw new CorruptedFileException();
			}
			break;

		case 4:
			// Life Bonus
			if (data[1] < 0 || data[1] >= boardDimension.x || data[2] < 0
					|| data[2] >= boardDimension.y || data[3] != 0
					|| data[4] != 0 || data[5] == 0) {
				throw new CorruptedFileException();
			}
			break;

		case 5:
			// Strength Bonus
			if (data[1] < 0 || data[1] >= boardDimension.x || data[2] < 0
					|| data[2] >= boardDimension.y || data[3] != 0
					|| data[4] != 0 || data[5] == 0) {
				throw new CorruptedFileException();
			}
			break;

		default:
			throw new CorruptedFileException();
		}
	}

	public boolean isPlayerLine() {
		return data[0] == 1;
	}


	public boolean isWallLine() {
		return data[0] == 2;
	}
	
	public boolean isMonsterLine() {
		return data[0] == 3;
	}

	public boolean isLifeBonusLine() {
		return data[0] == 4;
	}

	public boolean isStengthBonusLine() {
		return data[0] == 5;
	}
}