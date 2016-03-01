import java.util.*;

public class Launcher {

	public static Scanner read = new Scanner(System.in);
	
	public static char getMove()
	{
		char movement;

		System.out.println("Mova o Heroi usando: ASWD");

		movement = read.next().charAt(0);
		movement = Character.toLowerCase(movement);
		
		return movement;
	}
	
	public static void main(String[] args) {

		Game g = new Game();
		
		g.createMaze();
		
		while(!g.gameLost && !g.gameWon){
			g.clearScreen();
			g.drawMaze();
			char m = getMove();
			g.updateMaze(m);
		}
		read.close();
		g.clearScreen();
		if(g.gameWon){
			System.out.print("Congrats, you won!");
		}else{
			System.out.print("You Lost!");
		}
	}

}
