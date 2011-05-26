package src;

import org.junit.Test;

import back.Game;

public class SaveGameTests {

	@Test
	public void forWatchTheGameSaved(){
		Game game = new Game("./testBoard/boardForTest1");
		game.saveGame();
	}
}
