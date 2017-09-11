package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;

import Input.Keyboard;
import Points.Point;
import Snake.SnakeComponent;
import Snake.SnakeHead;

/*
 * Das GamePanel ist sozusagen das Spielfeld
 */
public class GamePanel extends SPanel{
	
	private static final long serialVersionUID = -2361258666119049509L;
	
	/// - Variables - /// 
	private int gridSize;
	private int WIDTH;
	private int HEIGHT;
	private Color color = Color.WHITE;
	private StartPanel sp;
	
	// - Input - // 
	private Keyboard key;
	
	/// - Constructor - ///
	public GamePanel(int gridSize, StartPanel sp, int componentSize, GameFrame gf){
		this.gridSize = gridSize;
		this.WIDTH = gridSize*componentSize;
		this.HEIGHT = gridSize*componentSize;
		this.sp = sp;
		
		setPreferredSize(new Dimension(WIDTH, HEIGHT));

		key = new Keyboard();
		addKeyListener(key);
		
		sp.fix(this);	
		requestFocusInWindow();
	}
	
	/// - Methods - ///
	
	public void createNewPoint(){
		/*
		 * Initalisiert ein neuen Punkt, den eine Schlange einsammeln kannn
		 */
		StartPanel.gf.initialPoint();
	}

	public void showLostScreen(){
		/*
		 * Ruft das Panel auf, wenn ein Spieler verloren hat
		 */
		StartPanel.gf.getContentPane().remove(0);
		StartPanel.gf.getContentPane().remove(0);
		SPanel lp = new LostPanel(WIDTH,HEIGHT,sp);
		StartPanel.gf.add(lp);
		StartPanel.gf.align();
	}
	
	public void showWinScreen() {
		StartPanel.gf.getContentPane().remove(0);
		StartPanel.gf.getContentPane().remove(0);
		SPanel wp = new WinPanel(WIDTH,HEIGHT,sp);
		StartPanel.gf.add(wp);
		StartPanel.gf.align();
	}
	
	public void paintScore(){
		/*
		 * Ruft die Paint Methode auf, um die Score-Anzahl anzuzeigen
		 */
		StartPanel.gf.paintSettingPanel();
	}
	
	
	@Override
	public void paintComponent(Graphics g){
		/*
		 * Die Paint-Methode die den Hintergrund einfärbt und die Schlange malt
		 */
		super.paintComponent(g);
		setBackground(color);
		
		
		
		if(StartPanel.isMultiPlayer) {
			// - Player1 - //
			SnakeHead sh1 = StartPanel.gf.getServerPlayer().getSnakeHead();
			ArrayList<SnakeComponent> s1 = StartPanel.gf.getServerPlayer().getSnake();
			g.fillRect(sh1.getX(), sh1.getY(), sh1.getWidth(), sh1.getHeight());
			for(SnakeComponent sc : s1){
				g.fillRect(sc.getX(), sc.getY(), sc.getWidth(), sc.getHeight());
			}

			// - Player2 - //
			SnakeHead sh2 = StartPanel.gf.getClientPlayer().getSnakeHead();
			ArrayList<SnakeComponent> s2 = StartPanel.gf.getClientPlayer().getSnake();
			g.fillRect(sh2.getX(),  sh2.getY(), sh2.getWidth(), sh2.getHeight());
			for(SnakeComponent sc : s2) {
				g.fillRect(sc.getX(), sc.getY(), sc.getWidth(), sc.getHeight());
			}
			
			Point point = StartPanel.gf.getPoint();
			g.fillRect(point.getPosX(), point.getPosY(), point.getWidth(), point.getHeight());
		}else {
			// - SinglePlayer - //
			SnakeHead sh = StartPanel.gf.getSinglePlayer().getSnakeHead();
			ArrayList<SnakeComponent> s = StartPanel.gf.getSinglePlayer().getSnake();
			g.fillRect(sh.getX(), sh.getY(), sh.getWidth(), sh.getHeight());
			for(SnakeComponent sc : s) {
				g.fillRect(sc.getX(), sc.getY(), sc.getWidth(), sc.getHeight());
			}
			
			Point point = StartPanel.gf.getPoint();
			g.fillRect(point.getPosX(), point.getPosY(), point.getWidth(), point.getHeight());
		}
		
		if(GameFrame.singlePlayerS != null) {
			g.fillRect(GameFrame.singlePlayerS.getPosX(), GameFrame.singlePlayerS.getPosY(), GameFrame.singlePlayerS.getWidth(), GameFrame.singlePlayerS.getHeight());
		}
		
		if(GameFrame.ki != null) {
			SnakeHead shKI = GameFrame.ki.getSnakeHead();
			ArrayList<SnakeComponent> sKI = GameFrame.ki.getSnake();
			g.fillRect(shKI.getX(), shKI.getY(), shKI.getWidth(), shKI.getHeight());
			for(SnakeComponent sc : sKI) {
				g.fillRect(sc.getX(), sc.getY(), sc.getWidth(), sc.getHeight());
			}
		}
		
	}
	
	/// - Getter & Setter - ///
	
	public Point getCurrentPoint(){
		return StartPanel.gf.getPoint();
	}
	
	public void setColor(Color color){
		this.color = color;
	}

	public void setTitle(String title){
		StartPanel.gf.setTitle(StartPanel.gf.getTitle() + title);
	}
	
	public Keyboard getKey(){
		return key;
	}
	
	public int getGridSize(){
		return gridSize;
	}
}
