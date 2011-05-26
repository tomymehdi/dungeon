package loadAndSave;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import back.BloodyFloor;
import back.Game;
import back.LifeBonus;
import back.Monster;
import back.StrengthBonus;
import back.Wall;

public class SaveGame {

	private Game gameToSave;
	private File placeToSave;

	public SaveGame(Game gameToSave) {
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
			// TODO NO OLVIDAR AGARRARLA DSPS
			throw new SavingCorruptedException();
		}
	}

	public SaveGame(Game gameToSave, File placeToSave) {
		this.gameToSave = gameToSave;
		this.placeToSave = placeToSave;
		FilterFileList filterFileList = new FilterArrayFileList(placeToSave
				.getParentFile());
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
			// TODO NO OLVIDAR AGARRARLA DSPS
			throw new SavingCorruptedException();
		}
	}

	private void save() throws IOException {
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
		out
				.write("#Player starting position, current position, "
						+ "current exp, current health, maxHealth, current strength, steps");
		out.newLine();
		out.write(1 + "," + (gameToSave.getStartingPlayerPosition().x - 1)
				+ "," + (gameToSave.getStartingPlayerPosition().y - 1) + ","
				+ (gameToSave.getPlayer().getPosition().x - 1) + ","
				+ (gameToSave.getPlayer().getPosition().y) + ","
				+ gameToSave.getPlayer().getExperience() + ","
				+ gameToSave.getPlayer().getHealth() + ","
				+ gameToSave.getPlayer().getMaxHealth() + ","
				+ gameToSave.getPlayer().getStrength() + ","
				+ gameToSave.getPlayer().getSteps());
		out.newLine();
		out.write("#Dungeon map");
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
							+ ((Monster) gameToSave.getBoard()[i][j])
									.getMonsterType()
							+ ","
							+ ((Monster) gameToSave.getBoard()[i][j])
									.getLevel() + "," + 0);
					out.newLine();
				} else if (LifeBonus.class.equals((gameToSave.getBoard()[i][j])
						.getClass())) {
					out.write(4
							+ ","
							+ (i - 1)
							+ ","
							+ (j - 1)
							+ ","
							+ 0
							+ ","
							+ 0
							+ ","
							+ ((LifeBonus) gameToSave.getBoard()[i][j])
									.getLifeBonus());
					out.newLine();
				} else if (StrengthBonus.class
						.equals((gameToSave.getBoard()[i][j]).getClass())) {
					out.write(5
							+ ","
							+ (i - 1)
							+ ","
							+ (j - 1)
							+ ","
							+ 0
							+ ","
							+ 0
							+ ","
							+ ((StrengthBonus) gameToSave.getBoard()[i][j])
									.getStrengthBonus());
					out.newLine();
				}

			}
		}

		out.flush();
		out.close();

	}
}