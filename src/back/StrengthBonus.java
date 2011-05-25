package back;

public class StrengthBonus extends Bonus {

	private Integer strengthBonus;

	public StrengthBonus(Integer strengthBonus) {
		this.strengthBonus = strengthBonus;
	}

	@Override
	public void giveBonus(Character character) {
		character.setStrength(character.getStrength() + strengthBonus);
	}

	public Integer getStrengthBonus() {
		return strengthBonus;
	}

	@Override
	public String toString() {
		return "Strength";
	}

}
