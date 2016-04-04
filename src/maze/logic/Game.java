package maze.logic;
import java.awt.Point;
import java.util.*;

/**
 * Game class.
 * Controls all the logic components of the game.
 * The game functions as follows:
 * There is a hero and there are drakes, swords and exits in a maze.
 * The player controls the movement of the hero through the "w", "a", "s", "d" keys or through the arrow keys.
 * The game is lost if:
 * 	1. The hero ends his turn next to a drake.
 * 	2. The hero ends his turn in a fire.
 * The hero can arm himself by picking up a sword.
 * Once armed, the hero will kill any adjacent drake.
 * Once all drakes are dead, the exits are opened and the hero is free to go.
 *
 */

public class Game {

	/**
	 * GameStatus Enum to store all possible game statuses.
	 *
	 */

	public enum GameStatus {
		HeroUnarmed, HeroArmed, HeroDied;
	}

	/**
	 * Tracks if the game is lost or not
	 */

	public boolean gameLost = false;

	/**
	 * Tracs if the game is won or not
	 */
	public boolean gameWon = false;

	/**
	 * Tracks the game status, i.e. the condition of the Hero
	 */

	public GameStatus status;

	/**
	 * Stores game mode.
	 * 1 means Static Drake
	 * 2 means Moving Drake
	 * 3 means Moving and Sleeping Drake
	 */

	public int mode;

	/**
	 * Number of drakes in the maze
	 */

	public int nDrakes = 0;

	/**
	 * Number of swords in the maze
	 */

	public int nSwords = 0;

	/**
	 * Number of exits in the maze
	 */

	public int nExits = 0;

	/**
	 * Char matrix to store the maze. Each object is stored as a differente char
	 */

	public char[][] Maze;

	/**
	 * The Hero in the maze
	 */

	Hero hero = new Hero(-1, -1);

	/**
	 * ArrayList to store all drakes in the maze
	 */

	public ArrayList<Drake> Drakes = new ArrayList<Drake>(nDrakes);

	/**
	 * ArrayList to store all swords in the maze
	 */

	public ArrayList<Sword> Swords = new ArrayList<Sword>(nSwords);

	/**
	 * ArrayList to store all exits in the maze
	 */

	public ArrayList<Exit> Exits = new ArrayList<Exit>(nExits);

	/**
	 * ArrayList to store all fires in the maze
	 */

	public ArrayList<Fire> Fires = new ArrayList<Fire>();

	/**
	 * Game Constructor.
	 * @param mode Game Mode
	 * @param maze The Maze itself
	 */

	public Game(int mode, char[][] maze)
	{
		this.mode = mode;
		status = GameStatus.HeroUnarmed;

		Maze = maze;		

		//searches and updates hero's coordinates according to the given maze
		outerLoop:
			for(int i = 0; i < Maze.length; i++)
				for(int j = 0; j < Maze[i].length; j++)
					if(Maze[i][j] == 'H')	{
						hero.setLine(i);
						hero.setCol(j);
						break outerLoop;
					}

		//searches all swords and adds them to the array
		for(int i = 0; i < Maze.length; i++)
			for(int j = 0; j < Maze[i].length; j++)
				if(Maze[i][j] == 'E')	{
					Swords.add(new Sword(i,j));
					nSwords++;
				}

		//searches all dragons and adds them to the array
		for(int i = 0; i < Maze.length; i++)
			for(int j = 0; j < Maze[i].length; j++)
				if(Maze[i][j] == 'D')	{
					Drakes.add(new Drake(i,j));
					nDrakes++;
				}

		// searches all Exits and adds them to the array
		for (int i = 0; i < Maze.length; i++)
			for (int j = 0; j < Maze[i].length; j++)
				if (Maze[i][j] == 'S') {
					Exits.add(new Exit(i, j));
					nExits++;
				}

	}

	/**
	 * Puts the maze matrix into a string to be printed
	 */

