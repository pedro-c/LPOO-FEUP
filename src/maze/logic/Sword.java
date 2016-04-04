package maze.logic;

/**
 * Sword class to be used in the Maze
 *
 */

public class Sword extends MazeObject{

	private boolean draw;

	/**
	 * Sword constructor
	 * @param line Line where the Sword will be put
	 * @param col Column where the Sword will be put
	 */
	
	public Sword (int line, int col){
		super(line, col);

		this.draw=true;
	}

	/**
	 * 
	 * @return Sword's draw status
	 */
	
	public boolean isDraw() {
		return draw;
	}

	/**
	 * Sets Sword's draw status to specified value
	 * @param draw Sword's new draw status
	 */
	
	public void setDraw(boolean draw) {
		this.draw = draw;
	}
}
