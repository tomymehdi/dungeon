package src;

import static org.junit.Assert.assertEquals;

import java.io.File;

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
		boardParser = new BoardParser(new File("./testBoard/boardForTest1"));
	}
	
	@Test(expected = CorruptedFileException.class)
	public void startPlayerPositionOverAMonsterTest() {
		new BoardParser(new File("./testBoard/boardForTest2"));
	}
	
	@Test(expected = CorruptedFileException.class)
	public void startPlayerPositionOverAWallTest() {
		new BoardParser(new File("./testBoard/boardForTest3"));
	}
	
	@Test
	public void mapWithoutSurroundingWalls() {
		BoardParser boardParser = new BoardParser(new File("./testBoard/boardForTest4"));
		assertEquals(Wall.class, boardParser.getBoardElem(new Point(0,0)).getClass());
		assertEquals(Wall.class, boardParser.getBoardElem(new Point(11,0)).getClass());
		assertEquals(Wall.class, boardParser.getBoardElem(new Point(0,11)).getClass());
		assertEquals(Wall.class, boardParser.getBoardElem(new Point(11,11)).getClass());
	}

	@Test(expected = CorruptedFileException.class)
	public void badPathPassedTest() {
		new BoardParser(new File("./noExist"));
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
		assertEquals(StrengthBonus.class,
				boardParser.getBoard()[2][8].getClass());
		assertEquals(LifeBonus.class, boardParser.getBoard()[8][2].getClass());
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
		assertEquals(new Integer(5),
				((LifeBonus) boardParser.getBoard()[8][2]).getLifeBonus());
		assertEquals(new Integer(3),
				((StrengthBonus) boardParser.getBoard()[2][8])
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