	@Override
	public String toString()
	{
		String s = "";
		for (int line = 0; line < Maze.length; line++) {
			for (int col = 0; col < Maze.length; col++) {
				if(Maze[line][col] != 'Y')
					s+=Maze[line][col];
				else
					s+=' ';
				//s+=" ";
			}
			s+="\n";
		}
		return s;
	}

	/**
	 * 
	 * @return A point containing the Hero's coordinates
	 */

	public Point getHeroPosition()
	{
		Point p = new Point(hero.getLine(), hero.getCol());
		return p;
	}

	/**
	 * 
	 * @return Game's Status (i.e. if the hero is armed, unarmed or dead)
	 */

	public GameStatus getStatus() {
		return status;
	}

	/**
	 * Gets the status of a certain Drake on the specified coordinates
	 * @param line Line where the Drake is
	 * @param col Column where the Drake is
	 * @return Status of the drake (true for alive, false otherwise)
	 */

	public boolean getDrakeStatus(int line, int col){

		boolean ret = false;
		for(int i = 0; i < Drakes.size(); i++){
			if(Drakes.get(i).getLine() == line && Drakes.get(i).getCol() == col)
				ret = Drakes.get(i).isDead();
		}
		return ret;	
	}

	/**
	 * Checks if the status of an Exit on the specified coordinates
	 * @param line Line where the exit is
	 * @param col Column where the exit is
	 * @return The status of the Exit (true for open, false otherwise)
	 */

	public boolean checkExit(int line, int col) {
		boolean ret = false;

		for(int i = 0; i < nExits; i++)
			if(Exits.get(i).getCol() == col && Exits.get(i).getLine() == line)
				ret= Exits.get(i).isOpen();

		return ret;

	}

	/**
	 * 
	 * @param col Column where the sword is
	 * @param line Line where the sword is
	 * @param draw True if the sword is to be drawn on the map, false if it already held by the hero
	 */

	public void updateSword(int col, int line, boolean draw) {
		for (int i = 0; i < nSwords; i++) {
			if (Swords.get(i).getCol() == col && Swords.get(i).getLine() == line) {
				Swords.get(i).setDraw(draw);
			}

		}
	}

	/**
	 * Attempts to places the Hero on the specified coordinates
	 * @param newLine Line where the Hero will be placed
	 * @param newCol Column where the Hero will be placed
	 * @return Successfulness of the placement
	 */

	public boolean moveHero(int newLine, int newCol)
	{		
		int curLine = hero.getLine(), curCol = hero.getCol();



		if (Maze[newLine][newCol] == 'S' && checkExit(newLine, newCol)) {
			gameWon = true;
		} 			
		else if (Maze[newLine][newCol] == ' ' || Maze[newLine][newCol] == 'Y') {
			hero.setLine(newLine);
			hero.setCol(newCol);
		} 
		else if (Maze[newLine][newCol] == 'E') {
			hero.setSymbol('A');
			hero.setLine(newLine);
			hero.setCol(newCol);
			status = GameStatus.HeroArmed;
			updateSword(newCol, newLine, false);
		} 
		else{
			return false; //Invalid Movement
		}

		//clears the hero previous position
		Maze[curLine][curCol] = ' ';

		updateMaze();
		return true; //Hero moved successfully

	}

	/**
	 * Attempts to move the Hero in the upwards direction
	 * @return Successfulness of the placement
	 */

	public boolean moveHeroUp() {

		return moveHero(hero.getLine() - 1, hero.getCol());
	}

	/**
	 * Attempts to move the Hero in the downwards direction
	 * @return Successfulness of the placement
	 */

	public boolean moveHeroDown() {

		return moveHero(hero.getLine() + 1, hero.getCol());
	}

	/**
	 * Attempts to move the Hero in the leftwards direction
	 * @return Successfulness of the placement
	 */

