package maze.logic;

public class Hero {
	
	int line, col;
	char symbol;
	
	public Hero (int line, int col, char symbol){
		this.line=line;
		this.col=col;
		this.symbol='H';

	}
	
	public int getCol(){
		return col;
	}
	
	public int getLine(){
		return line;
	}
	
}
