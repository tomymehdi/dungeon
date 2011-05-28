package src;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;

import javax.swing.JOptionPane;

import loadAndSave.FilterArrayFileList;
import loadAndSave.FilterFileList;
import loadAndSave.LoadGameFromFile;
import loadAndSave.SaveGameOnFile;

import org.junit.Before;
import org.junit.Test;

import parser.BoardParserFromFile;
import back.BloodyFloor;
import back.Bonus;
import back.DungeonGame;
import back.DungeonGameListener;
import back.LifeBonus;
import back.LoadGame;
import back.Monster;
import back.MoveTypes;
import back.Point;
import back.StrengthBonus;

public class GameTests {

	private DungeonGame game;

	@Before
	public void setup() {
		game = new DungeonGame(new BoardParserFromFile(new File(
				"./testBoard/boardForTest1")));
		game.addGameListener(new DungeonGameListener() {

			@Override
			public String playerNameRequest() {
				String name = null;
				while (name == null || name.isEmpty()) {
					name = JOptionPane.showInputDialog("Player name");
				}
				return name;
			}

			@Override
			public void executeWhenPlayerMoves(MoveTypes moveType) {
			}

			@Override
			public void executeWhenGameWinned() {
			}

			@Override
			public void executeWhenGameLoosed() {
			}

			@Override
			public void executeWhenCharacterDie(Point p) {
			}

			@Override
			public void executeWhenBonusGrabed(Point p) {
			}
		});
	}

	@Test
	public void goodFunctionamientOfmovePlayerTest() {
		game.movePlayer(MoveTypes.LEFT);
		game.movePlayer(MoveTypes.LEFT);
		assertEquals(new Integer(4), game.getPlayer().getHealth());
		assertEquals(new Integer(1), game.getPlayer().getExperience());
		game.movePlayer(MoveTypes.LEFT);
		assertEquals(new Point(4, 3), game.getPlayer().getPosition());
		game.movePlayer(MoveTypes.RIGHT);
		assertEquals(new Point(4, 4), game.getPlayer().getPosition());
		game.movePlayer(MoveTypes.DOWN);
		assertEquals(new Point(5, 4), game.getPlayer().getPosition());
		game.movePlayer(MoveTypes.UP);
		assertEquals(new Point(4, 4), game.getPlayer().getPosition());
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
		game.restartGame();
		assertEquals(Monster.class, ((game.getBoard()[3][6])).getClass());
		assertFalse(((Monster) ((game.getBoard()[3][6]))).isDead());
		assertEquals(new Point(4, 4), game.getPlayer().getPosition());
	}

	@Test
	public void forWatchTheGameSavedTest() {
		new SaveGameOnFile(game);
		File file = new File("./savedGames");
		FilterFileList filterFileList = new FilterArrayFileList(file);
		filterFileList = filterFileList.filter("savedGame");
		int number = filterFileList.size();
		if (number > 1) {
			File f = new File("./savedGames/savedGame" + "(" + (number - 1)
					+ ")");
			assertTrue(f.exists());
			f.delete();
		} else {
			File f = new File("./savedGames/savedGame");
			assertTrue(f.exists());
			f.delete();
		}
	}

	@Test
	public void loadGameTest() {
		File file = new File("./savedGames/testWithPath");
		new SaveGameOnFile(game, file);
		LoadGame loadGame = new LoadGameFromFile(file);
		DungeonGame game = loadGame.getGame();
		assertEquals(new Integer(0), game.getPlayer().getExperience());
		assertEquals(new Point(4, 4), game.getPlayer().getPosition());
		file.delete();
	}

	@Test
	public void forWatchTheGameSavedWithPathTest() {
		File file = new File("./savedGames/testWithPath");
		new SaveGameOnFile(game, file);
		FilterFileList filterFileList = new FilterArrayFileList(
				file.getParentFile());
		filterFileList = filterFileList.filter(file.getName());
		int number = filterFileList.size();
		if (number > 1) {
			File f = new File(file.getPath() + "(" + (number - 1) + ")");
			assertTrue(f.exists());
			f.delete();
		} else {
			File f = new File(file.getPath());
			assertTrue(f.exists());
			f.delete();
		}
	}

}