	public boolean moveHeroRight() {

		return moveHero(hero.getLine(), hero.getCol() + 1);
	}

	/**
	 * Attempts to move the Hero in the rightwards direction
	 * @return Successfulness of the placement
	 */

	public boolean moveHeroLeft() {

		return moveHero(hero.getLine(), hero.getCol() - 1);
	}

	/**
	 * Searches for a Drake in the Drakes ArrayList by giving its coordinates
	 * @param line Line where the Drake is
	 * @param col Column where the Drake is
	 * @return Index of the Drake in the Drakes ArrayList
	 */

	public int getDrakePos(int line, int col)
	{		
		for(int i = 0; i < Drakes.size(); ++i)
			if(line == Drakes.get(i).getLine() && col == Drakes.get(i).getCol())
				return i;
		return -1; //Not found
	}

	/**
	 * Attempts to kill the drake at the specified coordinates. Hero dies if it is not armed and the drake is awake.
	 * @param line Line where the Drake is
	 * @param col Column where the Drake is
	 */


	public void killDrake(int line, int col) {

		if(hero.getSymbol() != 'A' && Maze[line][col] == 'D')
		{
			gameLost = true;
			status = GameStatus.HeroDied;
			return; //No need to do anything else, the game is lost
		}

		if(hero.getSymbol() != 'A' && Maze[line][col] == 'd')
		{
			return; //Does nothing
		}

		Drakes.get(getDrakePos(line, col)).setDead(true);
		Maze[line][col] = ' ';


		if(checksAllDrakes())//Checks if all drakes are dead
			for (int i = 0; i < nExits; i++){
				Exits.get(i).setOpen(true);
			}

	}

	/**
	 * Returns an ArrayList of all Drakes' coordinates
	 * @return ArrayList of coordinates
	 */

	public ArrayList<Point> getDrakesPositions(){

		ArrayList<Point> Positions = new ArrayList<Point>(nDrakes);

		for(int i=0; i<nDrakes; i++){
			Point p = new Point(Drakes.get(i).getLine(),Drakes.get(i).getCol());
			Positions.add(p);
		}

		return Positions;
	}

	/**
	 * Checks if all Drakes are dead
	 * @return True when all Drakes are dead, false otherwise
	 */

	public boolean checksAllDrakes(){

		for (int i = 0; i < nDrakes; i++) {
			if(Drakes.get(i).isDead()==false){
				return false;
			}	
		}
		return true;		
	}

	/**
	 * Checks if a Drake can me moved to the coordinates specified
	 * @param line Line where the Drake is to be moved
	 * @param col Column where the is to be moved
	 * @return True if the Drake can be moved, false otherwise
	 */

	public boolean canMoveDrake(int line, int col)
	{
		if(Maze[line][col] == ' ' || Maze[line][col] == 'E')
			return true;
		return false;
	}

	/**
	 * Creates a column of fire in the upwards direction of the drake
	 * @param line Line where the fire begins
	 * @param col Column where the fire begins
	 */

	public void createFireUp(int line, int col)
	{
		int nextLine = line - 1, nextCol = col;
		while(Maze[nextLine][nextCol] == ' ' || Maze[nextLine][nextCol] == 'H' || Maze[nextLine][nextCol] == 'A')
		{
			Fires.add(new Fire(nextLine, nextCol));
			nextLine--;
		}	
	}

	/**
	 * Creates a column of fire in the downwards direction of the drake
	 * @param line Line where the fire begins
	 * @param col Column where the fire begins
	 */

	public void createFireDown(int line, int col)
	{
		int nextLine = line + 1, nextCol = col;
		while(Maze[nextLine][nextCol] == ' ' || Maze[nextLine][nextCol] == 'H' || Maze[nextLine][nextCol] == 'A')
		{
			Fires.add(new Fire(nextLine, nextCol));
			nextLine++;
		}	
	}

