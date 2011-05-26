package loadAndSave;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;

import back.Game;

public class SaveGame {

	Game gameToSave;
	File placeToSave;

	public SaveGame(Game gameToSave) {
		this.gameToSave = gameToSave;
		int number = new File("./savedGames").list(new FilenameFilter() {

			@Override
			public boolean accept(File dir, String name) {
				dir.getPath();
				return false;
			}

		}).length;
		placeToSave = new File("./savedGames/savedGame" + (number + 1));
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
		if (placeToSave.exists()) {
			placeToSave = new File(placeToSave.getPath() + "(1)");
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
		out.write(gameToSave.getBoardParser().getBoardDimension().x + ","
				+ gameToSave.getBoardParser().getBoardDimension().y);
		out.newLine();
		out
				.write("#Player starting position, help if gameloaded and restarted");
		out.newLine();
		out.write(gameToSave.getBoardParser().getPlayerPosition().x + ","
				+ gameToSave.getBoardParser().getPlayerPosition().y);
		out.newLine();
		out.write("#Player current position at the moment of saving");
		out.newLine();
		out.write(gameToSave.getPlayer().getPosition().x + ","
				+ gameToSave.getPlayer().getPosition().y);
		out.newLine();
		out.write("#Player experience,experienceToLevel at moment of saving");
		out.newLine();
		out.write(gameToSave.getPlayer().getExperience().toString());
		out.newLine();
		out.write("#Player health,max health at moment of saving");
		out.newLine();
		out.write(gameToSave.getPlayer().getHealth() + ","
				+ gameToSave.getPlayer().getMaxHealth());
		out.newLine();
		out.write("#Player strength at moment of saving");
		out.newLine();
		out.write(gameToSave.getPlayer().getStrength().toString());
		out.newLine();
		out.write("#Count of steps done at the moment of saving");
		out.newLine();
		out.write(gameToSave.getPlayer().getSteps().toString());
		out.newLine();
		out.write("#Dungeon map");
		out.newLine();

		for (int i = 1; i < gameToSave.getBoardParser().getBoardRows(); i++) {

		}

		out.flush();
		out.close();

	}

}
