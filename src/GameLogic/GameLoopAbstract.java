package GameLogic;

import GUI.GameFrame;
import GUI.GamePanel;
import GUI.StartPanel;
import Player.Player;
import Points.Point;
import Shoots.Shoot;
import Snake.SnakeComponent;
import Snake.SnakeHead;

public abstract class GameLoopAbstract implements Runnable{
	
	protected static boolean isWin;
	protected GameFrame gf;
	protected GamePanel gp;
	protected Point point;
	protected double gameSpeed;
	
	@Override
	public abstract void run();
	
	public void render(){
		gp.repaint();
		gp.paintScore();
	}
	
	public void update(){
		gp.getKey().update();
		move();
		
		abstractUpdate();
		collision();
	}
	
	public abstract void move();
	
	public abstract void abstractUpdate();
	
	public abstract void collision();
	
	public static  void setIsWin(boolean Win){
		isWin = Win;
	}
	
	public abstract boolean getIsWin();
	
	protected boolean collisionPoint(SnakeHead sh){
		return point.getBounds().intersects(sh.getBounds());
	}

	protected boolean collisionSelf(Player p){
		boolean r;
		for(SnakeComponent sc : p.getSnake()){
			r = p.getSnakeHead().getBounds().intersects(sc.getBounds());
			if(r) return r;
		}
		return false;
	}
	
	protected boolean coolisionOtherSnake(Player p1, Player p2) {
		boolean r;
		for(SnakeComponent sc : p2.getSnake()) {
			r = p1.getSnakeHead().getBounds().intersects(sc.getBounds());
			if(r) return r;
		}
		return false;
	}
	
	protected boolean collisionOtherSnakeWithShoot(Shoot s, Player p) {
		boolean r;
		for(SnakeComponent sc : p.getSnake()) {
			r = s.getBounds().intersects(sc.getBounds());
			if(r) return r;
		}
		return false;
	}
	
	public void backToStartPanel() {
		gf.stopGame();
		gf.getContentPane().removeAll();
		gf.storePanel = new StartPanel(300,100);
		gf.add(gf.storePanel);
		gf.align();
		closeStreams();
	}
	
	public abstract void closeStreams();
}
