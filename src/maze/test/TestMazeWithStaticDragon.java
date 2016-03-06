package maze.test;
import static org.junit.Assert.*;
import java.awt.Point;
import org.junit.Test;
import maze.logic.*;
import maze.logic.Game.GameStatus;

public class TestMazeWithStaticDragon {

	char[][] m1 = {
			{'X', 'X', 'X', 'X', 'X'},
			{'X', 'E', ' ', 'H', 'S'},
			{'X', ' ', 'X', ' ', 'X'},
			{'X', ' ', ' ', 'D', 'X'},
			{'X', 'X', 'X', 'X', 'X'}
			};


	@Test
	public void testMoveHeroToFreeCell() {
		Game g = new Game(1, 1, 1, 1, m1);
		assertEquals(new Point(1, 3), g.getHeroPosition());
		g.moveHeroLeft();
		assertEquals(new Point(1, 2), g.getHeroPosition());
	}
	@Test
	public void testMoveHeroToWall() {
		Game g = new Game(1, 1, 1, 1, m1);
		assertEquals(new Point(1, 3), g.getHeroPosition());
		g.moveHeroUp();
		assertEquals(new Point(1, 3), g.getHeroPosition());
	}
	@Test
	public void testMoveHeroToSword() {
		Game g = new Game(1, 1, 1, 1, m1);
		assertEquals(GameStatus.HeroUnarmed, g.getStatus());
		g.moveHeroLeft();
		g.moveHeroLeft();
		assertEquals(GameStatus.HeroArmed, g.getStatus());
	}
	
	
	@Test
	public void testHeroDies() {
		Game g = new Game(1, 1, 1, 1, m1);
		assertEquals(GameStatus.HeroUnarmed, g.getStatus());
		g.moveHeroDown();
		assertEquals(GameStatus.HeroDied, g.getStatus());
	}
	
	@Test
	public void testHeroKills() {
		Game g = new Game(1, 1, 1, 1, m1);
		assertEquals(false, g.getDrakeStatus(3, 3));
		g.moveHeroLeft();
		g.moveHeroLeft();
		g.moveHeroDown();
		g.moveHeroDown();
		g.moveHeroRight();
		assertEquals(true, g.getDrakeStatus(3, 3));
	}
	
	@Test
	public void testVictory() {
		Game g = new Game(1, 1, 1, 1, m1);
		assertEquals(false, g.gameWon);
		g.moveHeroLeft();
		g.moveHeroLeft();
		g.moveHeroDown();
		g.moveHeroDown();
		g.moveHeroRight();
		g.moveHeroRight();
		g.moveHeroUp();
		g.moveHeroUp();
		g.moveHeroRight();		
		assertEquals(true, g.gameWon);
	}
	
	@Test
	public void testUnsuccessfulExitWithoutSword() {
		Game g = new Game(1, 1, 1, 1, m1);
		assertEquals(false, g.gameWon);
		g.moveHeroRight();
		assertEquals(false, g.gameWon);
	}
	
	@Test
	public void testUnsuccessfulExiWithSword() {
		Game g = new Game(1, 1, 1, 1, m1);
		assertEquals(false, g.gameWon);
		g.moveHeroLeft();
		g.moveHeroLeft();
		g.moveHeroRight();
		g.moveHeroRight();
		g.moveHeroRight();
		assertEquals(false, g.gameWon);
	}
	

}
