package back;

public class Floor extends Cell implements Putable {
	@Override
	public String toString() {
		return "Floor";
	}

	@Override
	public boolean allowMovement(Game game) {
		return true;
	}

}
