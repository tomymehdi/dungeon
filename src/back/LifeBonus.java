package back;


public class LifeBonus extends Bonus {

	private Integer lifeBonus;
	
	public LifeBonus(Integer lifeBonus) {
		this.lifeBonus = lifeBonus;
	}

	@Override
	public void giveBonus(Character character) {
		character.setHealth(character.getHealth() + lifeBonus);
	}

	public Integer getLifeBonus() {
		return lifeBonus;
	}
	
	@Override
	public String toString() {
		return "Life";
	}
	
}
