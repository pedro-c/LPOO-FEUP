package maze.logic;

/**
 * Hero class to be used in the Maze
 *
 */

public class Hero extends MazeObject {
	
	private char symbol;
	
	/**
	 * Hero constructor
	 * @param line Line where the hero will be put
	 * @param col Column where the Hero will be put
	 */
	
	public Hero (int line, int col){
		super(line, col);
		symbol='H';

	}

	
	/**
	 * 
	 * @return Hero's symbol
	 */
	
	public char getSymbol(){
		return symbol;
	}
	
	
	/**
	 * Sets Hero's symbol to specified value
	 * @param symbol Hero's new symbol
	 */
	
	public void setSymbol(char symbol) {
		this.symbol = symbol;
	}
	
}
