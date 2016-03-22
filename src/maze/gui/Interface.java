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

public class Interface {

	private JFrame frmMaze;
	private JTextField fldSize;
	private JTextField fldDrakes;
	private Game g;

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
		frmMaze.setResizable(false);
		frmMaze.setTitle("Maze");
		frmMaze.setBounds(100, 100, 852, 556);
		frmMaze.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMaze.getContentPane().setLayout(null);
		
		fldSize = new JTextField();
		fldSize.setBounds(162, 26, 52, 22);
		frmMaze.getContentPane().add(fldSize);
		fldSize.setColumns(10);
		fldSize.setText("11"); //default value
		
		JLabel mazeSize = new JLabel("Maze Size");
		mazeSize.setBounds(12, 29, 138, 16);
		frmMaze.getContentPane().add(mazeSize);
	
		
		JLabel drakesNumber = new JLabel("Number of Dragons");
		drakesNumber.setBounds(12, 58, 138, 16);
		frmMaze.getContentPane().add(drakesNumber);
		
		fldDrakes = new JTextField();
		fldDrakes.setBounds(162, 55, 52, 22);
		frmMaze.getContentPane().add(fldDrakes);
		fldDrakes.setColumns(10);
		fldDrakes.setText("1"); //default value
		
		JLabel dragonMode = new JLabel("Dragon Mode");
		dragonMode.setBounds(12, 87, 103, 16);
		frmMaze.getContentPane().add(dragonMode);
		
		JComboBox<String> gameMode = new JComboBox<String>();
		gameMode.setBounds(162, 84, 117, 22);
		frmMaze.getContentPane().add(gameMode);
		gameMode.addItem("Static Dragon");
		gameMode.addItem("Moving Dragon");
		gameMode.addItem("Moving and Sleeping");
		//gameMode.setSelectedIndex(0); //default value
		

		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setBounds(645, 78, 97, 25);
		frmMaze.getContentPane().add(btnExit);
		
		JTextArea printMaze = new JTextArea();
		printMaze.setFont(new Font("Courier New", Font.PLAIN, 20));
		printMaze.setEditable(false);
		printMaze.setBounds(12, 134, 533, 343);
		frmMaze.getContentPane().add(printMaze);
		
		JButton btnUp = new JButton("Up");
		btnUp.setBounds(645, 191, 97, 25);
		frmMaze.getContentPane().add(btnUp);
		
		JButton btnLeft = new JButton("Left");
		btnLeft.setBounds(581, 229, 97, 25);
		frmMaze.getContentPane().add(btnLeft);
		
		JButton btnRight = new JButton("Right");
		btnRight.setBounds(707, 229, 97, 25);
		frmMaze.getContentPane().add(btnRight);
		
		JButton btnDown = new JButton("Down");
		btnDown.setBounds(645, 266, 97, 25);
		frmMaze.getContentPane().add(btnDown);
		
		JLabel lblcurretnState = new JLabel("Create new maze!");
		lblcurretnState.setBounds(12, 492, 533, 16);
		frmMaze.getContentPane().add(lblcurretnState);
		
		JButton newMaze = new JButton("New Maze");
		newMaze.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				btnLeft.setEnabled(true);
				btnRight.setEnabled(true);
				btnUp.setEnabled(true);
				btnDown.setEnabled(true);
				
				MazeBuilder mb = new MazeBuilder(Integer.parseInt(fldSize.getText()), 1, 1);
				char[][] mt = mb.maze;
				g = new Game(gameMode.getSelectedIndex()+1, Integer.parseInt(fldDrakes.getText()), 1, 1, mt);
				printMaze.setText(g.toString());
				
			}
		});
		newMaze.setBounds(645, 25, 97, 25);
		frmMaze.getContentPane().add(newMaze);
		
		
		btnUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				g.moveHeroUp();	
				printMaze.setText(g.toString());
			}
		});
		
		btnDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				g.moveHeroDown();
				printMaze.setText(g.toString());
			}
		});
		
		btnLeft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				g.moveHeroLeft();
				printMaze.setText(g.toString());
			}
		});
		
		btnRight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				g.moveHeroRight();
				printMaze.setText(g.toString());
			}
		});
	
	}
}
