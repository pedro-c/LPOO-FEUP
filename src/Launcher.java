
public class Launcher {

	public static void main(String[] args) {

		Game g = new Game();
		
		g.createMaze();
		
		while(!g.gameLost && !g.gameWon){
			g.clearScreen();
			g.drawMaze();
			g.updateMaze();
		}
		g.read.close();
		g.clearScreen();
		if(g.gameWon){
			System.out.print("Congrats, you won!");
		}else{
			System.out.print("You Lost!");
		}
	}

}
