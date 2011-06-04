package back;

/**
 * @author tomas
 *	A beautiful enumerate for the different types of Bonuses.
 */
public enum BonusTypes {
	
	LIFE("Life", 0, new GrabBonus(){

		@Override
		public void grabBonus(Character character, Integer bonusAmount) {
			character.winLife(bonusAmount);
		}
		
	}), STRENGTH("Strength", 0, new GrabBonus(){

		@Override
		public void grabBonus(Character character, Integer bonusAmount) {
			character.grabStrengthBonus(bonusAmount);
		}
		
	});
	
	private String name;
	private Integer bonusAmount;
	private GrabBonus bonusGrabbed;

	private BonusTypes(String name, Integer bonusAmount, GrabBonus bonusGrabbed) {
		this.name = name;
		this.bonusAmount = bonusAmount;
		this.bonusGrabbed = bonusGrabbed;
	}

	public Integer getBonusAmount(){
		return bonusAmount;
	}
	
	public void setBonusAmount(Integer bonusAmount){
		this.bonusAmount = bonusAmount;
	}

	public String getName() {
		return name;
	}
	
	public static BonusTypes getBonusType(int data) {
		switch (data) {
		case (4):
			return BonusTypes.LIFE;
		case (5):
			return BonusTypes.STRENGTH;
		default:
			return null;
		}
	}

	public void giveBonus(Character character) {
		bonusGrabbed.grabBonus(character,getBonusAmount());
	}
}
