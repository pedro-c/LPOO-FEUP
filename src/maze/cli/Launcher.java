package maze.cli;
import maze.logic.*;
import java.util.*;

public class Launcher {

	public static Scanner read = new Scanner(System.in);

	public static void clearScreen() {

		for(int clear = 0; clear < 1000; clear++) {
			System.out.println("\n") ;
		}

	}

	public static int menu()
	{
		int mode;

		while(true)
		{
			System.out.println("Chose a game mode: \n1. Static Dragon\n2. Moving Dragon\n3. Moving and Sleeping Dragon");
			mode = read.nextInt();

			if(mode > 3 || mode < 1)
			{
				System.out.println("Invalid Mode! Chose from 1-3\n");
				clearScreen();
			}
			else
				break;
		}
		clearScreen();
		return mode;
	}

	public static char getMove()
	{
		char movement;

		System.out.println("Move the Hero using: ASWD");

		movement = read.next().charAt(0);
		movement = Character.toLowerCase(movement);

		return movement;
	}

	public static void drawMaze(Game g)
	{
		for (int line = 0; line < g.Maze.length; line++) {
			for (int col = 0; col < g.Maze.length; col++) {
				System.out.print(g.Maze[line][col]);
				System.out.print(" ");
			}
			System.out.println();
		}
	}
	
	public static void main(String[] args) {

		int mode = menu();
		//a alterar para maze aleatorio
		char[][] m1 = {
				{'X', 'X', 'X', 'X', 'X'},
				{'X', 'E', ' ', 'H', 'S'},
				{'X', ' ', 'X', ' ', 'X'},
				{'X', ' ', ' ', 'D', 'X'},
				{'X', 'X', 'X', 'X', 'X'}
				};
		
		MazeBuilder mb = new MazeBuilder(11, 1, 1);
		
		char[][] mt = mb.maze;
		
		Game g = new Game(mode, 1, 1, 1, mt);


		while(!g.gameLost && !g.gameWon){
			clearScreen();
			drawMaze(g);
			char m = getMove();
			switch(m){
				case 'w':
					g.moveHeroUp();
					break;
				case 's':
					g.moveHeroDown();
					break;
				case 'a':
					g.moveHeroLeft();
					break;
				case 'd':
					g.moveHeroRight();
					break;
			}	
			g.updateMaze();
			//g.updateMaze(m);
		}
		read.close();
		clearScreen();
		if(g.gameWon){
			System.out.print("Congrats, you won!");
		}else{
			System.out.print("You Lost!");
		}
	}

}
