package src;

import static org.junit.Assert.assertEquals;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import parser.BoardParserFromFile;
import back.BoardObtainer;
import back.Bonus;
import back.Monster;
import back.MoveTypes;
import back.Player;
import back.PlayerData;
import back.Point;

public class PlayerTests {
	BoardObtainer boardParser;
	Player player;

	@Before
	public void setup() {
		boardParser = new BoardParserFromFile(new File(
				"./testBoard/boardForTest1"));
		player = new Player(new PlayerData("Tomas", 0, 0, 10, 10, 5,
				boardParser.getPlayerPosition()));
	}

	@Test
	public void goodFunctionamientPlayerMovementTest() {
		System.out.println(player.getPosition());
		assertEquals(new Point(4, 4), player.getPosition());
		player.move(MoveTypes.UP);
		assertEquals(new Point(3, 4), player.getPosition());
		player.move(MoveTypes.LEFT);
		assertEquals(new Point(3, 3), player.getPosition());
		player.move(MoveTypes.DOWN);
		assertEquals(new Point(4, 3), player.getPosition());
		player.move(MoveTypes.RIGHT);
		assertEquals(new Point(4, 4), player.getPosition());
	}

	@Test
	public void goodFunctionamientPlayerVsMonsterFightTest() {
		Monster monster = ((Monster) boardParser.getBoard()[5][7]);
		player.fightAnotherCharacter(monster);
		assertEquals(
				new Integer(player.getMaxHealth() - monster.getStrength()),
				player.getHealth());
		assertEquals(
				new Integer(monster.getMaxHealth() - player.getStrength()),
				monster.getHealth());
	}

	@Test
	public void goodFunctionamientPlayerEarningBonusTest() {
		player.hited(9);
		((Bonus) boardParser.getBoard()[8][2]).giveBonus(player);
		((Bonus) boardParser.getBoard()[2][8]).giveBonus(player);
		assertEquals(new Integer(6), player.getHealth());
		assertEquals(new Integer(8), player.getStrength());

	}

}
