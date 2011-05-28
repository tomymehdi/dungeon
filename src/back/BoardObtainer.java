package back;

public interface BoardObtainer {

	public void obtainBoard() throws Exception;
	
	public Point getBoardDimension();

	public Putable[][] getBoard();

	public Point getPlayerPosition();

	public String getBoardName();

	public Object getBoardElem(Point point);

	public int getBoardRows();

	public int getBoardColums();

}
