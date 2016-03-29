package maze.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;

import maze.logic.Game;
import maze.logic.MazeBuilder;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Dimension;
import javax.swing.JPanel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Interface {

	private JFrame frmMaze;
	private JTextField fldSize;
	private JTextField fldDrakes;
	private Game g;
	private JTextArea printMaze;
	private JLabel lblcurretnState;
	private JButton btnUp;
	private JButton btnDown;
	private JButton btnLeft;
	private JButton btnRight;
	private JPanel panel;
	private JButton newMaze;
	private MazeGraphics graphicsPanel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Interface window = new Interface();
					window.frmMaze.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Interface() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmMaze = new JFrame();

		frmMaze.getContentPane().setPreferredSize(new Dimension(2147483647, 2147483647));
		frmMaze.getContentPane().setMinimumSize(new Dimension(50, 50));
		frmMaze.setMinimumSize(new Dimension(500, 400));
		frmMaze.setTitle("Maze");
		frmMaze.setBounds(100, 100, 801, 522);
		frmMaze.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMaze.getContentPane().setLayout(null);
		
		
				
		JLabel mazeSize = new JLabel("Maze Size");
		mazeSize.setBounds(7, 13, 111, 22);
		mazeSize.setPreferredSize(new Dimension(290, 80));
		mazeSize.setMaximumSize(new Dimension(290, 80));
		frmMaze.getContentPane().add(mazeSize);
		
		fldSize = new JTextField();
		fldSize.setBounds(153, 13, 137, 22);
		fldSize.setPreferredSize(new Dimension(2147483647, 2147483647));
		frmMaze.getContentPane().add(fldSize);
		fldSize.setColumns(10);
		fldSize.setText("11"); //default value
		
		graphicsPanel = new MazeGraphics();
		graphicsPanel.setBounds(313, 13, 441, 434);
		frmMaze.getContentPane().add(graphicsPanel);
		
		
		
		JLabel drakesNumber = new JLabel("Number of Dragons");
		drakesNumber.setBounds(7, 39, 111, 22);
		drakesNumber.setPreferredSize(new Dimension(555, 80));
		drakesNumber.setMaximumSize(new Dimension(555, 80));
		frmMaze.getContentPane().add(drakesNumber);
		
		printMaze = new JTextArea();
		printMaze.setVisible(false);
		printMaze.setBounds(316, 10, 438, 437);
		printMaze.setEditable(false);
		printMaze.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				char key;
				key=e.getKeyChar();
				if(key=='w'){
					updateGame(0);
				}else if(key=='s'){
					updateGame(1);
				}else if(key=='a'){
					updateGame(2);
				}else if(key=='d'){
					updateGame(3);
				}
			}
		});
		
		fldDrakes = new JTextField();
		fldDrakes.setBounds(153, 39, 137, 22);
		fldDrakes.setMinimumSize(new Dimension(30, 22));
		fldDrakes.setPreferredSize(new Dimension(2147483647, 2147483647));
		frmMaze.getContentPane().add(fldDrakes);
		fldDrakes.setColumns(10);
		fldDrakes.setText("1"); //default value
		
		JLabel dragonMode = new JLabel("Dragon Mode");
		dragonMode.setBounds(7, 65, 111, 22);
		dragonMode.setPreferredSize(new Dimension(380, 80));
		dragonMode.setMaximumSize(new Dimension(380, 80));
		frmMaze.getContentPane().add(dragonMode);
		
		printMaze.setPreferredSize(new Dimension(2147483647, 2147483647));
		printMaze.setFont(new Font("Courier New", Font.PLAIN, 20));
		frmMaze.getContentPane().add(printMaze);
		
		JComboBox<String> gameMode = new JComboBox<String>();
		gameMode.setMinimumSize(new Dimension(100, 22));
		gameMode.setPreferredSize(new Dimension(200, 22));
		gameMode.setBounds(153, 65, 137, 22);
		frmMaze.getContentPane().add(gameMode);
		gameMode.addItem("Static Dragon");
		gameMode.addItem("Moving Dragon");
		gameMode.addItem("Moving and Sleeping");
		gameMode.setSelectedIndex(0); //default value
		
		panel = new JPanel();
		panel.setBounds(456, 85, 0, 100);
		panel.setPreferredSize(new Dimension(0, 100));
		panel.setMinimumSize(new Dimension(0, 100));
		frmMaze.getContentPane().add(panel);
		
		btnLeft = new JButton("Left");	
		btnLeft.setEnabled(false);
		btnLeft.setBounds(12, 195, 80, 25);
		btnLeft.setMinimumSize(new Dimension(80, 25));
		btnLeft.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyMovement(e);
			}
		});
		
		btnUp = new JButton("Up");
		btnUp.setEnabled(false);
		btnUp.setBounds(100, 166, 80, 25);
		btnUp.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyMovement(e);
			}
		});
		btnUp.setMinimumSize(new Dimension(65, 25));
		btnUp.setPreferredSize(new Dimension(65, 25));
		btnUp.setMaximumSize(new Dimension(225, 125));
		frmMaze.getContentPane().add(btnUp);
		
		
		btnUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateGame(0);
			}
		});
		btnLeft.setMaximumSize(new Dimension(80, 25));
		frmMaze.getContentPane().add(btnLeft);
		
		btnLeft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateGame(2);
			}
		});
		
		
	
		
		newMaze = new JButton("New Maze");
		newMaze.setBounds(46, 300, 183, 25);
		newMaze.setMinimumSize(new Dimension(40, 25));
		newMaze.setPreferredSize(new Dimension(2147483647, 2147483647));
		newMaze.setMaximumSize(new Dimension(455, 125));
		newMaze.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
	
				
				
				if (!fldSize.getText().matches("[0-9]+"))
				{
					lblcurretnState.setText("Invalid maze size!");	
				}else if(!fldDrakes.getText().matches("[0-9]+"))
				{
					lblcurretnState.setText("Invalid number of dragons!");
					
				}else if(Integer.parseInt(fldSize.getText()) < 5){
					lblcurretnState.setText("Maze is too small!");
				}else if(Integer.parseInt(fldSize.getText()) > 37){
					lblcurretnState.setText("Maze is too big!");
				}
				else{
					int x=Integer.parseInt(fldSize.getText());
					if((x % 2) == 0){
						x++;
					}
					MazeBuilder mb = new MazeBuilder(x, Integer.parseInt(fldDrakes.getText()), 1);
					char[][] mt = mb.maze;
					g = new Game(gameMode.getSelectedIndex()+1, Integer.parseInt(fldDrakes.getText()), 1, 1, mt);
					graphicsPanel.setMaze(g);
					printMaze.setText(g.toString());
					
					printMaze.setBounds(302, 10, g.Maze.length*24, g.Maze.length*24);
					if(printMaze.getHeight()<=500){
						frmMaze.setBounds(100, 100, 350+printMaze.getWidth(), 500);
					}else{
						frmMaze.setBounds(100, 100, 350+printMaze.getWidth(), printMaze.getHeight()+80);
					}
					
					lblcurretnState.setText("Pode jogar!");
					
					btnLeft.setEnabled(true);
					btnRight.setEnabled(true);
					btnUp.setEnabled(true);
					btnDown.setEnabled(true);
					
					
					graphicsPanel.repaint();
					
				}
				
			}
		});
		
		btnDown = new JButton("Down");
		btnDown.setEnabled(false);
		btnDown.setBounds(100, 231, 80, 25);
		btnDown.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyMovement(e);
			}
		});
		
		btnRight = new JButton("Right");
		btnRight.setEnabled(false);
		btnRight.setBounds(184, 195, 80, 25);
		btnRight.setMinimumSize(new Dimension(80, 25));
		btnRight.setPreferredSize(new Dimension(53, 25));
		btnRight.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyMovement(e);
			}
		});
		btnRight.setMaximumSize(new Dimension(80, 125));
		frmMaze.getContentPane().add(btnRight);
		
		btnRight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateGame(3);
			}
		});
		btnDown.setMaximumSize(new Dimension(325, 125));
		frmMaze.getContentPane().add(btnDown);
		
		btnDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateGame(1);
			}
		});
		frmMaze.getContentPane().add(newMaze);
		

		
		JButton btnExit = new JButton("Exit");
		btnExit.setBounds(46, 338, 183, 25);
		btnExit.setMinimumSize(new Dimension(40, 25));
		btnExit.setPreferredSize(new Dimension(2147483647, 2147483647));
		btnExit.setMaximumSize(new Dimension(455, 125));
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		frmMaze.getContentPane().add(btnExit);
		
		lblcurretnState = new JLabel("Create new maze!");
		lblcurretnState.setBounds(19, 415, 245, 16);
		lblcurretnState.setPreferredSize(new Dimension(525, 80));
		lblcurretnState.setMaximumSize(new Dimension(525, 80));
		frmMaze.getContentPane().add(lblcurretnState);

	
	}
	
	private void keyMovement(KeyEvent e){
			char key;
			key=e.getKeyChar();
			if(key=='w'){
				updateGame(0);
			}else if(key=='s'){
				updateGame(1);
			}else if(key=='a'){
				updateGame(2);
			}else if(key=='d'){
				updateGame(3);
			}
		
	}

	private void updateGame(int i){
		if(!g.gameLost && !g.gameWon){
			switch(i){
			case 0:
				g.moveHeroUp();	
				break;
			case 1:
				g.moveHeroDown();
				break;
			case 2:
				g.moveHeroLeft();
				break;
			case 3:
				g.moveHeroRight();
				break;
			default:
				break;
			}
		}
		
		printMaze.setText(g.toString());
		if(g.gameLost){
			lblcurretnState.setText("You Lost! Try again.");
			btnLeft.setEnabled(false);
			btnRight.setEnabled(false);
			btnUp.setEnabled(false);
			btnDown.setEnabled(false);
		}
		else if(g.gameWon){
			lblcurretnState.setText("You Won, Congrats!");
			btnLeft.setEnabled(false);
			btnRight.setEnabled(false);
			btnUp.setEnabled(false);
			btnDown.setEnabled(false);
		}
		else{
			lblcurretnState.setText("Pode Jogar!");
		}
		graphicsPanel.setMaze(g);
		graphicsPanel.repaint();
	}
}
