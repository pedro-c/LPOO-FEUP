package maze.logic;

import java.util.*;

/**
 * MazeBuilder class.
 * Generates a random maze to be used in the Game class
 * The algorithm used generates a square maze with the given size.
 * Size must be an odd integer.
 * A valid maze requires that it exists an unblocked path between the Hero and a Sword
 * 
 * We used an algorithm with is heavely influenced on an algorithm developed by Henrique Ferrolho.
 * @see <a href="http://difusal.blogspot.pt/2014/02/maze-generation-algorithm.html">Henrique Ferrolho's Algorithm</a>
 * 
 *
 *
 */


public class MazeBuilder {

	/**
	 * Enum for all the possible directions the GuideCell can take;
	 *
	 */

	public enum Direction {
		right, left, up, down, NULL;
	}

	/**
	 * To be used to store coordinates
	 * @author Tiago & Pedro
	 *
	 */

	class Cell {
		public int line, col;

		public Cell(int line, int col) {
			this.line = line;
			this.col = col;
		}
	}

	private int size, nDrakes, nSwords, visitedSize;
	private Cell guideCellV;
	private Stack<Cell> pathHistory;
	private boolean[][] visited;
	private boolean[][] visited2;

	/**
	 * The maze to be constructed is stored here.
	 */

	public char[][] maze;
	private int heroX, heroY;


	/**
	 * Maze Builder Constructor.
	 * Initializes attributes and starts the building of the maze.
	 * Only stops when there is a valid, playable maze
	 * @param size Size of the Maze to be built
	 * @param nDrakes Number of Drakes in the Maze
	 * @param nSwords Number of Swords in the Maze
	 */

	public MazeBuilder(int size, int nDrakes, int nSwords) {
		this.size = size;
		this.nDrakes = nDrakes;
		this.nSwords = nSwords;

		//verifies if there is a clean path from the hero to the sword
		do {
			buildMaze();
		} while (!validMaze());
	}

	/**
	 * Initializes an array to be used to check the maze's validity through a Backtracking Algorithm
	 */

	public void initializeVisited2() {
		// initialize visited
		visited2 = new boolean[size][size];

		for (int i = 0; i < size; i++)
			for (int a = 0; a < size; a++)
				visited2[i][a] = false;

		// searches for hero's coordinates
		outerLoop: for (int i = 0; i < maze.length; i++)
			for (int j = 0; j < maze[i].length; j++)
				if (maze[i][j] == 'H') {
					heroX = i;
					heroY = j;
					break outerLoop;
				}	
	}

	/**
	 * Checks the Maze's validity
	 * @return True on a valid Maze, false otherwise
	 * 
	 */

	public boolean validMaze() {
		initializeVisited2();
		return findGoal(heroX, heroY);
	}

	/**
	 * Attempts to find a clean path between the Hero and a Sword
	 * Recursive function: Calls itself many times due to the Backtracking algorithm implemented
	 * @param x Line of the Sword
	 * @param y Column of the Sword
	 * @return True on finding a clear path, false other wise.
	 */

	public boolean findGoal(int x, int y) {

		visited2[x][y] = true; 

		if (maze[x][y] == 'E')
			return true;

		// Goes right
		if (maze[x][y + 1] != 'X' && maze[x][y + 1] != 'S' && maze[x][y + 1] != 'D' && visited2[x][y + 1] == false) {
			visited2[x][y + 1] = true;
			if (findGoal(x, y+1))
				return true;
		}

		// Goes down
		if (maze[x + 1][y] != 'X' && maze[x+1][y] != 'S' && maze[x+1][y] != 'D' && visited2[x + 1][y] == false) {
			visited2[x + 1][y] = true;
			if (findGoal(x+1, y))
				return true;
		}

		// Goes left
		if (maze[x][y - 1] != 'X' && maze[x][y - 1] != 'S' && maze[x][y - 1] != 'D' && visited2[x][y - 1] == false) {
			visited2[x][y - 1] = true;
			if (findGoal(x, y - 1))
				return true;
		}

		// Goes up
		if (maze[x - 1][y] != 'X' && maze[x-1][y] != 'S' && maze[x-1][y] != 'D' && visited2[x - 1][y] == false) {
			visited2[x - 1][y] = true;
			if (findGoal(x - 1, y))
				return true;
		}

		return false;

	}

	/**
	 * Checks if the GuideCell used can move in the specified direction
	 * @param dir Direction to move the GuideCell
	 * @return True when the GuideCell can move, false otherwise
	 */

	public boolean canMove(Direction dir) {
		int nextLine = guideCellV.line, nextCol = guideCellV.col;

		switch (dir) {
		case up:
			nextLine -= 1;
			break;
		case down:
			nextLine += 1;
			break;
		case left:
			nextCol -= 1;
			break;
		case right:
			nextCol += 1;
			break;
		default:
			break;
		}

		if (nextCol >= visitedSize || nextCol < 0 || nextLine >= visitedSize || nextLine < 0)
			return false;

		if (visited[nextLine][nextCol])
			return false;

		return true;
	}

