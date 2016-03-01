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

	public static void main(String[] args) {

		int mode = menu();
		Game g = new Game();

		g.createMaze(mode);

		while(!g.gameLost && !g.gameWon){
			clearScreen();
			g.drawMaze();
			char m = getMove();
			g.updateMaze(m);
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
