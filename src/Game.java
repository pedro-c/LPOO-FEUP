import java.util.ArrayList;
import java.util.Scanner;

public class Game {

	public boolean gameLost = false;
	public boolean gameWon = false;
	private int width = 10, height = 10;
	private int nDrakes = 1;
	private int nSwords = 1;
	private int nExits = 1;
	private Character[][] Maze = new Character[width][height];
	public Scanner read = new Scanner(System.in);
	Hero hero = new Hero(1, 1, 'H');

	ArrayList<Drake> Drakes = new ArrayList<Drake>(nDrakes);
	ArrayList<Sword> Swords = new ArrayList<Sword>(nSwords);
	ArrayList<Exit> Exits = new ArrayList<Exit>(nExits);
	
	public void clearScreen() {

		for(int clear = 0; clear < 1000; clear++) {
		    System.out.println("\n") ;
		}

	}

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

		Drake d1 = new Drake(3, 1, false);
		Exit e1 = new Exit(5, 9);
		Sword s1 = new Sword(8, 1, true);

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
		Maze[hero.line][hero.col] = hero.symbol;

	}

	public void updateSword(int col, int line, boolean draw) {
		for (int i = 0; i < nSwords; i++) {
			if (Swords.get(i).col == col && Swords.get(i).line == line) {
				Swords.get(i).draw = draw;
			}

		}
	}

	public void updateHero() {

		char movement;

		System.out.println("Mova o Heroi usando: ASWD");
		
		movement = read.next().charAt(0);
		movement = Character.toLowerCase(movement);

		switch (movement) {
		case 'a':
			if (Maze[hero.line][hero.col - 1] == 'S' && hero.symbol == 'A') {
				gameWon = true;
			} 			
			if (Maze[hero.line][hero.col - 1] == ' ') {
				hero.col -= 1;
			} else if (Maze[hero.line][hero.col - 1] == 'E') {
				hero.symbol = 'A';
				hero.col -= 1;
				updateSword(hero.col, hero.line, false);
			} 
			//checks for drakes
			if (Maze[hero.line][hero.col - 1] == 'D'){
				if(hero.symbol == 'A'){
					updateDrakes(hero.col-1,hero.line,true);
				}
				else{
					gameLost=true;
				}
			}
			break;
		case 'd':
			if (Maze[hero.line][hero.col + 1] == 'S' && hero.symbol == 'A') {
				gameWon = true;
			}
			if (Maze[hero.line][hero.col + 1] == ' ') {
				hero.col += 1;
			} else if (Maze[hero.line][hero.col + 1] == 'E') {
				hero.symbol = 'A';
				hero.col += 1;
				updateSword(hero.col, hero.line, false);
			}
			//checks for drakes
			if(Maze[hero.line][hero.col+1] == 'D'){
				if(hero.symbol == 'A'){
					updateDrakes(hero.col+1,hero.line,true);
				}
				else{
					gameLost=true;
				}
			}
			break;
		case 's':
			if (Maze[hero.line + 1][hero.col] == 'S' && hero.symbol == 'A') {
				gameWon = true;
			}
			if (Maze[hero.line + 1][hero.col] == ' ') {
				hero.line += 1;
			} else if (Maze[hero.line + 1][hero.col] == 'E') {
				hero.symbol = 'A';
				hero.line += 1;
				updateSword(hero.col, hero.line, false);
			}
			//checks for drakes
			if( Maze[hero.line+1][hero.col] == 'D'){
				if(hero.symbol == 'A'){
					updateDrakes(hero.col,hero.line+1,true);
				}
				else{
					gameLost=true;
				}
			}
			break;
		case 'w':
			if (Maze[hero.line - 1][hero.col] == 'S' && hero.symbol == 'A') {
				gameWon = true;
			}
			if (Maze[hero.line - 1][hero.col] == ' ') {
				hero.line -= 1;
			} else if (Maze[hero.line - 1][hero.col] == 'E') {
				hero.symbol = 'A';
				hero.line -= 1;
				updateSword(hero.col, hero.line, false);
			}
			//checks for drakes
			if(Maze[hero.line-1][hero.col] == 'D'){
				if(hero.symbol == 'A'){
					updateDrakes(hero.col,hero.line-1,true);
				}
				else{
					gameLost=true;
				}
			}
			break;
		}

	}

	public void updateDrakes(int col, int line, boolean dead) {
		for (int i = 0; i < nDrakes; i++) {
			if (Drakes.get(i).col == col && Drakes.get(i).line == line) {
				Drakes.get(i).dead = dead;
			}
		}
	}


	public void updateMaze() {
		
		//cleans the last hero's position
		Maze[hero.line][hero.col] = ' ';
		
		//moves and updates the hero and checks for dragons and swords
		updateHero();
		
		
		// Colocar Dragões
		for (int i = 0; i < nDrakes; i++){
			if(Drakes.get(i).dead){
				Maze[Drakes.get(i).line][Drakes.get(i).col] = ' ';
			}
			else{
				Maze[Drakes.get(i).line][Drakes.get(i).col] = 'D';
			}
		}
			

		// Colocar Espadas
		for (int i = 0; i < nSwords; i++) {
			if (Swords.get(i).draw) {
				Maze[Swords.get(i).line][Swords.get(i).col] = 'E';
			} 
			else {
				Maze[Swords.get(i).line][Swords.get(i).col] = ' ';
			}
		}

		// Colocar Herói
		Maze[hero.line][hero.col] = hero.symbol;

	}

}
