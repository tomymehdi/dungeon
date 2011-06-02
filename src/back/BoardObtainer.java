package back;

import java.io.File;

public interface BoardObtainer {

	public void obtainBoard() throws Exception;
	
	public Point getBoardDimension();

	public Putable[][] getBoard();

	public Point getPlayerPosition();
	
	public Integer getPlayerLoadedHealth();
	
	public Integer getPlayerLoadedMaxHealth();

	public String getBoardName();

	public Putable getBoardElem(Point point);

	public int getBoardRows();

	public int getBoardColums();

	public File getFile();

	public String getPlayerName();

	public Integer getPlayerLoadedSteps();

	Integer getPlayerLoadedExperience();

	Integer getPlayerLoadedStrength();

	public int getPlayerLoadedLevel();

}
