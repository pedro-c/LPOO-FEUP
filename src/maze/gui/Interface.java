package maze.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;

import maze.logic.Game;
import maze.logic.MazeBuilder;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.swing.JSlider;
import java.awt.Component;
import java.awt.Rectangle;

public class Interface {

	private JFrame frmMaze;
	private Game g;
	private JTextArea printMaze;
	private JLabel lblcurretnState;
	private JLabel mazeSize;
	private JLabel drakesNumber;
	private JLabel dragonMode;
	private JButton btnUp;
	private JButton btnDown;
	private JButton btnLeft;
	private JButton btnRight;
	private JButton btnExit;
	private JButton newMaze;
	private JButton btnExitToMenu;
	private MazeGraphics graphicsPanel;
	private JSlider mazeDimensions;
	private JSlider drakeNumber;
	private JComboBox<String> gameMode;
	private JButton btnRandomMaze;
	private JButton btnBuildMaze;
	private JButton btnOpenMaze;
	private JButton btnSaveMaze;
	private JButton btnNext;
	private MazeGenerator mazeGenerator;
	private boolean creatingMaze = false;

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
		frmMaze.setMinimumSize(new Dimension(320, 500));
		frmMaze.setTitle("Maze");
		frmMaze.setBounds(500, 200, 701, 483);
		frmMaze.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMaze.getContentPane().setLayout(null);

		mazeGenerator = new MazeGenerator(this);

