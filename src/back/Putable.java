package back;

public interface Putable {

	public boolean allowMovement(DungeonGameImp game);

	public void standOver(DungeonGameImp game);

	public boolean isVisible();

	public void setVisible();

	public void setNotVisible();

}
