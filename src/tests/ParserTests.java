package tests;

import static org.junit.Assert.assertEquals;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import parser.BoardParserFromFile;
import parser.CorruptedFileException;
import back.BoardObtainer;
import back.Bonus;
import back.Monster;
import back.MonsterTypes;
import back.Point;
import back.Wall;

public class ParserTests {

	BoardObtainer boardParser;

	@Before
	public void setup() {
		boardParser = new BoardParserFromFile(new File(
				"./testBoard/boardForTest1.board"));
	}

	@Test(expected = CorruptedFileException.class)
	public void startPlayerPositionOverAMonsterTest() {
		new BoardParserFromFile(new File("./testBoard/boardForTest2.board"));
	}

	@Test(expected = CorruptedFileException.class)
	public void startPlayerPositionOverAWallTest() {
		new BoardParserFromFile(new File("./testBoard/boardForTest3.board"));
	}

	@Test
	public void mapWithoutSurroundingWalls() {
		BoardObtainer boardParser = new BoardParserFromFile(new File(
				"./testBoard/boardForTest4.board"));
		assertEquals(Wall.class, boardParser.getBoardElem(new Point(0, 0))
				.getClass());
		assertEquals(Wall.class, boardParser.getBoardElem(new Point(11, 0))
				.getClass());
		assertEquals(Wall.class, boardParser.getBoardElem(new Point(0, 11))
				.getClass());
		assertEquals(Wall.class, boardParser.getBoardElem(new Point(11, 11))
				.getClass());
	}

	@Test(expected = CorruptedFileException.class)
	public void positionOutOfBoardDimensionsTest() {
		new BoardParserFromFile(new File("./testBoard/boardForTest5.board"));
	}

	@Test(expected = CorruptedFileException.class)
	public void badPathPassedTest() {
		new BoardParserFromFile(new File("./noExist"));
	}

	@Test
	public void goodParseOfBoardDimensionTest() {
		assertEquals(new Point(12, 12), boardParser.getBoardDimension());
	}

	@Test
	public void goodParseOfBoardNameTest() {
		assertEquals("ejemplotablero", boardParser.getBoardName());
	}

	@Test
	public void goodParseOfPlayerPositionTest() {
		assertEquals(new Point(4, 4), boardParser.getPlayerPosition());
	}

	@Test
	public void goodParseOfAnyCellPositionTest() {
		assertEquals(Wall.class, boardParser.getBoard()[1][1].getClass());
		assertEquals(Wall.class, boardParser.getBoard()[10][1].getClass());
		assertEquals(Wall.class, boardParser.getBoard()[1][10].getClass());
		assertEquals(Wall.class, boardParser.getBoard()[10][10].getClass());
		assertEquals(Bonus.class,
				boardParser.getBoard()[2][8].getClass());
		assertEquals(Bonus.class, boardParser.getBoard()[8][2].getClass());
		assertEquals(Monster.class, boardParser.getBoard()[5][7].getClass());
		assertEquals(Monster.class, boardParser.getBoard()[3][6].getClass());
		assertEquals(Monster.class, boardParser.getBoard()[2][4].getClass());
	}

	@Test
	public void goodParseOfMonsterTest() {
		assertEquals(MonsterTypes.DRAGON,
				((Monster) boardParser.getBoard()[9][2]).getMonsterType());
		assertEquals(new Integer(3),
				((Monster) boardParser.getBoard()[9][2]).getLevel());
	}

	@Test
	public void goodParseOfBonusTest() {
		assertEquals(5,
				((Bonus) boardParser.getBoard()[8][2]).getAmountBonus());
		assertEquals(3,
				((Bonus) boardParser.getBoard()[2][8])
						.getAmountBonus());
	}

	@Test
	public void boardWatchTest() {
		String resp = "";
		for (int i = 0; i < boardParser.getBoardRows(); i++) {
			for (int j = 0; j < boardParser.getBoardColums(); j++) {
				resp += boardParser.getBoard()[i][j] + " ";
			}
			resp += "\n";
		}
		System.out.println(resp);
	}

}