	/**
	 * Creates a line of fire in the leftwards direction of the drake
	 * @param line Line where the fire begins
	 * @param col Column where the fire begins
	 */

	public void createFireLeft(int line, int col)
	{
		int nextLine = line , nextCol = col - 1;
		while(Maze[nextLine][nextCol] == ' ' || Maze[nextLine][nextCol] == 'H' || Maze[nextLine][nextCol] == 'A')
		{
			Fires.add(new Fire(nextLine, nextCol));
			nextCol--;
		}	
	}

	/**
	 * Creates a line of fire in the rightwards direction of the drake
	 * @param line Line where the fire begins
	 * @param col Column where the fire begins
	 */

	public void createFireRight(int line, int col)
	{
		int nextLine = line, nextCol = col + 1;
		while(Maze[nextLine][nextCol] == ' ' || Maze[nextLine][nextCol] == 'H' || Maze[nextLine][nextCol] == 'A')
		{
			Fires.add(new Fire(nextLine, nextCol));
			nextCol++;
		}	
	}

	/**
	 * Checks if the Drake's Fire Ability Cooldown is over. 
	 * If so, creates fire in a straight line in one of the directions.
	 * Drake can fire directly into a wall, thus not creating any flames on the Maze.
	 * @param d Drake to be checked
	 */

	public void spitFire(Drake d)
	{
		Random rand = new Random();

		if(d.getFireCounter() == 0)
		{
			int direction = rand.nextInt(4);
			switch(direction)
			{
			case 0: //Up
				createFireUp(d.getLine(), d.getCol());
				break;
			case 1: //Down
				createFireDown(d.getLine(), d.getCol());
				break;
			case 2: //Left
				createFireLeft(d.getLine(), d.getCol());
				break;
			case 3: //Right
				createFireRight(d.getLine(), d.getCol());
				break;
			}
		}
		d.advanceFireCounter();
	}

	/**
	 * Attempts to move the Drake based on the game mode and on probabilities:
	 * On "Static Mode", the Drake will not move. On other modes, the Drake will have a 50% likelihood of moving.
	 * On "Moving and Sleeping Dragon", the Drake will also have a 25% chance to fall asleep.
	 * While sleeping, the Drake has 50% chance to wake up.
	 * When the Drake wakes up, it has a 50% chance to move before the turn ends.
	 * @param d The Drake to be moved
	 */

	public void moveDrake(Drake d)
	{

		if(mode == 1) //On Static Drake Mode, do nothing
			return;

		//Used to check and update next position
		int nextLine = d.getLine();
		int nextCol = d.getCol();

		int maxRandInt = 0;
		Random rand = new Random();

		if(mode == 2) //On Moving Drake Mode, 50% chance that the Drake moves
			maxRandInt = 2;

		if(mode == 3) //On Moving/Sleeping Drake Mode, 50% chance to move and 25% chance to fall asleep. If asleep, 50% chance to awake up
			maxRandInt = 4;


		if(d.isAsleep())
		{
			int awakesUp = rand.nextInt(2);
			if(awakesUp == 0) //Stays asleep
				return;

			d.setAsleep(false); //Awakes up
			int moves = rand.nextInt(2); // 50% chance to move if it wakes up this turn
			if(moves == 0) //Does not move
				return;
		}
		int move = rand.nextInt(maxRandInt);

		if(move == 0) //Does not move
			return; 
		if(move == 1 || move == 2) //Moves
		{
			if(canMoveDrake(nextLine + 1,nextCol) || canMoveDrake(nextLine - 1,nextCol) ||  //checks that it can move in, at least, one direction
					canMoveDrake(nextLine,nextCol + 1) || canMoveDrake(nextLine,nextCol - 1))
			{
				while(!canMoveDrake(nextLine, nextCol)) //Checks if it can move to the coordinates
				{
					//Resets coordinates
					nextLine = d.getLine();
					nextCol = d.getCol();

					//Picks a random direction
					int moveDirection = rand.nextInt(4);
					switch(moveDirection)
					{
					case 0: //up
						nextLine--;
						break;
					case 1: //down
						nextLine++;
						break;
					case 2: //left
						nextCol--;
						break;
					case 3:
						nextCol++;
						break;
					default:
						break;
					}
				}
				//Updates drake's coordinates

				d.setLine(nextLine);
				d.setCol(nextCol);

			}
		}
		if(move == 3) //Sleeps 
			d.setAsleep(true);

	}

