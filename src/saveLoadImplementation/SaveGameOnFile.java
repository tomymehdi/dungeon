package saveLoadImplementation;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import back.BloodyFloor;
import back.Bonus;
import back.Game;
import back.Monster;
import back.SaveGame;
import back.Wall;

public class SaveGameOnFile<T extends Game> implements SaveGame<T>{

	private T gameToSave;
	private File placeToSave;

	public SaveGameOnFile(T gameToSave) {
		this.gameToSave = gameToSave;
		File file = new File("./savedGames");
		FilterFileList filterFileList = new FilterArrayFileList(file);
		filterFileList = filterFileList.filter("savedGame");
		int number = filterFileList.size();
		if (number > 0) {
			placeToSave = new File("./savedGames/savedGame" + "(" + number
					+ ")");
		} else {
			placeToSave = new File("./savedGames/savedGame");
		}
		try {
			save();
		} catch (IOException e) {
			throw new SavingCorruptedException();
		}
	}

	public SaveGameOnFile(T gameToSave, File placeToSave) {
		this.gameToSave = gameToSave;
		this.placeToSave = placeToSave;
		FilterFileList filterFileList = new FilterArrayFileList(
				placeToSave.getParentFile());
		filterFileList = filterFileList.filter(placeToSave.getName());
		int number = filterFileList.size();
		if (number > 0) {
			this.placeToSave = new File(placeToSave.getPath() + "(" + number
					+ ")");
		} else {
			this.placeToSave = new File(placeToSave.getPath());
		}
		try {
			save();
		} catch (IOException e) {
			throw new SavingCorruptedException();
		}
	}

	public void save() throws IOException {
		placeToSave.createNewFile();
		BufferedWriter out = new BufferedWriter(new FileWriter(placeToSave));
		out.write("#Board dimensions");
		out.newLine();
		out.write(gameToSave.getBoardDimension().x + ","
				+ gameToSave.getBoardDimension().y);
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
				+ gameToSave.getPlayer().getName());
		out.newLine();
		out.write("#Map");
		out.newLine();
		for (int i = 1; i < gameToSave.getBoardDimension().x - 1; i++) {
			for (int j = 1; j < gameToSave.getBoardDimension().y - 1; j++) {
				if (Wall.class.equals((gameToSave.getBoard()[i][j]).getClass())) {
					out.write(2 + "," + (i - 1) + "," + (j - 1) + "," + 0 + ","
							+ 0 + "," + 0);
					out.newLine();
				} else if (BloodyFloor.class
						.equals((gameToSave.getBoard()[i][j]).getClass())) {
					out.write(6 + "," + (i - 1) + "," + (j - 1) + "," + 0 + ","
							+ 0 + "," + 0);
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
									.getLevel() + "," + 0);
					out.newLine();
				} else if (Bonus.class.equals((gameToSave.getBoard()[i][j])
						.getClass())) {
					out.write((((Bonus) gameToSave.getBoard()[i][j])
							.getBonusType().ordinal() + 4)
							+ ","
							+ (i - 1)
							+ ","
							+ (j - 1)
							+ ","
							+ 0
							+ ","
							+ 0
							+ ","
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