package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;

import Input.Keyboard;
import Points.Point;
import Snake.SnakeComponent;
import Snake.SnakeHead;

public class GamePanel extends SPanel{
	
	private static final long serialVersionUID = -2361258666119049509L;
	protected int gridSize;
	protected int WIDTH;
	protected int HEIGHT;
	protected Color color = Color.WHITE;
	protected Keyboard key;
	protected StartPanel sp;
	protected static String title = "Multiplayersnake";
	
	public GamePanel(int gridSize, StartPanel sp, int componentSize){
		this.gridSize = gridSize;
		this.WIDTH = gridSize*componentSize;
		this.HEIGHT = gridSize*componentSize;
		this.sp = sp;
		key = new Keyboard();
		addKeyListener(key);
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		sp.fix(this);	
		requestFocusInWindow();
	}
	
	public void createNewPoint(){
		sp.gf.initialPoint();
	}
	
	public Point getCurrentPoint(){
		return sp.gf.getPoint();
	}
	
	public void setColor(Color color){
		this.color = color;
	}

	public void setTitle(String title){
		sp.gf.setTitle(sp.gf.title + title);
	}
	
	public void showLostScreen(){
		sp.gf.getContentPane().remove(0);
		sp.gf.getContentPane().remove(0);
		SPanel lp = new LostPanel(WIDTH,HEIGHT,sp);
		sp.gf.add(lp);
		sp.gf.align();
	}
	
	public Keyboard getKey(){
		return key;
	}
	
	public String getTitle(){
		return title;
	}
	
	public int getGridSize(){
		return gridSize;
	}
	
	public void paintScore(){
		sp.gf.paintScore();
	}
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		setBackground(color);
		
		SnakeHead sh = sp.gf.getPlayer().getSnakeHead();
		ArrayList<SnakeComponent> s = sp.gf.getPlayer().getSnake();
		g.fillRect(sh.getX(), sh.getY(), sh.getWidth(), sh.getHeight());
		for(SnakeComponent sc : s){
			g.fillRect(sc.getX(), sc.getY(), sc.getWidth(), sc.getHeight());
		}
		Point point = sp.gf.getPoint();
		g.fillRect(point.getPosX(), point.getPosY(), point.getWidth(), point.getHeight());

	}
}
