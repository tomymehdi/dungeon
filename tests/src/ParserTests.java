package src;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import parser.BoardParser;
import parser.CorruptedFileException;
import back.LifeBonus;
import back.Monster;
import back.MonsterTypes;
import back.Point;
import back.StrengthBonus;
import back.Wall;

public class ParserTests {

	BoardParser boardParser;

	@Before
	public void setup() {
		boardParser = new BoardParser("./testBoard/boardForTest1");
	}
	
	@Test(expected = CorruptedFileException.class)
	public void startPlayerPositionOverAMonsterTest() {
		new BoardParser("./testBoard/boardForTest2");
	}
	
	@Test(expected = CorruptedFileException.class)
	public void startPlayerPositionOverAWallTest() {
		new BoardParser("./testBoard/boardForTest3");
	}

	@Test(expected = CorruptedFileException.class)
	public void badPathPassedTest() {
		new BoardParser("./noExist");
	}

	@Test
	public void goodParseOfBoardDimensionTest() {
		assertEquals(new Point(10, 10), boardParser.getBoardDimension());
	}

	@Test
	public void goodParseOfBoardNameTest() {
		assertEquals("ejemplotablero", boardParser.getBoardName());
	}

	@Test
	public void goodParseOfPlayerPositionTest() {
		assertEquals(new Point(3, 3), boardParser.getPlayerPosition());
	}

	@Test
	public void goodParseOfAnyCellPositionTest() {
		assertEquals(Wall.class, boardParser.getBoard()[0][0].getClass());
		assertEquals(Wall.class, boardParser.getBoard()[9][0].getClass());
		assertEquals(Wall.class, boardParser.getBoard()[0][9].getClass());
		assertEquals(Wall.class, boardParser.getBoard()[9][9].getClass());
		assertEquals(StrengthBonus.class,
				boardParser.getBoard()[1][7].getClass());
		assertEquals(LifeBonus.class, boardParser.getBoard()[7][1].getClass());
		assertEquals(Monster.class, boardParser.getBoard()[4][6].getClass());
		assertEquals(Monster.class, boardParser.getBoard()[2][5].getClass());
		assertEquals(Monster.class, boardParser.getBoard()[1][3].getClass());
	}

	@Test
	public void goodParseOfMonsterTest() {
		assertEquals(MonsterTypes.DRAGON,
				((Monster) boardParser.getBoard()[8][1]).getMonsterType());
		assertEquals(new Integer(3),
				((Monster) boardParser.getBoard()[8][1]).getLevel());
	}

	@Test
	public void goodParseOfBonusTest() {
		assertEquals(new Integer(5),
				((LifeBonus) boardParser.getBoard()[7][1]).getLifeBonus());
		assertEquals(new Integer(3),
				((StrengthBonus) boardParser.getBoard()[1][7])
						.getStrengthBonus());
	}
	
	@Test
	public void boardWatchTest(){
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
