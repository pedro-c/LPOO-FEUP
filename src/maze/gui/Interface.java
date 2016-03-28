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
import net.miginfocom.swing.MigLayout;
import javax.swing.JPanel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.DropMode;
import java.awt.Dialog.ModalExclusionType;

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
		frmMaze.getContentPane().setLayout(new MigLayout("", "[138px][12px][383px][97px][][29px][][][][][][][][][][][][][97px]", "[25px][22px][28px][82px][13px][25px][][12px][211px][16px]"));
		
		fldSize = new JTextField();
		fldSize.setPreferredSize(new Dimension(2147483647, 2147483647));
		frmMaze.getContentPane().add(fldSize, "cell 2 0,alignx left,aligny center");
		fldSize.setColumns(10);
		fldSize.setText("11"); //default value


		
		JLabel mazeSize = new JLabel("Maze Size");
		mazeSize.setPreferredSize(new Dimension(290, 80));
		mazeSize.setMaximumSize(new Dimension(290, 80));
		frmMaze.getContentPane().add(mazeSize, "cell 0 0,growx,aligny center");
		
	
		
		JLabel drakesNumber = new JLabel("Number of Dragons");
		drakesNumber.setPreferredSize(new Dimension(555, 80));
		drakesNumber.setMaximumSize(new Dimension(555, 80));
		frmMaze.getContentPane().add(drakesNumber, "cell 0 1,growx,aligny center");
		
		fldDrakes = new JTextField();
		fldDrakes.setPreferredSize(new Dimension(2147483647, 2147483647));
		frmMaze.getContentPane().add(fldDrakes, "cell 2 1,alignx left,aligny top");
		fldDrakes.setColumns(10);
		fldDrakes.setText("1"); //default value
		
		JLabel dragonMode = new JLabel("Dragon Mode");
		dragonMode.setPreferredSize(new Dimension(380, 80));
		dragonMode.setMaximumSize(new Dimension(380, 80));
		frmMaze.getContentPane().add(dragonMode, "cell 0 2,alignx left,aligny center");
		
		JComboBox<String> gameMode = new JComboBox<String>();
		frmMaze.getContentPane().add(gameMode, "cell 2 2,alignx left,aligny bottom");
		gameMode.addItem("Static Dragon");
		gameMode.addItem("Moving Dragon");
		gameMode.addItem("Moving and Sleeping");
		gameMode.setSelectedIndex(0); //default value
		

		
		JButton btnExit = new JButton("Exit");
		btnExit.setMinimumSize(new Dimension(40, 25));
		btnExit.setPreferredSize(new Dimension(2147483647, 2147483647));
		btnExit.setMaximumSize(new Dimension(455, 125));
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		frmMaze.getContentPane().add(btnExit, "cell 8 2 8 1,alignx center,aligny top");
		
		printMaze = new JTextArea();
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
		
		printMaze.setPreferredSize(new Dimension(2147483647, 2147483647));
		printMaze.setFont(new Font("Courier New", Font.PLAIN, 20));
		frmMaze.getContentPane().add(printMaze, "cell 0 3 4 6,grow");
		
		panel = new JPanel();
		panel.setPreferredSize(new Dimension(0, 100));
		panel.setMinimumSize(new Dimension(0, 100));
		frmMaze.getContentPane().add(panel, "flowx,cell 5 3");
		
		btnUp = new JButton("Up");
		btnUp.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyMovement(e);
			}
		});
		btnUp.setMinimumSize(new Dimension(65, 25));
		btnUp.setPreferredSize(new Dimension(65, 25));
		btnUp.setMaximumSize(new Dimension(225, 125));
		frmMaze.getContentPane().add(btnUp, "cell 12 5,alignx center,aligny bottom");
		
		
		btnUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateGame(0);
			}
		});
		
		btnRight = new JButton("Right");
		btnRight.setPreferredSize(new Dimension(53, 25));
		btnRight.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyMovement(e);
			}
		});
		
		btnLeft = new JButton("Left");
		btnLeft.setMinimumSize(new Dimension(61, 25));
		btnLeft.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyMovement(e);
			}
		});
		btnLeft.setMaximumSize(new Dimension(265, 125));
		frmMaze.getContentPane().add(btnLeft, "cell 8 6,growx,aligny top");
		
		btnLeft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateGame(2);
			}
		});
		btnRight.setMaximumSize(new Dimension(305, 125));
		frmMaze.getContentPane().add(btnRight, "cell 15 6,growx,aligny top");
		
		btnRight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateGame(3);
			}
		});
		
		btnDown = new JButton("Down");
		btnDown.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyMovement(e);
			}
		});
		btnDown.setMaximumSize(new Dimension(325, 125));
		frmMaze.getContentPane().add(btnDown, "cell 12 8,alignx center,aligny top");
		
		btnDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateGame(1);
			}
		});
		
		lblcurretnState = new JLabel("Create new maze!");
		lblcurretnState.setPreferredSize(new Dimension(525, 80));
		lblcurretnState.setMaximumSize(new Dimension(525, 80));
		frmMaze.getContentPane().add(lblcurretnState, "cell 0 9 19 1,growx,aligny top");
		
		newMaze = new JButton("New Maze");
		newMaze.setMinimumSize(new Dimension(40, 25));
		newMaze.setPreferredSize(new Dimension(2147483647, 2147483647));
		newMaze.setMaximumSize(new Dimension(455, 125));
		newMaze.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				
				btnLeft.setEnabled(true);
				btnRight.setEnabled(true);
				btnUp.setEnabled(true);
				btnDown.setEnabled(true);
				
				
				if (!fldSize.getText().matches("[0-9]+"))
				{
					lblcurretnState.setText("Invalid maze size!");	
				}else if(!fldDrakes.getText().matches("[0-9]+"))
				{
					lblcurretnState.setText("Invalid number of dragons!");
					
				}else if(Integer.parseInt(fldSize.getText()) < 5){
					lblcurretnState.setText("Maze is too small!");
				}else if(Integer.parseInt(fldSize.getText()) > 40){
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
					printMaze.setText(g.toString());
					lblcurretnState.setText("Pode jogar!");
				}
				
			}
		});
		frmMaze.getContentPane().add(newMaze, "cell 8 0 8 1,alignx center,aligny top");
	
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

	}
}
