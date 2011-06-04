package front;

import java.awt.Frame;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * @author tomas Class for show the player a list of levels that are saved on
 *         the directory boards. It use a list of directorys and some class of
 *         java swing.
 */
public class LevelSelectorImp extends JFrame implements LevelSelector {

	private static final long serialVersionUID = 1L;

	private File levelSelected;

	public LevelSelectorImp(Frame frameToShowOn) {

		String[] listBoards;
		File directory = new File("./boards");
		listBoards = directory.list();
		Object levelSelected = JOptionPane.showInputDialog(frameToShowOn,
				"Select level", "Levels selector",
				JOptionPane.QUESTION_MESSAGE, null, listBoards, listBoards[0]);
		if (levelSelected != null) {
			this.levelSelected = new File("./boards/" + levelSelected);
		}

	}

	public File getLevelSelected() {
		return levelSelected;
	}

}
