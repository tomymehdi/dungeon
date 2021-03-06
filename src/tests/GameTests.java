package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;

import javax.swing.JOptionPane;

import org.junit.Before;
import org.junit.Test;

import parser.BoardParserFromFile;
import saveLoadImplementation.FilterArrayFileList;
import saveLoadImplementation.FilterFileList;
import saveLoadImplementation.LoadGameFromFile;
import saveLoadImplementation.SaveGameOnFile;
import back.BloodyFloor;
import back.Bonus;
import back.DungeonGameImp;
import back.DungeonGameListener;
import back.LoadGame;
import back.Monster;
import back.MoveTypes;
import back.Point;

public class GameTests {

	private DungeonGameImp game;

	@Before
	public void setup() {
		game = new DungeonGameImp(new BoardParserFromFile(new File(
				"./testBoard/boardForTest1.board")), new DungeonGameListener() {

			@Override
			public String playerNameRequest() {
				return "Tom";
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

			@Override
			public void executeWhenFight() {
			}

			@Override
			public void executeWhenLevelUp() {
			}
		});
	}

	@Test
	public void goodFunctionamientOfmovePlayerTest() {
		game.receiveMoveStroke(MoveTypes.LEFT);
		game.receiveMoveStroke(MoveTypes.LEFT);
		assertEquals(new Integer(4), game.getPlayer().getHealth());
		assertEquals(new Integer(1), game.getPlayer().getExperience());
		game.receiveMoveStroke(MoveTypes.LEFT);
		assertEquals(new Point(4, 3), game.getPlayer().getPosition());
		game.receiveMoveStroke(MoveTypes.RIGHT);
		assertEquals(new Point(4, 4), game.getPlayer().getPosition());
		game.receiveMoveStroke(MoveTypes.DOWN);
		assertEquals(new Point(5, 4), game.getPlayer().getPosition());
		game.receiveMoveStroke(MoveTypes.UP);
		assertEquals(new Point(4, 4), game.getPlayer().getPosition());
	}

	@Test
	public void goodFunctionamientOfWiningWhenKillMonsterLevel3Test() {
		game.getPlayer().winLife(40);
		Bonus bonus = new Bonus(new Point(7, 7), 4, 50);
		Bonus bonus2 = new Bonus(new Point(7, 7), 5, 50);
		bonus.giveBonus(game.getPlayer());
		bonus2.giveBonus(game.getPlayer());
		game.getPlayer().setPosition(new Point(8, 2));
		game.receiveMoveStroke(MoveTypes.LEFT);
	}

	@Test
	public void goodFunctionamientOfResetGameTest() {
		game.getPlayer().winLife(40);
		Bonus bonus = new Bonus(new Point(7, 7), 4, 50);
		Bonus bonus2 = new Bonus(new Point(7, 7), 5, 50);
		bonus.giveBonus(game.getPlayer());
		bonus2.giveBonus(game.getPlayer());
		game.getPlayer().setPosition(new Point(4, 6));
		game.receiveMoveStroke(MoveTypes.UP);
		assertEquals(BloodyFloor.class, ((game.getBoard()[3][6])).getClass());
		game.restart();
		assertEquals(Monster.class, ((game.getBoard()[3][6])).getClass());
		assertEquals(new Point(4, 4), game.getPlayer().getPosition());
	}

	@Test
	public void forWatchTheGameSavedTest() {
		File directory = new File("./savedGames");
		if (!directory.exists()) {
			directory.mkdir();
		}
		new SaveGameOnFile(game);
		File file = new File("./savedGames");
		FilterFileList filterFileList = new FilterArrayFileList(file);
		filterFileList = filterFileList.filter("savedGame");
		int number = filterFileList.size();
		if (number > 1) {
			File f = new File("./savedGames/savedGame" + "(" + (number - 1)
					+ ")" + ".board");
			assertTrue(f.exists());
			f.delete();
		} else {
			File f = new File("./savedGames/savedGame.board");
			assertTrue(f.exists());
			f.delete();
		}
		directory.delete();
	}

	@Test
	public void loadGameTest() {
		File directory = new File("./savedGames");
		if (!directory.exists()) {
			directory.mkdir();
		}
		File file = new File("./savedGames/testWithPath");
		new SaveGameOnFile(game, file);
		File file2 = new File(file.getPath() + ".board");
		LoadGame<DungeonGameImp> loadGame = new LoadGameFromFile<DungeonGameImp>(
				file2);
		DungeonGameImp game = loadGame.getGame(DungeonGameImp.class,
				new DungeonGameListener() {

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

					@Override
					public void executeWhenFight() {
					}

					@Override
					public void executeWhenLevelUp() {
					}
				});
		assertEquals(new Integer(0), game.getPlayer().getExperience());
		assertEquals(new Point(4, 4), game.getPlayer().getPosition());
		file2.delete();
		directory.delete();
	}

	@Test
	public void forWatchTheGameSavedWithPathTest() {
		File directory = new File("./savedGames");
		if (!directory.exists()) {
			directory.mkdir();
		}
		File file = new File("./savedGames/testWithPath");
		new SaveGameOnFile(game, file);
		FilterFileList filterFileList = new FilterArrayFileList(file
				.getParentFile());
		filterFileList = filterFileList.filter(file.getName());
		int number = filterFileList.size();
		if (number > 1) {
			File f = new File(file.getPath() + "(" + (number - 1) + ")"
					+ ".board");
			assertTrue(f.exists());
			f.delete();
		} else {
			File f = new File(file.getPath() + ".board");
			assertTrue(f.exists());
			f.delete();
		}
		directory.delete();
	}

}
