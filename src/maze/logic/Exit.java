package maze.logic;

/**
 * Exit class to be used in the Maze
 *
 */

public class Exit extends MazeObject {

	private boolean open;
	
	/**
	 * Exit constructor
	 * @param line Line where the Exit will be put
	 * @param col Column where the Exit will be put
	 */
	
	public Exit (int line, int col){
		super(line, col);
		open=false;
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
