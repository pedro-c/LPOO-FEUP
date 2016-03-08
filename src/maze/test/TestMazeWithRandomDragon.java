package maze.test;
import static org.junit.Assert.*;

import java.awt.Point;
import org.junit.Test;

import maze.logic.Drake;
import maze.logic.Game;
import maze.logic.Game.GameStatus;
import maze.logic.Sword;

public class TestMazeWithRandomDragon {

	char[][] m1 = {
			{'X', 'X', 'X', 'X', 'X'},
			{'X', 'E', ' ', 'H', 'S'},
			{'X', ' ', 'X', ' ', 'X'},
			{'X', ' ', ' ', 'D', 'X'},
			{'X', 'X', 'X', 'X', 'X'}
			};
	
	char[][] m2 = {
			{'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'},
			{'X', 'E', ' ', 'H', ' ', ' ', ' ', 'S'},
			{'X', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
			{'X', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
			{'X', ' ', ' ', ' ', 'D', ' ', ' ', 'X'},
			{'X', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
			{'X', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
			{'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'}
			};
	
	char[][] m3 = {
			{'X', 'X', 'S', 'X', 'X'},
			{'X', ' ', 'D', ' ', 'X'},
			{'X', ' ', 'E', ' ', 'X'},
			{'X', ' ', 'H', ' ', 'X'},
			{'X', 'X', 'X', 'X', 'X'}
			};
	
	char[][] m4 = {
			{'X', 'X', 'X', 'X', 'X'},
			{'X', ' ', 'H', ' ', 'X'},
			{'X', ' ', 'E', ' ', 'X'},
			{'X', ' ', 'D', ' ', 'X'},
			{'X', 'X', 'S', 'X', 'X'}
			};
	
	char[][] m5 = {
			{'X', 'X', 'X', 'X', 'X'},
			{'X', ' ', ' ', ' ', 'X'},
			{'X', 'H', 'E', 'D', 'S'},
			{'X', ' ', ' ', ' ', 'X'},
			{'X', 'X', 'X', 'X', 'X'}
			};
	
	char[][] m6 = {
			{'X', 'X', 'X', 'X', 'X'},
			{'X', ' ', ' ', ' ', 'X'},
			{'S', 'D', 'E', 'H', 'X'},
			{'X', ' ', ' ', ' ', 'X'},
			{'X', 'X', 'X', 'X', 'X'}
			};
	
	char[][] m7 = {
			{'X', 'X', 'X', 'X', 'X', 'X'},
			{'X', 'E', ' ', ' ', ' ', 'X'},
			{'X', ' ', 'D', ' ', ' ', 'X'},
			{'X', ' ', ' ', ' ', ' ', 'X'},
			{'X', ' ', ' ', ' ', 'H', 'S'},
			{'X', 'X', 'X', 'X', 'X', 'X'}
			};
	
	char[][] m8 = {
			{'X', 'X', 'X', 'X', 'X'},
			{'X', 'E', 'X', ' ', 'S'},
			{'X', 'X', 'H', 'X', 'X'},
			{'X', ' ', 'X', 'D', 'X'},
			{'X', 'X', 'X', 'X', 'X'}
			};
	
	@Test(timeout=1000)
	public void triesToExitWithoutKillingDragon(){
		boolean outcome1 = false;			
		while (! outcome1) {
			Game g = new Game(1, 1, 1, 1, m1);
			g.moveHeroRight();
			if (g.checkExit(1, 4) == false)
				outcome1 = true;
			else
				fail("some error message");
		}
	}
	
	
	@Test(timeout=1000)
	public void heroDoesntMove(){
		boolean outcome1 = false, outcome2 = false, outcome3=false, outcome4 = false;			
		while (! outcome1 || !outcome2 || !outcome3 || !outcome4) {
			Game g = new Game(1, 1, 1, 1, m8);
			if (g.moveHeroDown() == false)
				outcome1 = true;
			if (g.moveHeroLeft() == false)
				outcome2 = true;
			if (g.moveHeroRight() == false)
				outcome3 = true;
			if (g.moveHeroUp() == false)
				outcome4 = true;
			else
				fail("some error message");
		}
	}
	
	@Test
	public void DoesntKillAllDrakes(){
		Game g = new Game(1, 1, 1, 1, m1);
		boolean outcome1 = false;
		Drake d1 = new Drake(3,1);
		Drake d2 = new Drake(2,1);
		Drake d3 = new Drake(3,2);
		g.Drakes.add(d1);
		g.Drakes.add(d2);
		g.Drakes.add(d3);
		g.nDrakes+=3;
		while (! outcome1) {
			g.moveHeroLeft();
			g.moveHeroLeft();
			if (!g.checksAllDrakes())
				outcome1 = true;
			else
				fail("some error message");
		}
	}
	
	@Test
	public void KillsAllDrakes(){
		Game g = new Game(1, 1, 1, 1, m1);
		boolean outcome1 = false;
		Drake d1 = new Drake(3,1);
		Drake d2 = new Drake(2,1);
		Drake d3 = new Drake(3,2);
		g.Drakes.add(d1);
		g.Drakes.add(d2);
		g.Drakes.add(d3);
		g.nDrakes+=3;
		while (! outcome1) {
			g.moveHeroLeft();
			g.moveHeroLeft();
			g.moveHeroDown();
			g.moveHeroDown();
			g.moveHeroRight();
			g.moveHeroRight();
			if (g.checksAllDrakes())
				outcome1 = true;
			else
				fail("some error message");
		}
	}
	
	
	@Test
	public void putsDrakeOverSword(){
		boolean outcome1 = false;			
		while (! outcome1) {
			Game g = new Game(1, 1, 1, 1, m7);
			Sword s = new Sword(2,2);
			g.updateMaze();
			g.Swords.add(s);
			g.nSwords++;
			g.updateMaze();
			if (g.Maze[2][2]=='F')
				outcome1 = true;
			else
				fail("some error message");
		}
	}
	
	
	@Test
	public void testMoveHeroUpVictory(){
		Game g = new Game(1, 1, 1, 1, m3);
		boolean outcome1 = false;
		while (! outcome1) {
			g.moveHeroUp();
			g.moveHeroUp();
			g.moveHeroUp();
			if (g.gameWon)
				outcome1 = true;
			else
				fail("some error message");
		}
	}
	
	@Test
	public void testMoveHeroDownVictory(){
		Game g = new Game(1, 1, 1, 1, m4);
		boolean outcome1 = false;
		while (! outcome1) {
			g.moveHeroDown();
			g.moveHeroDown();
			g.moveHeroDown();
			if (g.gameWon)
				outcome1 = true;
			else
				fail("some error message");
		}
	}

	
	@Test
	public void testMoveHeroRightVictory(){
		Game g = new Game(1, 1, 1, 1, m5);
		boolean outcome1 = false;
		while (! outcome1) {
			g.moveHeroRight();
			g.moveHeroRight();
			g.moveHeroRight();
			if (g.gameWon)
				outcome1 = true;
			else
				fail("some error message");
		}
	}

	
	@Test
	public void testMoveHeroLeftVictory(){
		Game g = new Game(1, 1, 1, 1, m6);
		boolean outcome1 = false;
		while (! outcome1) {
			g.moveHeroLeft();
			g.moveHeroLeft();
			g.moveHeroLeft();
			if (g.gameWon)
				outcome1 = true;
			else
				fail("some error message");
		}
	}



	@Test(timeout=1000)
	public void testMoveHeroToFreeCell() {
		Game g = new Game(3, 1, 1, 1, m1);
		Point p = new Point(1,2);
		assertEquals(new Point(1, 3), g.getHeroPosition());
		boolean outcome1 = false;
		while (! outcome1) {
			g.moveHeroLeft();
			if (p.equals(g.getHeroPosition()))
				outcome1 = true;
			else
				fail("some error message");
		}
	}
	
	@Test(timeout=1000)
	public void testDrakeMovement() {
		Point p1 = new Point(4,4);
		Point p2 = new Point(5,4);
		Point p3 = new Point(4,5);
		Point p4 = new Point(3,4);
		Point p5 = new Point(4,3);
		boolean outcome1 = false, outcome2 = false, outcome3=false, outcome4 = false, outcome5=false;			
		while (! outcome1 || !outcome2 || !outcome3 || !outcome4 || !outcome5) {
			Game g = new Game(3, 1, 1, 1, m2);
			g.moveDrake(g.Drakes.get(0));
			if (p1.equals(g.getDrakesPositions().get(0)))
				outcome1 = true;
			else if (p2.equals(g.getDrakesPositions().get(0)))
				outcome2 = true;
			else if (p3.equals(g.getDrakesPositions().get(0)))
				outcome3 = true;
			else if (p4.equals(g.getDrakesPositions().get(0)))
				outcome4 = true;
			else if (p5.equals(g.getDrakesPositions().get(0)))
				outcome5 = true;
			else
				fail("some error message");
		}
	}
	
	
	@Test(timeout=10000)
	public void testMoveHeroToWall() {
		Game g = new Game(3, 1, 1, 1, m1);
		Point p = new Point(1, 3);
		assertEquals(new Point(1, 3), g.getHeroPosition());
		boolean outcome1 = false;
		while (!outcome1) {
			g.moveHeroUp();
			if (p.equals(g.getHeroPosition()))
				outcome1 = true;
			else
				fail("some error message");
		}
	}
	
	@Test(timeout=1000)
	public void testMoveHeroToSword() {
		Game g = new Game(3, 1, 1, 1, m1);
		boolean outcome1 = false;
		while (! outcome1) {
			assertEquals(GameStatus.HeroUnarmed, g.getStatus());
			g.moveHeroLeft();
			g.moveHeroLeft();
			if (GameStatus.HeroArmed == g.getStatus())
				outcome1 = true;
			else
				fail("some error message");
		}
	}
	
	@Test(timeout=1000)
	public void testHeroDies() {
		boolean outcome1 = false;
		while (! outcome1) {
			Game g = new Game(1, 1, 1, 1, m1);
			g.moveHeroDown();							
			if (GameStatus.HeroDied == g.getStatus())
				outcome1 = true;
			else
				fail("some error message");
		}
	}
	
	@Test
	public void drakeAwakes(){
		boolean outcome1 = false, outcome2 = false;
		while (! outcome1 || !outcome2) {
			Game g = new Game(3, 1, 1, 1, m2);
			g.Drakes.get(0).asleep=true;
			g.moveDrake(g.Drakes.get(0));
			if (g.Drakes.get(0).asleep==false)
				outcome1 = true;
			else if(g.Drakes.get(0).asleep==true)
				outcome2 = true;
			else
				fail("some error message");
		}
	}
	
	@Test
	public void drakeAsleep(){
		boolean outcome1 = false;
		while (! outcome1) {
			Game g = new Game(1, 1, 1, 1, m1);
			g.Drakes.get(0).asleep=true;
			g.updateMaze();
			g.killDrake(3,3);
			if (g.Drakes.get(0).dead==false)
				outcome1 = true;
			else
				fail("some error message");
		}
	}
	
	

	
}
