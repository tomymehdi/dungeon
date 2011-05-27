package src;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;

import loadAndSave.FilterArrayFileList;
import loadAndSave.FilterFileList;

import org.junit.Before;
import org.junit.Test;

import back.BloodyFloor;
import back.Bonus;
import back.Game;
import back.LifeBonus;
import back.Monster;
import back.MoveTypes;
import back.Point;
import back.StrengthBonus;

public class GameTests {

	Game game;
	FilterFileList filesCreated = new FilterArrayFileList();

	@Before
	public void setup() {
		game = new Game("./testBoard/boardForTest1");
	}

	@Test
	public void goodFunctionamientOfmovePlayerTest() {
		game.movePlayer(MoveTypes.LEFT);
		game.movePlayer(MoveTypes.LEFT);
		assertEquals(new Integer(4), game.getPlayer().getHealth());
		assertEquals(new Integer(1), game.getPlayer().getExperience());
		game.movePlayer(MoveTypes.LEFT);
		assertEquals(new Point(4, 3), game.getPlayer().getPosition());
		game.movePlayer(MoveTypes.LEFT);
		assertEquals(new Point(4, 2), game.getPlayer().getPosition());
		game.movePlayer(MoveTypes.LEFT);
		assertEquals(new Point(4, 2), game.getPlayer().getPosition());
		game.movePlayer(MoveTypes.LEFT);
		assertEquals(new Point(4, 2), game.getPlayer().getPosition());
	}

	@Test
	public void goodFunctionamientOfWiningWhenKillMonsterLevel3Test() {
		game.getPlayer().setMaxHealth(50);
		Bonus bonus = new LifeBonus(50);
		Bonus bonus2 = new StrengthBonus(50);
		bonus.giveBonus(game.getPlayer());
		bonus2.giveBonus(game.getPlayer());
		game.getPlayer().setPosition(new Point(8, 2));
		game.movePlayer(MoveTypes.LEFT);
	}

	@Test
	public void goodFunctionamientOfResetGameTest() {
		game.getPlayer().setMaxHealth(50);
		Bonus bonus = new LifeBonus(50);
		Bonus bonus2 = new StrengthBonus(50);
		bonus.giveBonus(game.getPlayer());
		bonus2.giveBonus(game.getPlayer());
		game.getPlayer().setPosition(new Point(4, 6));
		game.movePlayer(MoveTypes.UP);
		assertEquals(BloodyFloor.class, ((game.getBoard()[3][6])).getClass());
		game.resetGame();
		assertEquals(Monster.class, ((game.getBoard()[3][6])).getClass());
		assertFalse(((Monster) ((game.getBoard()[3][6]))).isDead());
		assertEquals(new Point(4, 4), game.getPlayer().getPosition());
	}

	@Test
	public void forWatchTheGameSavedTest() {
		game.saveGame();
		File file = new File("./savedGames");
		FilterFileList filterFileList = new FilterArrayFileList(file);
		filterFileList = filterFileList.filter("savedGame");
		int number = filterFileList.size();
		if (number > 1) {
			File f = new File("./savedGames/savedGame" + "(" + (number - 1)
					+ ")");
			assertTrue(f.exists());
			filesCreated.add(f);
		} else {
			File f = new File("./savedGames/savedGame");
			assertTrue(f.exists());
			filesCreated.add(f);
		}
	}

	@Test
	public void forWatchTheGameSavedWithPathTest() {
		File file = new File("./savedGames/testWithPath");
		game.saveGame(file);
		FilterFileList filterFileList = new FilterArrayFileList(
				file.getParentFile());
		filterFileList = filterFileList.filter(file.getName());
		int number = filterFileList.size();
		if (number > 1) {
			File f = new File(file.getPath() + "(" + (number - 1) + ")");
			assertTrue(f.exists());
			filesCreated.add(f);
		} else {
			File f = new File(file.getPath());
			assertTrue(f.exists());
			filesCreated.add(f);
		}
	}

	@Test
	public void goodLoadGameFunctionamientTest() {
		File file = new File("./savedGames/testWithPath");
		game.loadGame(file);
		assertEquals(new Integer(0), game.getPlayer().getExperience());
		assertEquals(new Point(4, 4), game.getPlayer().getPosition());
	}

	@Test
	public void deleteAllFilesUsedForTestings() {
		for (int i = 0; i < filesCreated.size(); i++) {
			filesCreated.get(i).delete();
		}
	}

	// TODO AYUDA PARA Q NO SE PIERDA LA REFERENCIA DE LOS FILES Q GUARDO EN FILESCREATED

}
