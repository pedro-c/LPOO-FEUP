
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
	private int x = 0, y = 0, width = 50, height=50;

	// in-memory representation of an example image to be displayed in the screen
	private BufferedImage hero;
	private BufferedImage sword;
	private BufferedImage drake;
	private BufferedImage exit;
	private BufferedImage wall;

	
	// Constructor. Initiates listeners, 
	public MazeGraphics() {
		
		try {
			hero =  ImageIO.read(new File("hero.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			hero =  ImageIO.read(new File("sword.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			hero =  ImageIO.read(new File("drake.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			hero =  ImageIO.read(new File("exit.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			hero =  ImageIO.read(new File("wall.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
			
	public void paintComponent(Graphics g) {
		super.paintComponent(g); // clears the background ...		
		g.drawMaze(g);
	}
	
	

	

}
