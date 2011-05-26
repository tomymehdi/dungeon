package src;

import java.io.File;

import loadAndSave.SaveGame;

import org.junit.Test;

import back.Game;

public class SaveGameTests {

	@Test
	public void forWatchTheGameSavedTest() {
		Game game = new Game("./testBoard/boardForTest1");
		new SaveGame(game);
	}

	@Test
	public void forWatchTheGameSavedWithPathTest() {
		Game game = new Game("./testBoard/boardForTest1");
		new SaveGame(game, new File("./savedGames/testWithPath"));
	}
}
