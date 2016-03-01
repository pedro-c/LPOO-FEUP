package maze.logic;

public class Drake {

	int line, col;
	public boolean dead;
	public boolean asleep;
	
	public Drake (int line, int col, boolean dead ){
		this.line=line;
		this.col=col;
		this.dead=dead;
		this.asleep=false;
	}
	
}
