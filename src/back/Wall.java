package back;

public class Wall extends Cell implements Putable {

	@Override
	public String toString() {
		return "Wall";
	}

	@Override
	public boolean allowMovement(DungeonGameImp game) {
		return false;
	}

	@Override
	public void standOver(DungeonGameImp game) {}

}