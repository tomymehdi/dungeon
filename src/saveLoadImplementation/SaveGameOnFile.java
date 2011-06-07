package saveLoadImplementation;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import back.BloodyFloor;
import back.Bonus;
import back.Floor;
import back.Game;
import back.Monster;
import back.SaveGame;
import back.Wall;

/**
 * @author tomas SaveGame implementation that save on a file.
 */
public class SaveGameOnFile implements SaveGame {

	private Game gameToSave;
	private File placeToSave;

	public SaveGameOnFile(Game gameToSave) {
		this.gameToSave = gameToSave;
		File file = new File("./savedGames");
		FilterFileList filterFileList = new FilterArrayFileList(file);
		filterFileList = filterFileList.filter("savedGame");
		int number = filterFileList.size();
		if (number > 0) {
			placeToSave = new File("./savedGames/savedGame" + "(" + number + ")"
					+ ".board");
		} else {
			placeToSave = new File("./savedGames/savedGame.board");
		}
		try {
			save();
		} catch (IOException e) {
			throw new SavingCorruptedException();
		}
	}

	public SaveGameOnFile(Game gameToSave, File placeToSave) {
		this.gameToSave = gameToSave;
		this.placeToSave = placeToSave;
		FilterFileList filterFileList = new FilterArrayFileList(
				placeToSave.getParentFile());
		filterFileList = filterFileList.filter(placeToSave.getName());
		int number = filterFileList.size();
		if (number > 0) {
			this.placeToSave = new File(placeToSave.getPath() + "(" + number
					+ ")"+ ").board");
		} else {
			this.placeToSave = new File(placeToSave.getPath());
		}
		try {
			save();
		} catch (IOException e) {
			throw new SavingCorruptedException();
		}
	}

	/**
	 * The format of the file saved is: board dimension (10,11) board name
	 * ("Board name") player (1,row pos, col pos,exp,health,max health,
	 * strength, steps, level, name) walls (2,row pos, col pos, 0 ,0, [0 is
	 * visible 1 not visible]) bloodyFloor(2,row pos, col pos, 2 ,0, [0 is
	 * visible 1 not visible]) floor(2,row pos, col pos, 1 ,0,[0 is visible 1
	 * not visible]) monsters (3,row pos, col pos, monster type, level, [0 is
	 * visible 1 not visible]) bonus (4 or 5, row pos, col pos, 0,[0 is visible
	 * 1 not visible],amount of bonus)
	 **/
	public void save() throws IOException {
		placeToSave.createNewFile();
		BufferedWriter out = new BufferedWriter(new FileWriter(placeToSave));
		out.write("#Board dimensions");
		out.newLine();
		out.write((gameToSave.getBoardDimension().x - 2) + ","
				+ (gameToSave.getBoardDimension().y - 2));
		out.newLine();
		out.write("#Board name");
		out.newLine();
		out.write(gameToSave.getBoardName());
		out.newLine();
		out.write("#Player current position, "
				+ "current exp, current health, maxHealth, current strength, steps, name");
		out.newLine();
		out.write(1 + "," + (gameToSave.getPlayer().getPosition().x - 1) + ","
				+ (gameToSave.getPlayer().getPosition().y - 1) + ","
				+ gameToSave.getPlayer().getExperience() + ","
				+ gameToSave.getPlayer().getHealth() + ","
				+ gameToSave.getPlayer().getMaxHealth() + ","
				+ gameToSave.getPlayer().getStrength() + ","
				+ gameToSave.getPlayer().getSteps() + ","
				+ gameToSave.getPlayer().getLevel() + ","
				+ gameToSave.getPlayer().getName());
		out.newLine();
		out.write("#Map");
		out.newLine();
		for (int i = 1; i < gameToSave.getBoardDimension().x - 1; i++) {
			for (int j = 1; j < gameToSave.getBoardDimension().y - 1; j++) {
				if (Wall.class.equals((gameToSave.getBoard()[i][j]).getClass())) {
					out.write(2 + "," + (i - 1) + "," + (j - 1) + "," + 0 + ","
							+ 0 + ",");
					if (gameToSave.getBoard()[i][j].isVisible()) {
						out.write("0");
					} else {
						out.write("1");
					}
					out.newLine();
				} else if (Floor.class.equals((gameToSave.getBoard()[i][j])
						.getClass())) {
					out.write(2 + "," + (i - 1) + "," + (j - 1) + "," + 1 + ","
							+ 0 + ",");
					if (gameToSave.getBoard()[i][j].isVisible()) {
						out.write("0");
					} else {
						out.write("1");
					}
					out.newLine();
				} else if (BloodyFloor.class
						.equals((gameToSave.getBoard()[i][j]).getClass())) {
					out.write(2 + "," + (i - 1) + "," + (j - 1) + "," + 2 + ","
							+ 0 + ",");
					if (gameToSave.getBoard()[i][j].isVisible()) {
						out.write("0");
					} else {
						out.write("1");
					}
					out.newLine();
				} else if (Monster.class.equals((gameToSave.getBoard()[i][j])
						.getClass())) {
					out.write(3
							+ ","
							+ (i - 1)
							+ ","
							+ (j - 1)
							+ ","
							+ (((Monster) gameToSave.getBoard()[i][j])
									.getMonsterType().ordinal() + 1)
							+ ","
							+ ((Monster) gameToSave.getBoard()[i][j])
									.getLevel() + ",");
					if (gameToSave.getBoard()[i][j].isVisible()) {
						out.write((((Monster) gameToSave.getBoard()[i][j])
								.getHealth() * -1) + "");
					} else {
						out.write((((Monster) gameToSave.getBoard()[i][j])
								.getHealth()) + "");
					}
					out.newLine();
				} else if (Bonus.class.equals((gameToSave.getBoard()[i][j])
						.getClass())) {
					out.write((((Bonus) gameToSave.getBoard()[i][j])
							.getBonusType().ordinal() + 4)
							+ ","
							+ (i - 1)
							+ "," + (j - 1) + "," + 0 + ",");
					if (gameToSave.getBoard()[i][j].isVisible()) {
						out.write("0");
					} else {
						out.write("1");
					}
					out.write(","
							+ ((Bonus) gameToSave.getBoard()[i][j])
									.getAmountBonus());
					out.newLine();
				}
			}
		}

		out.flush();
		out.close();

	}
}