	/**
	 * Attempts to move the GuideCell in a random direction
	 * 
	 * 
	 */

	public void moveGuideCell() {
		Random rand = new Random();
		int direction = rand.nextInt(4);
		Direction dir = Direction.NULL;
		int drawLine = guideCellV.line * 2 + 1;
		int drawCol = guideCellV.col * 2 + 1;
		int nextLine = guideCellV.line, nextCol = guideCellV.col;
		switch (direction) {
		case 0:
			nextLine--;
			dir = Direction.up;
			break;
		case 1:
			nextLine++;
			dir = Direction.down;
			break;
		case 2:
			nextCol--;
			dir = Direction.left;
			break;
		case 3:
			nextCol++;
			dir = Direction.right;
			break;
		}

		if (!canMove(dir))
			return;

		switch (dir) {
		case up:
			maze[drawLine - 1][drawCol] = ' ';
			break;
		case down:
			maze[drawLine + 1][drawCol] = ' ';
			break;
		case left:
			maze[drawLine][drawCol - 1] = ' ';
			break;
		case right:
			maze[drawLine][drawCol + 1] = ' ';
			break;
		default:
			return;
		}

		visited[nextLine][nextCol] = true;
		guideCellV.line = nextLine;
		guideCellV.col = nextCol;

		pathHistory.push(new Cell(guideCellV.line, guideCellV.col));

	}

	/**
	 * Checks if the GuideCell has reached a Dead End
	 * @return True if the GuideCell is at a Dead End, false otherwise
	 */

	public boolean deadEnd() {

		if (canMove(Direction.up) || canMove(Direction.down) || canMove(Direction.left) || canMove(Direction.right))
			return false;

		return true;
	}

	/**
	 * Implementation of the algorithm to generate a random maze
	 */

	public void buildMaze() {
		// Preparing

		Random rand = new Random();

		maze = new char[size][size];
		visitedSize = (size - 1) / 2;

		visited = new boolean[visitedSize][visitedSize];
		for (int i = 0; i < visitedSize; i++)
			for (int j = 0; j < visitedSize; j++)
				visited[i][j] = false;

		// Basic layout
		for (int i = 0; i < size; i++)
			for (int j = 0; j < size; j++)
				if (i % 2 != 0 && j % 2 != 0)
					maze[i][j] = ' ';
				else
					maze[i][j] = 'X';

		// Randomly pick first guide Cell

		int line = 0, col = 0, r;
		r = rand.nextInt(4);
		int temp = 0;
		while (temp % 2 == 0)
			temp = rand.nextInt(size - 2) + 1;

		switch (r) {
		case 0:
			line = 1;
			col = temp;
			break;
		case 1:
			line = size - 2;
			col = temp;
			break;
		case 2:
			col = 1;
			line = temp;
			break;
		case 3:
			col = size - 2;
			line = temp;
			break;
		}

		guideCellV = new Cell((line - 1) / 2, (col - 1) / 2);

		// Places exit

		if (line + 1 == size - 1)
			maze[line + 1][col] = 'S';
		else if (line - 1 == 0)
			maze[line - 1][col] = 'S';
		else if (col + 1 == size - 1)
			maze[line][col + 1] = 'S';
		else if (col - 1 == 0)
			maze[line][col - 1] = 'S';

		pathHistory = new Stack<Cell>();

		pathHistory.push(new Cell(guideCellV.line, guideCellV.col));
		visited[guideCellV.line][guideCellV.col] = true;

		// Carves the maze

		while (!pathHistory.empty()) {
			if (deadEnd())
				guideCellV = pathHistory.pop();
			moveGuideCell();
		}

		// Places Hero

		boolean placed = false;

		while (!placed) {
			int lineH = rand.nextInt(size);
			int colH = rand.nextInt(size);

			if (maze[lineH][colH] == ' ') {
				maze[lineH][colH] = 'H';
				placed = true;
			}
		}

		// Places Drake

		for (int i = 0; i < nDrakes; i++) {
			placed = false;
			while (!placed) {
				int lineD = rand.nextInt(size);
				int colD = rand.nextInt(size);

				if (maze[lineD][colD] == ' ')
					if (maze[lineD - 1][colD] != 'H' && maze[lineD + 1][colD] != 'H' && maze[lineD][colD - 1] != 'H'
					&& maze[lineD][colD + 1] != 'H') {
						maze[lineD][colD] = 'D';
						placed = true;
					}

			}
		}

		// Places Swords

		for (int i = 0; i < nSwords; i++) {
			placed = false;
			while (!placed) {
				int lineE = rand.nextInt(size);
				int colE = rand.nextInt(size);

				if (maze[lineE][colE] == ' ') {
					maze[lineE][colE] = 'E';
					placed = true;
				}
			}
		}

	}
}
