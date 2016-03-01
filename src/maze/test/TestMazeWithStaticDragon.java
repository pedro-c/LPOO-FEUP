package maze.test;
import static org.junit.Assert.*;
import java.awt.Point;
import org.junit.Test;
import maze.logic.*;
import maze.logic.Game.GameStatus;

public class TestMazeWithStaticDragon {

	char[][] m1 = {
			{'X', 'X', 'X', 'X', 'X'},
			{'X', ' ', ' ', 'H', 'S'},
			{'X', ' ', 'X', ' ', 'X'},
			{'X', 'E', ' ', 'D', 'X'},
			{'X', 'X', 'X', 'X', 'X'}
			};

	@Test
	
	public void testMoveHeroToFreeCell() {
		Game g = new Game(1, 1, 1, 1, m1);
		assertEquals(new Point(1, 3), g.getHeroPosition());
		g.updateHero('a');
		assertEquals(new Point(1, 2), g.getHeroPosition());
	}
	
	public void testHeroDies() {
		Game g = new Game(1, 1, 1, 1, m1);
		assertEquals(GameStatus.HeroUnarmed, g.getStatus());
		g.updateHero('s');
		assertEquals(GameStatus.HeroUnarmed, g.getStatus());
	}

}
