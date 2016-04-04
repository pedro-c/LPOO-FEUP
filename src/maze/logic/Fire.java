
package maze.logic;

/**
 * Fire class to be used in the Maze
 *
 */

public class Fire {

	private int line, col;
	private int timer;

	/**
	 * Fire Constructor
	 * @param line Line where the fire is located
	 * @param col Column where the fire is located
	 */

	Fire(int line, int col)
	{
		this.line = line;
		this.col = col;
		timer = 4;
	}

	/**
	 * Reduces by 1 the number of turns until fire dies down
	 */

	public void advanceTimer()
	{
		timer--;
	}

	/**
	 * 
	 * @return Line where the fire is located
	 */

	public int getLine() {
		return line;
	}

	/**
	 * 
	 * @return Column where the fire is located
	 */

	public int getCol() {
		return col;
	}

	/**
	 * 
	 * @return Number of turns until the fire dies down
	 */
	
	
	public int getTimer() {
		return timer;
	}



}
