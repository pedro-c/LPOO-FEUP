package maze.gui;

import java.awt.EventQueue;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileWriter;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MazeGenerator implements MouseListener {

	public JFrame frame;

	private MazeBuilderGraphics buildMaze;
	private boolean heroFlag;
	private boolean swordFlag;
	private boolean wallFlag;
	private boolean floorFlag;
	private boolean exitFlag;
	private boolean drakeFlag;
	private Interface mainWindow;
	private boolean heroPlaced;
	private boolean exitPlaced;
	private int mazeSize = 20;
	private int nDrakes;
	private int nSwords;
	private int nExits;
	private int nHeros;
	private JButton btnSave;
	private JButton btnStart;
	private JButton btnMenu;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MazeGenerator window = new MazeGenerator();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MazeGenerator() {
		initialize();
	}

	public MazeGenerator(Interface mainWindow) {
		this();
		this.mainWindow = mainWindow;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 800, 650);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		// mazeSize=mainWindow.getMazeSize();
		buildMaze = new MazeBuilderGraphics(mazeSize);
		buildMaze.setMazeSize(mazeSize);
		buildMaze.setBounds(100, 0, 472, 463);
		frame.getContentPane().add(buildMaze);

		btnSave = new JButton("Save");
		btnSave.setBounds(0, 118, 97, 25);
		frame.getContentPane().add(btnSave);
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser chooser = new JFileChooser();
				// chooser.setCurrentDirectory(new File("/home/me/Documents"));
				int retrival = chooser.showSaveDialog(null);
				if (retrival == JFileChooser.APPROVE_OPTION) {
					try (FileWriter fw = new FileWriter(chooser.getSelectedFile() + ".txt")) {

						String s = "";
						for (int line = 0; line < buildMaze.maze.length; line++) {
							for (int col = 0; col < buildMaze.maze.length; col++) {
								s += buildMaze.maze[col][line];
							}
							s += "\n";
						}

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

		btnStart = new JButton("Start");
		btnStart.setBounds(0, 156, 97, 25);
		frame.getContentPane().add(btnStart);
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(nExits==0 || nDrakes==0 || nSwords==0 || nHeros==0){
					
				}else{
					mainWindow.startGame(buildMaze.maze, mazeSize, nDrakes);
				}
				
			}
		});

		btnMenu = new JButton("Menu");
		btnMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mainWindow.backToMenu();
			}
		});
		btnMenu.setBounds(0, 204, 97, 25);
		frame.getContentPane().add(btnMenu);

		frame.setSize(605, 510);

		frame.addMouseListener(this);
		buildMaze.repaint();

	}

	@Override
	public void mouseClicked(MouseEvent arg0) {

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		buildMaze.repaint();
	}

	@Override
	public void mouseExited(MouseEvent arg0) {

	}

	@Override
	public void mousePressed(MouseEvent arg0) {

		Point p = new Point(arg0.getX(), arg0.getY() - 30);
		mousePress(p);
		buildMaze.repaint();
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		buildMaze.repaint();
	}

	public void setMazeSize(int x) {
		this.mazeSize = x;
		frame.setBounds(100, 100, mazeSize * 35 + 180, mazeSize * 35 + 50);
		buildMaze.setBounds(100, 0, mazeSize * 35 + 50, mazeSize * 35 + 50);
		buildMaze.setMazeSize(x);
		buildMaze.repaint();
	}
	
	public void resetBuild(){
		nDrakes=0;
		nExits=0;
		nHeros=0;
		nSwords=0;
		buildMaze = new MazeBuilderGraphics(mazeSize);
		buildMaze.setMazeSize(mazeSize);
		frame.getContentPane().removeAll();
		frame.getContentPane().add(btnSave);
		frame.getContentPane().add(btnMenu);
		frame.getContentPane().add(btnStart);
		frame.getContentPane().add(buildMaze);
		heroFlag=false;
		swordFlag=false;
		wallFlag=false;
		floorFlag=false;
		exitFlag=false;
		drakeFlag=false;
		heroPlaced=false;
		exitPlaced=false;
		frame.setBounds(100, 100, mazeSize * 35 + 180, mazeSize * 35 + 50);
		buildMaze.setBounds(100, 0, mazeSize * 35 + 50, mazeSize * 35 + 50);
		buildMaze.repaint();
	}

	public void mousePress(Point arg0) {

		System.out.print((int) arg0.getX());
		System.out.print("rato");

		int y = (int) arg0.getY();
		System.out.print(y);
		if (arg0.getX() >= 100 && arg0.getX() <= 135)
			if (y >= 50 && y <= 85) {
				System.out.print("heroi");
				heroFlag = true;
				swordFlag = false;
				wallFlag = false;
				floorFlag = false;
				exitFlag = false;
				drakeFlag = false;
				return;
			}

		if (arg0.getX() >= 100 && arg0.getX() <= 135)
			if (y >= 100 && y <= 135) {
				heroFlag = false;
				swordFlag = true;
				wallFlag = false;
				floorFlag = false;
				exitFlag = false;
				drakeFlag = false;
				return;
			}

		if (arg0.getX() >= 100 && arg0.getX() <= 135)
			if (y >= 150 && y <= 185) {
				heroFlag = false;
				swordFlag = false;
				wallFlag = false;
				floorFlag = false;
				exitFlag = false;
				drakeFlag = true;
				return;
			}

		if (arg0.getX() >= 100 && arg0.getX() <= 135)
			if (y >= 200 && y <= 235) {
				heroFlag = false;
				swordFlag = false;
				wallFlag = false;
				floorFlag = false;
				exitFlag = true;
				drakeFlag = false;
				return;
			}

		if (arg0.getX() >= 100 && arg0.getX() <= 135)
			if (y >= 250 && y <= 285) {
				heroFlag = false;
				swordFlag = false;
				wallFlag = true;
				floorFlag = false;
				exitFlag = false;
				drakeFlag = false;
				return;
			}

		if (arg0.getX() >= 100 && arg0.getX() <= 135)
			if (y >= 300 && y <= 335) {
				heroFlag = false;
				swordFlag = false;
				wallFlag = false;
				floorFlag = true;
				exitFlag = false;
				drakeFlag = false;
				return;
			}

		if (arg0.getX() > 150)
			if (heroFlag && !heroPlaced) {
				if (buildMaze.maze[((int) arg0.getX() - 150) / 35][(y - 8) / 35] == 'S') {
					nExits--;
				}
				if (buildMaze.maze[((int) arg0.getX() - 150) / 35][(y - 8) / 35] == 'E') {
					nSwords--;
				}
				if (buildMaze.maze[((int) arg0.getX() - 150) / 35][(y - 8) / 35] == 'D') {
					nDrakes--;
				}
				if (buildMaze.maze[((int) arg0.getX() - 150) / 35][(y - 8) / 35] == 'S') {
					exitPlaced = false;
				}
				nHeros++;
				heroPlaced = true;
				buildMaze.setMaze('H', (int) y - 8, (int) arg0.getX() - 150);
			} else if (swordFlag) {
				if (buildMaze.maze[((int) arg0.getX() - 150) / 35][(y - 8) / 35] == 'S') {
					nExits--;
				}
				if (buildMaze.maze[((int) arg0.getX() - 150) / 35][(y - 8) / 35] == 'H') {
					nHeros--;
				}
				if (buildMaze.maze[((int) arg0.getX() - 150) / 35][(y - 8) / 35] == 'D') {
					nDrakes--;
				}
				if (buildMaze.maze[((int) arg0.getX() - 150) / 35][(y - 8) / 35] == 'H') {
					heroPlaced = false;
				}
				if (buildMaze.maze[((int) arg0.getX() - 150) / 35][(y - 8) / 35] == 'S') {
					exitPlaced = false;
				}
				nSwords++;
				buildMaze.setMaze('E', (int) y - 8, (int) arg0.getX() - 150);
			} else if (wallFlag) {
				if (buildMaze.maze[((int) arg0.getX() - 150) / 35][(y - 8) / 35] == 'S') {
					nExits--;
				}
				if (buildMaze.maze[((int) arg0.getX() - 150) / 35][(y - 8) / 35] == 'E') {
					nSwords--;
				}
				if (buildMaze.maze[((int) arg0.getX() - 150) / 35][(y - 8) / 35] == 'H') {
					nHeros--;
				}
				if (buildMaze.maze[((int) arg0.getX() - 150) / 35][(y - 8) / 35] == 'D') {
					nDrakes--;
				}
				if (buildMaze.maze[((int) arg0.getX() - 150) / 35][(y - 8) / 35] == 'S') {
					exitPlaced = false;
				}
				if (buildMaze.maze[((int) arg0.getX() - 150) / 35][(y - 8) / 35] == 'H') {
					heroPlaced = false;
				}
				buildMaze.setMaze('X', (int) y - 8, (int) arg0.getX() - 150);
			} else if (floorFlag) {
				if (buildMaze.maze[((int) arg0.getX() - 150) / 35][(y - 8) / 35] == 'S') {
					nExits--;
				}
				if (buildMaze.maze[((int) arg0.getX() - 150) / 35][(y - 8) / 35] == 'E') {
					nSwords--;
				}
				if (buildMaze.maze[((int) arg0.getX() - 150) / 35][(y - 8) / 35] == 'H') {
					nHeros--;
				}
				if (buildMaze.maze[((int) arg0.getX() - 150) / 35][(y - 8) / 35] == 'D') {
					nDrakes--;
				}
				if (buildMaze.maze[((int) arg0.getX() - 150) / 35][(y - 8) / 35] == 'S') {
					exitPlaced = false;
				}
				if (buildMaze.maze[((int) arg0.getX() - 150) / 35][(y - 8) / 35] == 'H') {
					heroPlaced = false;
				}
				buildMaze.setMaze(' ', (int) y, (int) arg0.getX() - 150);
			} else if (exitFlag && !exitPlaced) {
				if (buildMaze.maze[((int) arg0.getX() - 150) / 35][(y - 8) / 35] == 'E') {
					nSwords--;
				}
				if (buildMaze.maze[((int) arg0.getX() - 150) / 35][(y - 8) / 35] == 'H') {
					nHeros--;
				}
				if (buildMaze.maze[((int) arg0.getX() - 150) / 35][(y - 8) / 35] == 'D') {
					nDrakes--;
				}
				nExits++;
				exitPlaced = true;
				if (buildMaze.maze[((int) arg0.getX() - 150) / 35][(y - 8) / 35] == 'H') {
					heroPlaced = false;
				}
				buildMaze.setMaze('S', (int) y, (int) arg0.getX() - 150);
			} else if (drakeFlag) {
				if (buildMaze.maze[((int) arg0.getX() - 150) / 35][(y - 8) / 35] == 'S') {
					nExits--;
				}
				if (buildMaze.maze[((int) arg0.getX() - 150) / 35][(y - 8) / 35] == 'E') {
					nSwords--;
				}
				if (buildMaze.maze[((int) arg0.getX() - 150) / 35][(y - 8) / 35] == 'H') {
					nHeros--;
				}
				if (buildMaze.maze[((int) arg0.getX() - 150) / 35][(y - 8) / 35] == 'S') {
					exitPlaced = false;
				}
				if (buildMaze.maze[((int) arg0.getX() - 150) / 35][(y - 8) / 35] == 'H') {
					heroPlaced = false;
				}
				nDrakes++;
				buildMaze.setMaze('D', (int) y, (int) arg0.getX() - 150);
			}

		return;
	}
}
