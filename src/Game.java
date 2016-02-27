import java.util.ArrayList;

public class Game {

	private int width = 10, height = 10;
	private int nDrakes = 1;
	private int nSwords = 1;
	private int nExits = 1;
	private Character[][] Maze = new Character[width][height];
	Hero hero = new Hero (1,1,'H');
	
	
	ArrayList<Drake> Drakes = new ArrayList<Drake>(nDrakes);
	ArrayList<Sword> Swords = new ArrayList<Sword>(nSwords);
	ArrayList<Exit> Exits = new ArrayList<Exit>(nExits);

	public void drawMaze() {
		for (int line = 0; line < height; line++) {
			for (int col = 0; col < width; col++) {
				System.out.print(Maze[line][col]);
				System.out.print(" ");
			}
			System.out.println();
		}
	}
	

	public void createMaze() {
		
		// Temporario ate criarmos o labirinto de forma aleatoria
		
		Drake d1 = new Drake(3,1,false);
		Exit e1 = new Exit (5,9);
		Sword s1 = new Sword (8,2);
		
		Exits.add(e1);
		Drakes.add(d1);
		Swords.add(s1);
		

		// Inicia um tabuleiro a branco
		for (int i = 0; i < width; i++) {
			for (int x = 0; x < height; x++) {
				Maze[i][x] = ' ';
			}
		}
		
		// Colocar as paredes exteriores

		for (int i = 0; i < width; i++) {
			Maze[0][i] = 'X';
			Maze[height - 1][i] = 'X';
		}
		for (int i = 0; i < height; i++) {
			Maze[i][0] = 'X';
			Maze[i][width - 1] = 'X';
		}

		// Colocar Saidas

		for (int i = 0; i < nExits; i++)
			Maze[Exits.get(i).line][Exits.get(i).col] = 'S';

		// Colocar Paredes Interiores -> Temporário

		Maze[2][2] = 'X';
		Maze[3][2] = 'X';
		Maze[4][2] = 'X';
		Maze[6][2] = 'X';
		Maze[7][2] = 'X';
		Maze[8][2] = 'X';

		Maze[2][3] = 'X';
		Maze[3][3] = 'X';
		Maze[4][3] = 'X';
		Maze[6][3] = 'X';
		Maze[7][3] = 'X';
		Maze[8][3] = 'X';

		Maze[2][5] = 'X';
		Maze[3][5] = 'X';
		Maze[4][5] = 'X';
		Maze[6][5] = 'X';
		Maze[7][5] = 'X';

		Maze[2][7] = 'X';
		Maze[3][7] = 'X';
		Maze[4][7] = 'X';
		Maze[5][7] = 'X';
		Maze[6][7] = 'X';
		Maze[7][7] = 'X';

		// Colocar Dragões
		for (int i = 0; i < nDrakes; i++)
			Maze[Drakes.get(i).line][Drakes.get(i).col] = 'D';

		// Colocar Espadas
		for (int i = 0; i < nSwords; i++)
			Maze[Swords.get(i).line][Swords.get(i).col] = 'E';

		// Colocar Herói
		Maze[hero.line][hero.col] = 'H';

	}
	
	public void updateMaze() {
		
		
	}

}
