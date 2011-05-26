package src;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

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
		assertEquals(new Point(4,3),game.getPlayer().getPosition());
		game.movePlayer(MoveTypes.LEFT);
		assertEquals(new Point(4,2),game.getPlayer().getPosition());
		game.movePlayer(MoveTypes.LEFT);
		assertEquals(new Point(4,2),game.getPlayer().getPosition());
		game.movePlayer(MoveTypes.LEFT);
		assertEquals(new Point(4,2),game.getPlayer().getPosition());
	}
	
	@Test
	public void goodFunctionamientOfWiningWhenKillMonsterLevel3Test(){
		game.getPlayer().setMaxHealth(50);
		Bonus bonus = new LifeBonus(50);
		Bonus bonus2 = new StrengthBonus(50);
		bonus.giveBonus(game.getPlayer());
		bonus2.giveBonus(game.getPlayer());
		game.getPlayer().setPosition(new Point(8,2));
		game.movePlayer(MoveTypes.LEFT);
	}
	
	@Test
	public void goodFunctionamientOfResetGameTest(){
		game.getPlayer().setMaxHealth(50);
		Bonus bonus = new LifeBonus(50);
		Bonus bonus2 = new StrengthBonus(50);
		bonus.giveBonus(game.getPlayer());
		bonus2.giveBonus(game.getPlayer());
		game.getPlayer().setPosition(new Point(4,6));
		game.movePlayer(MoveTypes.UP);
		assertEquals(BloodyFloor.class, ((game.getBoardParser().getBoard()[3][6])).getClass());
		game.resetGame();
		assertEquals(Monster.class, ((game.getBoardParser().getBoard()[3][6])).getClass());
		assertFalse(((Monster)((game.getBoardParser().getBoard()[3][6]))).isDead());
		assertEquals(new Point(4,4), game.getPlayer().getPosition());
	}
}
