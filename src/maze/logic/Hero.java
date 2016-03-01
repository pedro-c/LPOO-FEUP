package maze.logic;

public class Hero {
	
	int line, col;
	char symbol;
	
	public Hero (int line, int col){
		this.line=line;
		this.col=col;
		symbol='H';

	}
	public Hero()
	{
		symbol = 'H';
	}
	
	public int getCol(){
		return col;
	}
	
	public int getLine(){
		return line;
	}
	
}
