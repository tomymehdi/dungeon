package back;

/**
 * @author tomas
 *	Abstract class inserted on the hierarchy to make every class that can be on the board
 *	to be visible or invisible. Particular feature of this game.
 */
public abstract class Cell {
	
	boolean isVisible = false;

	public boolean isVisible() {
		return isVisible;
	}

	public void setVisible() {
		this.isVisible = true;
	}
	
	public void setNotVisible() {
		this.isVisible = false;
	}
	
}
