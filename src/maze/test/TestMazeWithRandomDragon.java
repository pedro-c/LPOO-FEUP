package maze.test;
import static org.junit.Assert.assertEquals;
import java.awt.Point;
import org.junit.Test;
import maze.logic.Game;

public class TestMazeWithRandomDragon {

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
	
}
