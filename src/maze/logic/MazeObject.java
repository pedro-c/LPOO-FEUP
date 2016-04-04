package maze.logic;

/**
 * Basic class which contains a set of coordinates and the appropriatte get's and set's
 */
public class MazeObject {

	protected int line, col;

	MazeObject(int line, int col)
	{
		this.line = line;
		this.col = col;
	}
	
	/**
	 * 
	 * @return Object's line
	 */
	
	public int getLine() {
		return line;
	}

	/**
	 * Sets the object's line to specified value
	 * @param line Object's new line
	 */
	
	public void setLine(int line) {
		this.line = line;
	}

	/**
	 * 
	 * @return Object's column
	 */
	
	public int getCol() {
		return col;
	}
	
	/**
	 * Sets the object's column to specified value
	 * @param col Object's new column
	 */

	public void setCol(int col) {
		this.col = col;
	}
	
}
