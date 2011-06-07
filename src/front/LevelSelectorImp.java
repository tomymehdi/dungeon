package front;

import java.awt.Frame;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

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

		String[] auxFiles, listBoardsShowed;
		List<String> listBoards = new ArrayList<String>();
		File directory = new File("." + File.separator + "boards");
		auxFiles = directory.list();
		for (String s : auxFiles) {
			if (s.endsWith(".board")) {
				listBoards.add(s.replace(".board", ""));
			}
		}
		listBoardsShowed = new String[listBoards.size()];
		for (int k = 0; k < listBoards.size(); k++) {
			listBoardsShowed[k] = listBoards.get(k);
		}

		Object levelSelected = JOptionPane.showInputDialog(frameToShowOn,
				"Select level", "Levels selector",
				JOptionPane.QUESTION_MESSAGE, null, listBoardsShowed,
				listBoardsShowed[0]);
		if (levelSelected != null) {
			this.levelSelected = new File("." + File.separator + "boards"
					+ File.separator + levelSelected + ".board");
		}

	}

	public File getLevelSelected() {
		return levelSelected;
	}

}
