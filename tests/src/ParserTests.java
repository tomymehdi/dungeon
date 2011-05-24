package src;

import static org.junit.Assert.assertEquals;

import java.awt.Point;

import org.junit.Before;
import org.junit.Test;

import parser.BoardParser;
import parser.CorruptedFileException;
import back.LifeBonus;
import back.Monster;
import back.StrengthBonus;
import back.WallCell;

public class ParserTests {

	BoardParser boardParser;

	@Before
	public void setup() {
		boardParser = new BoardParser("./testBoard/boardForTest");
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
		assertEquals(new Point(3,3), boardParser.getPlayerPosition());
	}
	
	@Test
	public void goodParseOfAnyCellPositionTest(){
		assertEquals(WallCell.class,boardParser.getBoard()[0][0].getClass());
		assertEquals(WallCell.class,boardParser.getBoard()[9][0].getClass());
		assertEquals(WallCell.class,boardParser.getBoard()[0][9].getClass());
		assertEquals(WallCell.class,boardParser.getBoard()[9][9].getClass());
		assertEquals(StrengthBonus.class,boardParser.getBoard()[1][7].getClass());
		assertEquals(LifeBonus.class,boardParser.getBoard()[7][1].getClass());
		assertEquals(Monster.class,boardParser.getBoard()[4][6].getClass());
		assertEquals(Monster.class,boardParser.getBoard()[2][5].getClass());
		assertEquals(Monster.class,boardParser.getBoard()[1][3].getClass());
	}

}
