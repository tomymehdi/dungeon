package back;

public class Player extends Character {

	Integer experience;
	
	public Player(String name, Integer initialLife, Integer initialStrength) {
		super(name, initialLife, initialStrength);
		this.experience = 0;
		this.level = 1;
		// TODO Auto-generated constructor stub
	}
	

	
}
