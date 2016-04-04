
package maze.logic;

public class Fire {

	private int line, col;
	private int timer;
	
	Fire(int line, int col)
	{
		this.line = line;
		this.col = col;
		timer = 4;
	}
	
	public void advanceTimer()
	{
		timer--;
	}

	public int getLine() {
		return line;
	}

	public void setLine(int line) {
		this.line = line;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}

	public int getTimer() {
		return timer;
	}

	
	
}
