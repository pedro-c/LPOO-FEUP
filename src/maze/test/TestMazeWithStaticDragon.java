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
		g.updateHero('a');
		assertEquals(new Point(1, 2), g.getHeroPosition());
	}
	@Test
	public void testMoveHeroToWall() {
		Game g = new Game(1, 1, 1, 1, m1);
		assertEquals(new Point(1, 3), g.getHeroPosition());
		g.updateHero('w');
		assertEquals(new Point(1, 3), g.getHeroPosition());
	}
	@Test
	public void testMoveHeroToSword() {
		Game g = new Game(1, 1, 1, 1, m1);
		assertEquals(GameStatus.HeroUnarmed, g.getStatus());
		g.updateHero('a');
		g.updateHero('a');
		assertEquals(GameStatus.HeroArmed, g.getStatus());
	}
	
	
	@Test
	public void testHeroDies() {
		Game g = new Game(1, 1, 1, 1, m1);
		assertEquals(GameStatus.HeroUnarmed, g.getStatus());
		g.updateHero('s');
		assertEquals(GameStatus.HeroUnarmed, g.getStatus());
	}
	
	@Test
	public void testHeroKills() {
		Game g = new Game(1, 1, 1, 1, m1);
		assertEquals(false, g.getDrakeStatus(3, 3));
		g.updateHero('a');
		g.updateHero('a');
		g.updateHero('s');
		g.updateHero('s');
		g.updateMaze('d');
		assertEquals(true, g.getDrakeStatus(3, 3));
	}

}
