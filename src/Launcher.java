
public class Launcher {

	public static void main(String[] args) {

		Game g = new Game();
		
		g.createMaze();
		
		while(!g.endGame){
			g.clearScreen();
			g.drawMaze();
			g.updateMaze();
		}
		g.read.close();
	}

}
