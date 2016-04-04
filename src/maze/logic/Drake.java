package maze.logic;

/**
 * Drake class to be used in the Maze
 * @author Tiago & Pedro
 *
 */

public class Drake {

	private int line, col;
	private boolean dead;
	private boolean asleep;
	
	/**
	 * Drake constructor. Drake starts as alive and awake
	 * @param line Line where the Drake will be put
	 * @param col Column where the Drake will be put
	 */
	
	public Drake (int line, int col){
		this.line=line;
		this.col=col;
		this.dead=false;
		this.asleep=false;
	}

	/**
	 * 
	 * @return Line where the Drake is located
	 */
	
	public int getLine() {
		return line;
	}
	
	/**
	 * 
	 * @return Column where the Drake is located
	 */

	public int getCol() {
		return col;
	}

	/**
	 * 
	 * @return Drake's dead status
	 */
	
	public boolean isDead() {
		return dead;
	}

	/**
	 * 
	 * @return Drake's asleep status
	 */
	
	public boolean isAsleep() {
		return asleep;
	}

	/**
	 * Sets Drake's line to specified value
	 * @param line Drake's new line
	 */
	
	
	public void setLine(int line) {
		this.line = line;
	}

	/**
	 * Sets Drake's column to specified value
	 * @param col Drake's new column
	 */
	
	public void setCol(int col) {
		this.col = col;
	}

	/**
	 * Sets Drake's dead status to specified status
	 * @param dead Drake's new dead status
	 */
	
	public void setDead(boolean dead) {
		this.dead = dead;
	}

	/**
	 * Sets Drake's asleep status to specified status
	 * @param asleep Drake's asleep status
	 */
	
	public void setAsleep(boolean asleep) {
		this.asleep = asleep;
	}
	
}
