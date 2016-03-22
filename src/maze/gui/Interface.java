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
import java.awt.ComponentOrientation;
import javax.swing.JPanel;
import java.awt.Frame;

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
		frmMaze.getContentPane().setLayout(new MigLayout("", "[138px][12px][383px][97px][][29px][][][97px]", "[25px][22px][28px][82px][13px][25px][][12px][211px][16px]"));
		
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
		frmMaze.getContentPane().add(btnExit, "cell 3 2 6 1,alignx center,aligny top");
		
		printMaze = new JTextArea();
		printMaze.setPreferredSize(new Dimension(2147483647, 2147483647));
		printMaze.setFont(new Font("Courier New", Font.PLAIN, 20));
		printMaze.setEditable(false);
		frmMaze.getContentPane().add(printMaze, "cell 0 3 3 6,grow");
		
		panel = new JPanel();
		panel.setPreferredSize(new Dimension(0, 100));
		panel.setMinimumSize(new Dimension(0, 100));
		frmMaze.getContentPane().add(panel, "flowx,cell 5 3");
		
		btnUp = new JButton("Up");
		btnUp.setMinimumSize(new Dimension(65, 25));
		btnUp.setPreferredSize(new Dimension(65, 25));
		btnUp.setMaximumSize(new Dimension(225, 125));
		frmMaze.getContentPane().add(btnUp, "cell 6 5,alignx center,aligny bottom");
		
		
		btnUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateGame(0);
			}
		});
		
		btnLeft = new JButton("Left");
		btnLeft.setMaximumSize(new Dimension(265, 125));
		frmMaze.getContentPane().add(btnLeft, "cell 4 6 2 1,growx,aligny top");
		
		btnLeft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateGame(2);
			}
		});
		
		btnRight = new JButton("Right");
		btnRight.setMaximumSize(new Dimension(305, 125));
		frmMaze.getContentPane().add(btnRight, "cell 7 6 2 1,growx,aligny top");
		
		btnRight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateGame(3);
			}
		});
		
		btnDown = new JButton("Down");
		btnDown.setMaximumSize(new Dimension(325, 125));
		frmMaze.getContentPane().add(btnDown, "cell 3 8 6 1,alignx center,aligny top");
		
		lblcurretnState = new JLabel("Create new maze!");
		lblcurretnState.setPreferredSize(new Dimension(525, 80));
		lblcurretnState.setMaximumSize(new Dimension(525, 80));
		frmMaze.getContentPane().add(lblcurretnState, "cell 0 9 9 1,growx,aligny top");
		
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
				
				if(Integer.parseInt(fldSize.getText()) < 5){
					lblcurretnState.setText("Maze is too small!");
				}else if(Integer.parseInt(fldSize.getText()) > 39)
				{
					lblcurretnState.setText("Maze is too Big!");	
				}else{
					int x=Integer.parseInt(fldSize.getText());
					if((x % 2) == 0){
						x++;
					}
					MazeBuilder mb = new MazeBuilder(x, Integer.parseInt(fldDrakes.getText()), 1);
					char[][] mt = mb.maze;
					g = new Game(gameMode.getSelectedIndex()+1, Integer.parseInt(fldDrakes.getText()), 1, 1, mt);
					printMaze.setText(g.toString());
				}
				

				
			}
		});
		frmMaze.getContentPane().add(newMaze, "cell 3 0 6 1,alignx center,aligny top");
		
		btnDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateGame(1);
			}
		});
	
	}
	

	private void updateGame(int i){
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
