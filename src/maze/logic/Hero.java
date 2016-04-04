package maze.logic;

/**
 * Hero class to be used in the Maze
 *
 */

public class Hero {
	
	private int line, col;
	private char symbol;
	
	/**
	 * Hero constructor
	 * @param line Line where the hero will be put
	 * @param col Column where the Hero will be put
	 */
	
	public Hero (int line, int col){
		this.line=line;
		this.col=col;
		symbol='H';

	}
	
	/**
	 * Hero constructor without coordinates indication
	 */
	
	public Hero()
	{
		symbol = 'H';
	}
	
	/**
	 * 
	 * @return Line where the Hero is located
	 */
	
	public int getLine(){
		return line;
	}
	
	/**
	 * 
	 * @return Column where the Hero is located
	 */
	
	public int getCol(){
		return col;
	}
	
	/**
	 * 
	 * @return Hero's symbol
	 */
	
	public char getSymbol(){
		return symbol;
	}
	
	/**
	 * Sets Hero's line to specified value
	 * @param line Hero's new line
	 */
	
	public void setLine(int line) {
		this.line = line;
	}
	
	/**
	 * Sets Hero's column to specified value
	 * @param col Hero's new column
	 */
	
	public void setCol(int col) {
		this.col = col;
	}
	
	/**
	 * Sets Hero's symbol to specified value
	 * @param symbol Hero's new symbol
	 */
	
	public void setSymbol(char symbol) {
		this.symbol = symbol;
	}
	
}