	/**
	 * Updates the Maze.
	 * Calls functions to move Hero and Drakes.
	 * Handles the placement of the swords and fires.
	 * If the Hero ends his turn on a flame, the game is lost.
	 * If the Hero is unarmed and ends his turn next to a Drake, the game is lost.
	 * If the Hero is armed and ends his turn next to a Drake, he kills it.
	 * When all Drakes are dead, the exits open
	 */

	public void updateMaze() {


		// Puts Fires
		for(int i = 0; i < Fires.size(); i++)
		{

			Maze[Fires.get(i).getLine()][Fires.get(i).getCol()] = 'Y';
			Fires.get(i).advanceTimer();
			if(Fires.get(i).getTimer() == 0) //if the fire is not active anymore
			{
				Maze[Fires.get(i).getLine()][Fires.get(i).getCol()] = ' ';
				Fires.remove(i);
				i--;
			}
		}

		// Puts Hero
		if(Maze[hero.getLine()][hero.getCol()] == 'Y')
		{
			Maze[hero.getLine()][hero.getCol()] = 'B';
			gameLost = true;
			return;
		}
		else
			Maze[hero.getLine()][hero.getCol()] = hero.getSymbol();



		// Moves and puts Drakes. Also checks if they are gonna spit fire
		for (int i = 0; i < nDrakes; i++){
			if(!Drakes.get(i).isDead()) //If the drake is dead, don't bother doint anything
			{
				int curLine = Drakes.get(i).getLine(), curCol = Drakes.get(i).getCol();
				moveDrake(Drakes.get(i));
				Maze[curLine][curCol] = ' '; 
				if(Drakes.get(i).isAsleep() == false)
				{
					Maze[Drakes.get(i).getLine()][Drakes.get(i).getCol()] = 'D';
					spitFire(Drakes.get(i));
				}
				else
					Maze[Drakes.get(i).getLine()][Drakes.get(i).getCol()] = 'd';

			}
		}

		// Puts Swords
		for (int i = 0; i < nSwords; i++) {
			if (Maze[Swords.get(i).getLine()][Swords.get(i).getCol()] == 'D'){ //Checks if there is drake in the tile
				if (Swords.get(i).isDraw())
					Maze[Swords.get(i).getLine()][Swords.get(i).getCol()] = 'F';
			}
			else
				if (Swords.get(i).isDraw())
					Maze[Swords.get(i).getLine()][Swords.get(i).getCol()] = 'E';
		}

		//checks for drakes adjacent to the hero

		if(Maze[hero.getLine()][hero.getCol()-1] == 'D' || Maze[hero.getLine()][hero.getCol()-1] == 'd') 
			killDrake(hero.getLine(),hero.getCol() - 1);
		else if(Maze[hero.getLine()][hero.getCol()+1] == 'D' || Maze[hero.getLine()][hero.getCol()+1] == 'd')
			killDrake(hero.getLine(),hero.getCol() + 1);
		else if(Maze[hero.getLine()-1][hero.getCol()] == 'D' || Maze[hero.getLine()-1][hero.getCol()] == 'd')
			killDrake(hero.getLine() - 1,hero.getCol());
		else if(Maze[hero.getLine()+1][hero.getCol()] == 'D' || Maze[hero.getLine()+1][hero.getCol()] == 'd')
			killDrake(hero.getLine() + 1,hero.getCol());


	}

}
