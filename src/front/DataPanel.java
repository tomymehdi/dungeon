package front;

import java.util.HashMap;
import java.util.Map;

import javax.swing.JLabel;
import javax.swing.JPanel;

import back.DungeonGame;
import back.Monster;
import back.Player;

public class DataPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private Map<Monster,JLabel> labels = new HashMap<Monster,JLabel>();
	
	public DataPanel(Player player) {
		addCharacter(player);
	}

	public void addCharacter(Player character) {
		String dataPanel = new String("");
		dataPanel += character.getName() + "\n";
		dataPanel += "Health:";
		dataPanel += character.getHealth().toString() + "/"
				+ character.getMaxHealth().toString() + "\n";
		dataPanel += "Strength:";
		dataPanel += character.getStrength() + "\n";
		dataPanel += "Level:";
		dataPanel += character.getLevel().toString();
		dataPanel += "Experience:";
		dataPanel += character.getExperience().toString();
		add(new JLabel(dataPanel));
	}
	
	public void addCharacter(Monster character) {
		String dataPanel = new String("");
		dataPanel += character.getName() + "\n";
		dataPanel += "Health:";
		dataPanel += character.getHealth().toString() + "/"
				+ character.getMaxHealth().toString() + "\n";
		dataPanel += "Strength:";
		dataPanel += character.getStrength() + "\n";
		dataPanel += "Level:";
		dataPanel += character.getLevel().toString();
		JLabel aux = new JLabel(dataPanel);
		labels.put(character, aux);
		add(aux);
	}
	
	public void refresh(DungeonGame game) {
		//TODO
	}
}
