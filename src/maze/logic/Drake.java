package maze.logic;

import java.util.Random;

/**
 * Drake class to be used in the Maze
 *
 */

public class Drake extends MazeObject {

	private boolean dead;
	private boolean asleep;
	private int fireCounter;
	
	/**
	 * Drake constructor. Drake starts as alive and awake
	 * @param line Line where the Drake will be put
	 * @param col Column where the Drake will be put
	 */
	
	public Drake (int line, int col){
		super(line, col);
		this.dead=false;
		this.asleep=false;
		Random rand = new Random();
		fireCounter = rand.nextInt(6) + 4; //random cooldown between 4 and 9 for first time the drake spits fire
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
	 * 
	 * @return Number of turns until Fire Spitting ability
	 */
	
	public int getFireCounter(){
		return fireCounter;
	}
	
	/**
	 * Reduces de cooldown of the Fire Spitting ability by 1. Once it goes behind 0, resets it to max
	 */
	
	public void advanceFireCounter(){

		fireCounter--;
		if(fireCounter == - 1)
			fireCounter = 9;
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
