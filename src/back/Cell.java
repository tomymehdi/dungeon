package back;

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
