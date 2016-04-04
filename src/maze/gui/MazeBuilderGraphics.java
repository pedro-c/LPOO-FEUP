package maze.gui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class MazeBuilderGraphics extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final int IMAGE_SIZE=35;
	
	private BufferedImage hero;
	private BufferedImage sword;
	private BufferedImage drake;
	private BufferedImage exit;
	private BufferedImage wall;
	private BufferedImage floor;
	private int mazeSize;

	public char[][] maze; 

	public MazeBuilderGraphics(int mSize){
		this.mazeSize=20;
		this.maze = new char[mazeSize][mazeSize];
	
		for(int i=0; i<mazeSize;i++){
			for(int j=1; j<mazeSize;j++){
				maze[i][j]=' ';
			}
			maze[i][0]='X';
			maze[0][i]='X';
			maze[i][mazeSize-1]='X';			
		}	
		for(int i=0;i<mazeSize;i++)
			maze[mazeSize-1][i]='X';
		
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
			drake =  ImageIO.read(new File("res/drakeA.png"));
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

		
		//initializeMaze();
		
	}
	
	protected void paintComponent(Graphics gr) {
		super.paintComponent(gr);
		draw(gr);
	}
	
	public void setMazeSize(int x){
		this.mazeSize=x;
		
		this.maze = new char[mazeSize][mazeSize];
		
		for(int i=0; i<mazeSize;i++){
			for(int j=1; j<mazeSize;j++){
				maze[i][j]=' ';
			}
			maze[i][0]='X';
			maze[0][i]='X';
			maze[i][mazeSize-1]='X';			
		}	
		for(int i=0;i<mazeSize;i++)
			maze[mazeSize-1][i]='X';
		
		
	}
	
	
	public void draw(Graphics gr){
			
		gr.drawImage(hero, 0, 50,null);
		gr.drawImage(sword, 0, 100,null);
		gr.drawImage(drake, 0, 150,null);
		gr.drawImage(exit, 0, 200,null);
		gr.drawImage(wall, 0, 250,null);
		gr.drawImage(floor, 0,300,null);

		for(int i=0;i<this.maze.length;i++)
			for(int j=0; j<this.maze.length;j++){
				if(this.maze[i][j]=='H')
					gr.drawImage(hero, i*IMAGE_SIZE+50, j*35,null);
				else if(this.maze[i][j]=='E')
					gr.drawImage(sword, i*IMAGE_SIZE+50, j*35,null);
				else if(this.maze[i][j]=='D')
					gr.drawImage(drake, i*IMAGE_SIZE+50, j*35,null);
				else if(this.maze[i][j]=='S')
					gr.drawImage(exit, i*IMAGE_SIZE+50, j*35,null);
				else if(this.maze[i][j]=='X')
					gr.drawImage(wall, i*IMAGE_SIZE+50, j*35,null);
				else if(this.maze[i][j]==' ')
					gr.drawImage(floor, i*IMAGE_SIZE+50, j*35,null);
			}
		
	}
	
	public void setMaze(char character, int y, int x){
		int a=y/IMAGE_SIZE;
		int b=x/IMAGE_SIZE;
		this.maze[b][a]=character;
		
		for(int i=0; i<mazeSize;i++){
			if(!(maze[i][0]=='S')){
				maze[i][0]='X';
			}
			if(!(maze[0][i]=='S')){
				maze[0][i]='X';
			}
			if(!(maze[i][mazeSize-1]=='S')){
				maze[i][mazeSize-1]='X';
			}
			
		}	
		for(int i=0;i<mazeSize;i++)
			if(!(maze[i][mazeSize-1]=='S')){
				maze[mazeSize-1][i]='X';
			}
			
		
	}



	

}

