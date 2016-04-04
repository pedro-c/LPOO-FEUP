package maze.logic;

public class Sword {
	
	/**
	 * Sword class to be used in the Maze
	 * @author Tiago & Pedro
	 *
	 */
	
	private int line, col;
	private boolean draw;

	/**
	 * Sword constructor
	 * @param line Line where the Sword will be put
	 * @param col Column where the Sword will be put
	 */
	
	public Sword (int line, int col){
		this.line=line;
		this.col=col;
		this.draw=true;
	}

	/**
	 * 
	 * @return Line where the Sword is located
	 */
	
	public int getLine() {
		return line;
	}
	
	/**
	 * 
	 * @return Column where the Sword is located
	 */
	
	public int getCol() {
		return col;
	}

	/**
	 * 
	 * @return Sword's draw status
	 */
	
	public boolean isDraw() {
		return draw;
	}
	
	/**
	 * Sets Sword's line to specified value
	 * @param line Sword's new line
	 */
	
	public void setLine(int line) {
		this.line = line;
	}
	
	/**
	 * Sets Sword's column to specified value
	 * @param col Sword's new column
	 */

	public void setCol(int col) {
		this.col = col;
	}

	/**
	 * Sets Sword's draw status to specified value
	 * @param draw Sword's new draw status
	 */
	
	public void setDraw(boolean draw) {
		this.draw = draw;
	}
}
