
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
	private BufferedImage exit;
	private BufferedImage wall;
	private BufferedImage floor;
	private BufferedImage armed;
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

	}
			
	public void setMaze(Game g){
		this.maze=g.Maze;
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g); // clears the background ...		
		drawMaze(g);
	}

	
	public void drawMaze(Graphics g){

		for (int i = 0; i < maze.length ; i++) {
			for (int j = 0; j < maze.length; j++) {
				int x=i*35;
				int y=j*35;
				switch(maze[j][i]){
				case 'X':
					g.drawImage(wall, x, y, null);
					break;
				case 'H':
					g.drawImage(hero, x, y, null);
					break;
				case 'E':
					g.drawImage(sword, x, y, null);
					break;
				case 'D':
					g.drawImage(drakeA, x, y, null);
					break;
				case 'd':
					g.drawImage(drakeS, x, y, null);
					break;
				case 'S':
					g.drawImage(exit, x, y, null);
					break;
				case 'A':
					g.drawImage(armed, x, y, null);
					break;
				case ' ':
					g.drawImage(floor, x, y, null);
					break;
				default:
					break;
				}
			}
		}
		
	}
	
}
