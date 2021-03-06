package maze.gui;

import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import maze.logic.*;



@SuppressWarnings("serial")
public class MazeGraphics extends JPanel  {

		
	// Coordinates of the bounding rectangle for drawing the example image in the screen.
	private int width = 50, height=50;

	// in-memory representation of an example image to be displayed in the screen
	private BufferedImage hero;
	private BufferedImage sword;
	private BufferedImage drakeA;
	private BufferedImage drakeS;
	private BufferedImage drakeOnSword;
	private BufferedImage exit;
	private BufferedImage wall;
	private BufferedImage floor;
	private BufferedImage armed;
	private BufferedImage menuImage;
	private BufferedImage won;
	private BufferedImage lost;
	private BufferedImage fire;
	public boolean gameLost = false;
	public boolean gameWon = false;
	private boolean menuFlag=true;

	public char[][] maze = new char[width][height];
	


	// Constructor. Initiates listeners, 
	public MazeGraphics() {
		
		try {
			hero =  ImageIO.read(new File("res/hero.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			sword =  ImageIO.read(new File("res/sword.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			drakeA =  ImageIO.read(new File("res/drakeA.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			drakeS =  ImageIO.read(new File("res/drakeS.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			exit =  ImageIO.read(new File("res/exit.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			wall =  ImageIO.read(new File("res/wall.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			floor =  ImageIO.read(new File("res/floor.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			armed =  ImageIO.read(new File("res/armed.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			menuImage =  ImageIO.read(new File("res/supermario.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			won =  ImageIO.read(new File("res/won.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			lost =  ImageIO.read(new File("res/lost.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			fire =  ImageIO.read(new File("res/fire.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			drakeOnSword =  ImageIO.read(new File("res/drakeOnSword.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		

	}
			
	public void setMaze(Game g){
		this.maze=g.Maze;
		this.gameLost=g.gameLost;
		this.gameWon=g.gameWon;

	}
	
	public void printMenu(){
		this.menuFlag=true;
	}
	
	public void paintComponent(Graphics gr) {
		super.paintComponent(gr); // clears the background ...		
		drawMaze(gr);
	}

	
	public void drawMaze(Graphics gr){
	
		if(menuFlag){
			gr.drawImage(menuImage, 0, 0, null);
			menuFlag=false;
		}else if(gameWon){
			gr.drawImage(won, 0, 0, null);
		}else if(gameLost){
			gr.drawImage(lost, 0, 0, null);
		}else{
			for (int i = 0; i < maze.length ; i++) {
				for (int j = 0; j < maze.length; j++) {
					int x=i*35;
					int y=j*35;
					switch(maze[j][i]){
					case 'X':
						gr.drawImage(wall, x, y, null);
						break;
					case 'H':
						gr.drawImage(hero, x, y, null);
						break;
					case 'E':
						gr.drawImage(sword, x, y, null);
						break;
					case 'D':
						gr.drawImage(drakeA, x, y, null);
						break;
					case 'd':
						gr.drawImage(drakeS, x, y, null);
						break;
					case 'S':
						gr.drawImage(exit, x, y, null);
						break;
					case 'A':
						gr.drawImage(armed, x, y, null);
						break;
					case 'F':
						gr.drawImage(floor, x, y, null);
						gr.drawImage(drakeOnSword, x, y, null);
						break;
					case ' ':
						gr.drawImage(floor, x, y, null);
						break;
					case 'Y':
						gr.drawImage(floor, x, y, null);
						gr.drawImage(fire, x, y, null);
						break;
					case 'B':
						gr.drawImage(floor, x, y, null);
						gr.drawImage(fire, x, y, null);
						gr.drawImage(hero, x, y, null);
						break;

					default:
						break;
					}
				}
			}
		}
	
		
	}
	
}