		btnRandomMaze = new JButton("Random Maze");
		btnRandomMaze.setBounds(446, 215, 147, 57);
		btnRandomMaze.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmMaze.setBounds(500, 200, 322, 500);
				graphicsPanel.setVisible(false);
				mazeSize.setVisible(true);
				drakesNumber.setVisible(true);
				dragonMode.setVisible(true);
				newMaze.setVisible(true);
				newMaze.setEnabled(true);
				mazeDimensions.setVisible(true);
				drakeNumber.setVisible(true);
				btnExit.setVisible(true);
				btnExit.setEnabled(true);
				gameMode.setVisible(true);
				gameMode.setEnabled(true);
				btnExitToMenu.setVisible(true);
				btnRandomMaze.setVisible(false);
				btnRandomMaze.setEnabled(false);
				btnBuildMaze.setVisible(false);
				btnBuildMaze.setVisible(false);
				btnOpenMaze.setEnabled(false);
				btnOpenMaze.setVisible(false);
			}
		});
		frmMaze.getContentPane().add(btnRandomMaze);

		btnBuildMaze = new JButton("Build Maze");
		btnBuildMaze.setBounds(new Rectangle(300, 0, 0, 0));
		btnBuildMaze.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnBuildMaze.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mazeSize.setVisible(true);
				dragonMode.setVisible(true);
				mazeDimensions.setVisible(true);
				drakesNumber.setVisible(false);
				drakeNumber.setVisible(false);
				dragonMode.setVisible(true);
				btnExit.setVisible(true);
				btnExit.setEnabled(true);
				gameMode.setVisible(true);
				gameMode.setEnabled(true);
				graphicsPanel.setVisible(false);
				btnRandomMaze.setVisible(false);
				btnRandomMaze.setEnabled(false);
				btnBuildMaze.setVisible(false);
				btnBuildMaze.setVisible(false);
				btnOpenMaze.setEnabled(false);
				btnOpenMaze.setVisible(false);
				btnNext.setVisible(true);
				btnNext.setEnabled(true);
				btnExitToMenu.setVisible(true);
				btnExitToMenu.setEnabled(true);
				frmMaze.setBounds(500, 200, 340, 483);
				

			}
		});
		btnBuildMaze.setBounds(446, 132, 147, 57);
		frmMaze.getContentPane().add(btnBuildMaze);
		frmMaze.setBounds(500, 200, 315 + 400, 500);

		btnOpenMaze = new JButton("Open Maze");
		btnOpenMaze.setBounds(446, 300, 147, 25);
		btnOpenMaze.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter("Ficheiros de texto", "txt");
				fileChooser.setFileFilter(filter);
				int returnValue = fileChooser.showOpenDialog(null);
				if (returnValue == JFileChooser.APPROVE_OPTION) {
					File selectedFile = fileChooser.getSelectedFile();
					// reading
					try {
						InputStream ips = new FileInputStream(selectedFile);
						InputStreamReader ipsr = new InputStreamReader(ips);
						BufferedReader br = new BufferedReader(ipsr);
						String line;
						line = br.readLine();
						char[][] Maze = new char[line.length()][line.length()];
						int x = 0;
						do {
							for (int i = 0; i < line.length(); i++) {
								Maze[x][i] = line.charAt(i);
							}
							x++;
						} while ((line = br.readLine()) != null);
						br.close();

						// start game
						g = new Game(gameMode.getSelectedIndex() + 1, Maze);
						graphicsPanel.setMaze(g);
						graphicsPanel.setBounds(314, 13, x * 35, x * 35);
						printMaze.setBounds(302, 10, x * 24, x * 24);
						changeFrame(false);

						lblcurretnState.setText("Pode jogar!");

						btnLeft.setEnabled(true);
						btnRight.setEnabled(true);
						btnUp.setEnabled(true);
						btnDown.setEnabled(true);
						btnLeft.setVisible(true);
						btnRight.setVisible(true);
						btnUp.setVisible(true);
						btnDown.setVisible(true);
						btnExitToMenu.setVisible(true);
						graphicsPanel.requestFocus();
						graphicsPanel.repaint();
						btnRandomMaze.setVisible(false);
						btnRandomMaze.setEnabled(false);
						btnBuildMaze.setVisible(false);
						btnBuildMaze.setVisible(false);
						btnOpenMaze.setEnabled(false);
						btnOpenMaze.setVisible(false);
						btnSaveMaze.setEnabled(true);
						btnSaveMaze.setVisible(true);
					} catch (Exception e1) {
						System.out.println(e1.toString());
					}
				}
			}
		});
		frmMaze.getContentPane().add(btnOpenMaze);

		mazeSize = new JLabel("Maze Size");
		mazeSize.setBounds(7, 13, 111, 22);
		mazeSize.setPreferredSize(new Dimension(290, 80));
		mazeSize.setMaximumSize(new Dimension(290, 80));
		frmMaze.getContentPane().add(mazeSize);

		drakesNumber = new JLabel("Number of Dragons");
		drakesNumber.setBounds(7, 54, 111, 22);
		drakesNumber.setPreferredSize(new Dimension(555, 80));
		drakesNumber.setMaximumSize(new Dimension(555, 80));
		frmMaze.getContentPane().add(drakesNumber);

		printMaze = new JTextArea();
		printMaze.setMinimumSize(new Dimension(0, 0));
		printMaze.setVisible(false);
		printMaze.setBounds(316, 10, 438, 437);
		printMaze.setEditable(false);

		dragonMode = new JLabel("Dragon Mode");
		dragonMode.setBounds(7, 109, 111, 22);
		dragonMode.setPreferredSize(new Dimension(380, 80));
		dragonMode.setMaximumSize(new Dimension(380, 80));
		frmMaze.getContentPane().add(dragonMode);

		printMaze.setPreferredSize(new Dimension(2147483647, 2147483647));
		printMaze.setFont(new Font("Courier New", Font.PLAIN, 20));
		frmMaze.getContentPane().add(printMaze);

		gameMode = new JComboBox<String>();
		gameMode.setMinimumSize(new Dimension(100, 22));
		gameMode.setPreferredSize(new Dimension(200, 22));
		gameMode.setBounds(156, 109, 137, 22);
		frmMaze.getContentPane().add(gameMode);
		gameMode.addItem("Static Dragon");
		gameMode.addItem("Moving Dragon");
		gameMode.addItem("Moving and Sleeping");
		gameMode.setSelectedIndex(0);

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

		mazeDimensions = new JSlider();
		mazeDimensions.setMajorTickSpacing(5);
		mazeDimensions.setPaintTicks(true);
		mazeDimensions.setValue(20);
		mazeDimensions.setMaximum(27);
		mazeDimensions.setMinimum(5);
		mazeDimensions.setBounds(156, 13, 137, 29);
		frmMaze.getContentPane().add(mazeDimensions);

		drakeNumber = new JSlider();
		drakeNumber.setPaintTicks(true);
		drakeNumber.setSnapToTicks(true);
		drakeNumber.setMajorTickSpacing(2);
		drakeNumber.setMinorTickSpacing(1);
		drakeNumber.setValue(1);
		drakeNumber.setMinimum(1);
		drakeNumber.setMaximum(20);
		drakeNumber.setBounds(156, 54, 137, 42);
		frmMaze.getContentPane().add(drakeNumber);

		newMaze = new JButton("Create Maze");
		newMaze.setBounds(46, 300, 183, 25);
		newMaze.setMinimumSize(new Dimension(40, 25));
		newMaze.setPreferredSize(new Dimension(2147483647, 2147483647));
		newMaze.setMaximumSize(new Dimension(455, 125));
		newMaze.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int x = mazeDimensions.getValue();
				if ((x % 2) == 0) {
					x++;
				}
				MazeBuilder mb = new MazeBuilder(x, drakeNumber.getValue(), 1);
				char[][] mt = mb.maze;
				g = new Game(gameMode.getSelectedIndex() + 1, mt);
				graphicsPanel.setMaze(g);
				graphicsPanel.setBounds(314, 13, x * 35, x * 35);
				printMaze.setBounds(302, 10, x * 24, x * 24);
				changeFrame(false);

				lblcurretnState.setText("Pode jogar!");

				btnLeft.setEnabled(true);
				btnRight.setEnabled(true);
				btnUp.setEnabled(true);
				btnDown.setEnabled(true);
				btnLeft.setVisible(true);
				btnRight.setVisible(true);
				btnUp.setVisible(true);
				btnDown.setVisible(true);
				btnExitToMenu.setVisible(true);
				graphicsPanel.requestFocus();
				graphicsPanel.repaint();
				btnSaveMaze.setEnabled(true);
				btnSaveMaze.setVisible(true);

			}
		});
		newMaze.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyMovement(e);
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

		btnSaveMaze = new JButton("Save Maze");
		btnSaveMaze.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser chooser = new JFileChooser();
				// chooser.setCurrentDirectory(new File("/home/me/Documents"));
				int retrival = chooser.showSaveDialog(null);
				if (retrival == JFileChooser.APPROVE_OPTION) {
					try (FileWriter fw = new FileWriter(chooser.getSelectedFile() + ".txt")) {
						String s = g.toString();

						for (int i = 0; i < s.length(); i++) {
							if (s.charAt(i) == '\n') {
								fw.write(System.getProperty("line.separator"));
							} else {
								fw.write(s.charAt(i));
							}
						}
						fw.close();
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
			}
		});
		btnSaveMaze.setBounds(46, 415, 183, 24);
		btnSaveMaze.setVisible(false);
		frmMaze.getContentPane().add(btnSaveMaze);
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

		btnExit = new JButton("Exit");
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
		lblcurretnState.setVisible(false);
		lblcurretnState.setBounds(19, 415, 245, 16);
		lblcurretnState.setPreferredSize(new Dimension(525, 80));
		lblcurretnState.setMaximumSize(new Dimension(525, 80));
		frmMaze.getContentPane().add(lblcurretnState);

		btnExitToMenu = new JButton("Exit to Menu");
		btnExitToMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				graphicsPanel.printMenu();
				frmMaze.setBounds(500, 200, 701, 483);
				graphicsPanel.setBounds(0, 0, 697, 453);
				graphicsPanel.setVisible(true);
				mazeSize.setVisible(false);
				drakesNumber.setVisible(false);
				dragonMode.setVisible(false);
				newMaze.setVisible(false);
				newMaze.setEnabled(false);
				mazeDimensions.setVisible(false);
				drakeNumber.setVisible(false);
				btnExit.setVisible(false);
				btnExit.setEnabled(false);
				gameMode.setVisible(false);
				gameMode.setEnabled(false);
				btnExitToMenu.setVisible(false);
				btnLeft.setEnabled(false);
				btnRight.setEnabled(false);
				btnUp.setEnabled(false);
				btnDown.setEnabled(false);
				btnLeft.setVisible(false);
				btnRight.setVisible(false);
				btnUp.setVisible(false);
				btnDown.setVisible(false);
				btnRandomMaze.setVisible(true);
				btnRandomMaze.setEnabled(true);
				btnBuildMaze.setVisible(true);
				btnBuildMaze.setVisible(true);
				btnOpenMaze.setEnabled(true);
				btnOpenMaze.setVisible(true);
				btnSaveMaze.setEnabled(false);
				btnSaveMaze.setVisible(false);
				btnNext.setEnabled(false);
				btnNext.setVisible(false);
			}
		});
		btnExitToMenu.setVisible(false);
		btnExitToMenu.setBounds(46, 376, 183, 25);
		frmMaze.getContentPane().add(btnExitToMenu);

		printMaze.setVisible(false);
		btnUp.setVisible(false);
		btnUp.setEnabled(false);
		btnDown.setVisible(false);
		btnDown.setEnabled(false);
		btnLeft.setVisible(false);
		btnLeft.setEnabled(false);
		btnRight.setVisible(false);
		btnRight.setEnabled(false);
		mazeSize.setVisible(false);
		drakesNumber.setVisible(false);
		dragonMode.setVisible(false);
		newMaze.setVisible(false);
		newMaze.setEnabled(false);
		mazeDimensions.setVisible(false);
		drakeNumber.setVisible(false);
		btnExit.setVisible(false);
		btnExit.setEnabled(false);
		gameMode.setVisible(false);
		gameMode.setEnabled(false);

		graphicsPanel = new MazeGraphics();
		graphicsPanel.setVisible(true);
		graphicsPanel.setBounds(0, 0, 697, 453);
		frmMaze.getContentPane().add(graphicsPanel);

		btnNext = new JButton("Next");
		btnNext.setEnabled(false);
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*
				 * int x = mazeDimensions.getValue(); if ((x % 2) == 0) { x++; }
				 * char[][] maze = new char[x][x]; for(int i = 0; i < x; ++i)
				 * for (int j = 0; j < x; ++j) maze[i][j] ='X';
				 * 
				 * g = new Game(gameMode.getSelectedIndex() + 1,
				 * drakeNumber.getValue(), 1, 1, maze);
				 * graphicsPanel.setMaze(g); graphicsPanel.setBounds(314, 13, x
				 * * 35, x * 35); printMaze.setBounds(302, 10, x * 24, x * 24);
				 * changeFrame(false);
				 * 
				 * btnNext.setEnabled(false); btnNext.setVisible(false);
				 * btnExitToMenu.setVisible(true);
				 * btnExitToMenu.setEnabled(true); creatingMaze = true;
				 */
				
				mazeGenerator.setMazeSize(mazeDimensions.getValue());
				mazeGenerator.resetBuild();
				mazeGenerator.frame.setVisible(true);
				mazeGenerator.frame.repaint();

			}
		});
		btnNext.setBounds(46, 300, 183, 25);
		frmMaze.getContentPane().add(btnNext);

		graphicsPanel.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyMovement(e);
			}
		});
		graphicsPanel.setVisible(true);

	}
	
	public int getMazeSize(){
		int x = mazeDimensions.getValue();
		if ((x % 2) == 0) {
			x++;
		}
		return x;
	}

	public void startGame(char[][] m, int size, int drakes) {
		int x = size;
		if ((x % 2) == 0) {
			x++;
		}
		char[][] mt = m;
		g = new Game(gameMode.getSelectedIndex() + 1, mt);
		graphicsPanel.setMaze(g);
		graphicsPanel.setBounds(314, 13, x * 35, x * 35);
		printMaze.setBounds(302, 10, x * 24, x * 24);
		changeFrame(false);

		lblcurretnState.setText("Pode jogar!");

		btnLeft.setEnabled(true);
		btnRight.setEnabled(true);
		btnUp.setEnabled(true);
		btnDown.setEnabled(true);
		btnLeft.setVisible(true);
		btnRight.setVisible(true);
		btnUp.setVisible(true);
		btnDown.setVisible(true);
		btnExitToMenu.setVisible(true);
		graphicsPanel.requestFocus();
		graphicsPanel.repaint();
		btnSaveMaze.setEnabled(true);
		btnSaveMaze.setVisible(true);
		btnNext.setEnabled(false);
		btnNext.setVisible(false);
		mazeGenerator.frame.setVisible(false);
	}
	
	public void backToMenu(){
		
		graphicsPanel.printMenu();
		frmMaze.setBounds(500, 200, 701, 483);
		graphicsPanel.setBounds(0, 0, 697, 453);
		graphicsPanel.setVisible(true);
		mazeSize.setVisible(false);
		drakesNumber.setVisible(false);
		dragonMode.setVisible(false);
		newMaze.setVisible(false);
		newMaze.setEnabled(false);
		mazeDimensions.setVisible(false);
		drakeNumber.setVisible(false);
		btnExit.setVisible(false);
		btnExit.setEnabled(false);
		gameMode.setVisible(false);
		gameMode.setEnabled(false);
		btnExitToMenu.setVisible(false);
		btnLeft.setEnabled(false);
		btnRight.setEnabled(false);
		btnUp.setEnabled(false);
		btnDown.setEnabled(false);
		btnLeft.setVisible(false);
		btnRight.setVisible(false);
		btnUp.setVisible(false);
		btnDown.setVisible(false);
		btnRandomMaze.setVisible(true);
		btnRandomMaze.setEnabled(true);
		btnBuildMaze.setVisible(true);
		btnBuildMaze.setVisible(true);
		btnOpenMaze.setEnabled(true);
		btnOpenMaze.setVisible(true);
		btnSaveMaze.setEnabled(false);
		btnSaveMaze.setVisible(false);
		btnNext.setEnabled(false);
		btnNext.setVisible(false);
		mazeGenerator.frame.setVisible(false);
	}

	private void keyMovement(KeyEvent e) {
		char key;
		key = e.getKeyChar();

		if (key == 'w') {
			updateGame(0);
		} else if (key == 's') {
			updateGame(1);
		} else if (key == 'a') {
			updateGame(2);
		} else if (key == 'd') {
			updateGame(3);
		} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			updateGame(3);
		} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			updateGame(2);
		} else if (e.getKeyCode() == KeyEvent.VK_UP) {
			updateGame(0);
		} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			updateGame(1);
		}

	}

	private void updateGame(int i) {
		if (!g.gameLost && !g.gameWon) {
			switch (i) {
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
		if (g.gameLost) {
			graphicsPanel.setMaze(g);
			changeFrame(true);
			lblcurretnState.setText("You Lost! Try again.");
			btnLeft.setEnabled(false);
			btnRight.setEnabled(false);
			btnUp.setEnabled(false);
			btnDown.setEnabled(false);
			frmMaze.setBounds(500, 200, 315 + 400, 500);

		} else if (g.gameWon) {
			graphicsPanel.setMaze(g);
			changeFrame(true);
			lblcurretnState.setText("You Won, Congrats!");
			btnLeft.setEnabled(false);
			btnRight.setEnabled(false);
			btnUp.setEnabled(false);
			btnDown.setEnabled(false);
			frmMaze.setBounds(500, 200, 315 + 400, 500);

		} else {
			lblcurretnState.setText("Pode Jogar!");
		}
		graphicsPanel.setMaze(g);
		graphicsPanel.repaint();
	}

	public void changeFrame(boolean flag) {

		printMaze.setVisible(false);
		lblcurretnState.setVisible(flag);
		mazeSize.setVisible(flag);
		drakesNumber.setVisible(flag);
		dragonMode.setVisible(flag);
		mazeDimensions.setVisible(flag);
		drakeNumber.setVisible(flag);
		graphicsPanel.setVisible(true);
		gameMode.setVisible(flag);
		gameMode.setEnabled(flag);
		btnExitToMenu.setVisible(flag);

		if (flag) {
			frmMaze.setBounds(500, 200, 500, 322);
			graphicsPanel.setBounds(305, 0, 430, 380);

		} else {
			frmMaze.setBounds(100, 100, graphicsPanel.getWidth() + 350, graphicsPanel.getHeight() + 80);

		}

	}
}
