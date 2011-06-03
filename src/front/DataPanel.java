package front;

import java.awt.Color;
import java.awt.Font;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import back.Monster;
import back.Player;
import back.Point;
import back.Putable;

/**
 * @author tmehdi Class that extends the class J|Panel. This class is used for
 *         the Dungeon panel that is into the DungeonGameFrame.
 * 
 */
public class DataPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unused")
	private JLabel[] playerLabel;
	private Map<Monster, JLabel[]> monstersLabels = new HashMap<Monster, JLabel[]>();

	public DataPanel(Player player, Color color) {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		addCharacter(player);
	}

	public void addCharacter(Player character) {
		JLabel[] playerLabel = new JLabel[6];
		playerLabel[0] = new JLabel(character.getName());
		playerLabel[0].setFont(new Font("Serif",Font.BOLD,30));
		playerLabel[0].setForeground(Color.BLUE);
		playerLabel[1] = new JLabel("Health: " + character.getHealth() + "/"
				+ character.getMaxHealth());
		playerLabel[2] = new JLabel("Strength: " + character.getStrength());
		playerLabel[3] = new JLabel("Level: " + character.getLevel());
		playerLabel[4] = new JLabel("Experience: " + character.getExperience()
				+ "/" + character.getExperienceToLevelUp()+ "  ");
		playerLabel[5] = new JLabel(" ");
		this.playerLabel = playerLabel;
		for (JLabel pl : playerLabel) {
			add(pl);
		}
	}

	public void addCharacter(Monster character) {
		JLabel[] playerLabel = new JLabel[5];
		playerLabel[0] = new JLabel(character.getName());
		playerLabel[0].setFont(new Font("Serif",Font.BOLD,25));
		playerLabel[0].setForeground(Color.RED);
		playerLabel[1] = new JLabel("Health: " + character.getHealth() + "/"
				+ character.getMaxHealth());
		playerLabel[2] = new JLabel("Strength: " + character.getStrength());
		playerLabel[3] = new JLabel("Level: " + character.getLevel());
		playerLabel[4] = new JLabel(" ");
		for (JLabel pl : playerLabel) {
			add(pl);
		}
		monstersLabels.put(character, playerLabel);
	}

	public void removeCharacter(Monster character) {
		JLabel[] labels = monstersLabels.get(character);
		for (JLabel ml : labels) {
			remove(ml);
		}
	}

	public void refresh(DungeonGameFrame dungeonGameFrame) {
		Putable[] posibleMonsters = new Putable[5];
		Point p = dungeonGameFrame.game.getPlayer().getPosition();

		posibleMonsters[0] = dungeonGameFrame.game.getBoard()[p.x + 1][p.y];
		posibleMonsters[1] = dungeonGameFrame.game.getBoard()[p.x - 1][p.y];
		posibleMonsters[2] = dungeonGameFrame.game.getBoard()[p.x][p.y + 1];
		posibleMonsters[3] = dungeonGameFrame.game.getBoard()[p.x][p.y - 1];
		posibleMonsters[4] = dungeonGameFrame.getDungeonPanel()
				.getMonsterUnderMouse();

		removeAll();

		for (int i = 0; posibleMonsters[4] != null && i < 4; i++) {
			if (posibleMonsters[4].equals(posibleMonsters[i])) {
				posibleMonsters[4] = null;
			}
		}

		addCharacter(dungeonGameFrame.game.getPlayer());
		for (Putable put : posibleMonsters) {
			if (put != null && put instanceof Monster) {
				addCharacter((Monster) put);
			}
		}
	}

}
