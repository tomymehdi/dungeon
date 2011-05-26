package src;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import parser.BoardParser;
import back.Bonus;
import back.Monster;
import back.MoveTypes;
import back.Player;
import back.Point;

public class PlayerTests {
	BoardParser boardParser;
	Player player;

	@Before
	public void setup() {
		boardParser = new BoardParser("./testBoard/boardForTest1");
		player = new Player("Tomas",boardParser.getPlayerPosition(), 10, 5);
	}

	@Test
	public void goodFunctionamientPlayerMovementTest() {
		assertEquals(new Point(4,4), player.getPosition());
		player.move(MoveTypes.UP);
		assertEquals(new Point(3,4), player.getPosition());
		player.move(MoveTypes.LEFT);
		assertEquals(new Point(3,3), player.getPosition());
		player.move(MoveTypes.DOWN);
		assertEquals(new Point(4,3), player.getPosition());
		player.move(MoveTypes.RIGHT);
		assertEquals(new Point(4,4), player.getPosition());
	}
	
	@Test
	public void goodFunctionamientPlayerVsMonsterFightTest(){
		Monster monster = ((Monster)boardParser.getBoard()[5][7]);
		player.fightAnotherCharacter(monster);
		assertEquals(new Integer(player.getMaxHealth()-monster.getStrength()), player.getHealth());
		assertEquals(new Integer(monster.getMaxHealth()-player.getStrength()), monster.getHealth());
	}
	
	@Test
	public void goodFunctionamientPlayerEarningBonusTest(){
		player.setHealth(1);
		((Bonus)boardParser.getBoard()[8][2]).giveBonus(player);
		((Bonus)boardParser.getBoard()[2][8]).giveBonus(player);
		assertEquals(new Integer(6), player.getHealth());
		assertEquals(new Integer(8), player.getStrength());
		
	}
	
}
