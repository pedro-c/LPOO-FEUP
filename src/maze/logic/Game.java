package maze.logic;
import java.util.*;

public class Game {

	public boolean gameLost = false;
	public boolean gameWon = false;
	private int mode;
	private int width = 10, height = 10;
	private int nDrakes = 1;
	private int nSwords = 1;
	private int nExits = 1;
	private Character[][] Maze = new Character[width][height];
	Hero hero = new Hero(1, 1, 'H');

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

	public void createMaze(int m) {

		// Temporario ate criarmos o labirinto de forma aleatoria

		mode = m;
		Drake d1 = new Drake(3, 1, false);
		Exit e1 = new Exit(5, 9);
		Sword s1 = new Sword(4, 1, true);

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

	public boolean checkExit(int line, int col) {
		boolean ret = false;

		for(int i = 0; i < nExits; i++)
			if(Exits.get(i).col == col && Exits.get(i).line == line)
				ret = Exits.get(i).open;

		return ret;
	}

	public void updateSword(int col, int line, boolean draw) {
		for (int i = 0; i < nSwords; i++) {
			if (Swords.get(i).col == col && Swords.get(i).line == line) {
				Swords.get(i).draw = draw;
			}

		}
	}

	public boolean updateHero(char movement) {

		int newLine = hero.line;
		int newCol = hero.col;

		switch (movement) {
		case 'a':
			newCol -= 1;
			break;
		case 'd':
			newCol += 1;
			break;
		case 's':
			newLine +=1;
			break;
		case 'w':
			newLine -=1;
			break;
		}

		if (Maze[newLine][newCol] == 'S' && checkExit(newLine, newCol)) {
			gameWon = true;
		} 			
		else if (Maze[newLine][newCol] == ' ') {
			hero.line = newLine;
			hero.col = newCol;
		} 
		else if (Maze[newLine][newCol] == 'E') {
			hero.symbol = 'A';
			hero.line = newLine;
			hero.col = newCol;
			updateSword(newCol, newLine, false);
		} 
		else
			return false; //Invalid Movement

		return true; //Hero moved successfully

	}

	public void killDrake(int line, int col) {

		if(hero.symbol != 'A' && Maze[line][col] == 'D')
		{
			gameLost = true;
			return; //No need to do anything else, the game is lost
		}

		boolean allDead = true;
		for (int i = 0; i < nDrakes; i++) {
			if (Drakes.get(i).col == col && Drakes.get(i).line == line) 
				Drakes.get(i).dead = true;
			else if(Drakes.get(i).dead == false)
				allDead = false;
		}
		if(allDead)
			for (int i = 0; i < nExits; i++)
				Exits.get(i).open = true;
	}

	public void moveDrake(Drake d)
	{

		if(mode == 1) //does not move --> do nothing
			return;

		int maxRandInt = 3 + mode;

		Random rand = new Random();
		int drakeMove = rand.nextInt(maxRandInt);
		int nextLine = d.line;
		int nextCol = d.col;

		if(d.asleep == false)
			switch(drakeMove)
			{
			case 0:
				break;
			case 1:
				nextCol += 1;
				break;
			case 2:
				nextCol -= 1;
				break;
			case 3:
				nextLine += 1;
				break;
			case 4:
				nextLine -= 1;
				break;
			case 5:
				d.asleep = true;
			}
		else
			if(drakeMove < 3) //0, 1, 2
				d.asleep = false; // 50/50 chance that he wakes up

		if(Maze[nextLine][nextCol] == ' ' || Maze[nextLine][nextCol] == 'E'){
			d.line = nextLine;
			d.col = nextCol;
		}

	}

	public void updateMaze(char movement) {

		//cleans the last hero's position

		Maze[hero.line][hero.col] = ' ';

		//moves and updates the hero and checks for dragons and swords
		if(!updateHero(movement))
		{
			Maze[hero.line][hero.col] = hero.symbol;
			return; //If the movement was invalid, no need to update
		}


		//checks for drakes adjacent to the hero

		if(Maze[hero.line][hero.col-1] == 'D' || Maze[hero.line][hero.col-1] == 'd') //checkar se nao é melhor passar tudo para o KillDrake
			killDrake(hero.line,hero.col - 1);
		if(Maze[hero.line][hero.col+1] == 'D' || Maze[hero.line][hero.col+1] == 'd')
			killDrake(hero.line,hero.col + 1);
		if(Maze[hero.line-1][hero.col] == 'D' || Maze[hero.line-1][hero.col] == 'd')
			killDrake(hero.line - 1,hero.col);
		if(Maze[hero.line+1][hero.col] == 'D' || Maze[hero.line+1][hero.col] == 'd')
			killDrake(hero.line + 1,hero.col);

		// Moves and puts Drakes
		for (int i = 0; i < nDrakes; i++){
			Maze[Drakes.get(i).line][Drakes.get(i).col] = ' ';
			if(!Drakes.get(i).dead) //If the drake is dead, don't bother moving it
			{
				moveDrake(Drakes.get(i));
				if(Drakes.get(i).asleep == false)
					Maze[Drakes.get(i).line][Drakes.get(i).col] = 'D';
				else
					Maze[Drakes.get(i).line][Drakes.get(i).col] = 'd';
			}
		}

		// Puts Swords
		for (int i = 0; i < nSwords; i++) {
			if (Maze[Swords.get(i).line][Swords.get(i).col] == 'D'){ //Checks if there is drake in the tile
				if (Swords.get(i).draw)
					Maze[Swords.get(i).line][Swords.get(i).col] = 'F';
			}
			else
				if (Swords.get(i).draw)
					Maze[Swords.get(i).line][Swords.get(i).col] = 'E';
				else
					Maze[Swords.get(i).line][Swords.get(i).col] = ' ';
		}



		// Colocar Herói
		Maze[hero.line][hero.col] = hero.symbol;

	}

}
