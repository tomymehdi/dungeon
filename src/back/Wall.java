package back;


public class Wall extends Cell implements Putable{

	@Override
	public String toString() {
		return "Wall";
	}

	@Override
	public boolean allowMovement(Game game) {
		return false;
	}

}