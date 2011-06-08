package parser;

public abstract class Lines {

	protected int[] data;
	private final int elemsCuantity;
	private String line;

	public Lines(int elemsCuantity, String line) {
		this.elemsCuantity = elemsCuantity;
		this.line = line;
	}

	/**
	 * Process the line parsed by separating it by "," and removing the spaces,
	 * enters and tabs in between.
	 * 
	 */
	protected void lineProcess() {
		data = new int[elemsCuantity];
		int k = 0;
		String[] arrayString;

		arrayString = line.split(",");

		if (arrayString.length == elemsCuantity) {
			for (k = 0; k < elemsCuantity; k++) {
				try {
					data[k] = Integer.valueOf(arrayString[k]);
				} catch (NumberFormatException e) {
					throw new CorruptedFileException();
				}
			}
		} else {
			throw new CorruptedFileException();
		}
	}

	public int getData(int i) {
		return data[i];
	}

	public String getLine() {
		return line;
	}

	protected void lineCheck(){}
}