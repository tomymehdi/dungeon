package parser;

public class BoardNameLine extends Lines {

	private static final int elemsCuantity = 1;
	private String name;

	public BoardNameLine(String line) {
		super(elemsCuantity, line);
		this.name = getLine();
	}

	@Override
	protected void lineProcess() {}
	
	public String getName() {
		return name;
	}

}
