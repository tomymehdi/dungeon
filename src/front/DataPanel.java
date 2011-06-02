package front;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import back.Game;
import back.Monster;
import back.Player;
import back.Point;
import back.Putable;

public class DataPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private JLabel playerLabel;
	private Map<Monster, JLabel> monstersLabels = new HashMap<Monster, JLabel>();

	public DataPanel(Player player, Color color) {
		addCharacter(player);
		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
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
		playerLabel = new JLabel(dataPanel);
		add(playerLabel);
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
		monstersLabels.put(character, aux);
		add(aux);
	}

	public void refresh(Game game) {
		Point p = game.getPlayer().getPosition();
		Putable[] posibleMonsters = new Putable[4];
		posibleMonsters[0] = game.getBoard()[p.x + 1][p.y];
		posibleMonsters[1] = game.getBoard()[p.x - 1][p.y];
		posibleMonsters[2] = game.getBoard()[p.x][p.y + 1];
		posibleMonsters[3] = game.getBoard()[p.x][p.y - 1];
		remove(playerLabel);
		for(JLabel l: monstersLabels.values()){
			remove(l);
		}
		
		addCharacter(game.getPlayer());
		for (Putable put : posibleMonsters) {
			if (put.getClass().equals(Monster.class)) {
				addCharacter((Monster) put);
			}
		}
	}
	
}
