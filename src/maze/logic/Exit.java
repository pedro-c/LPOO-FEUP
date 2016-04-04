package maze.logic;

/**
 * Exit class to be used in the Maze
 *
 */

public class Exit {

	private int line, col;
	private boolean open;
	
	/**
	 * Exit constructor
	 * @param line Line where the Exit will be put
	 * @param col Column where the Exit will be put
	 */
	
	public Exit (int line, int col){
		this.line=line;
		this.col=col;
		open=false;
	}

	/**
	 * 
	 * @return Line where the Exit is located
	 */
	
	public int getLine() {
		return line;
	}
	
	/**
	 * 
	 * @return Column where the Exit is located
	 */

	public int getCol() {
		return col;
	}

	/**
	 * 
	 * @return Exit's open status
	 */
	
	public boolean isOpen() {
		return open;
	}
	
	/**
	 * Sets Exit's open status to specified value
	 * @param open Exit's new open status
	 */
	
	public void setOpen(boolean open){
		this.open = open;
	}
	
}
