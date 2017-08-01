package GameLogic;

import GUI.GameFrame;
import GUI.GamePanel;
import Player.Player;
import Points.Point;
import Snake.SnakeComponent;
import Snake.SnakeHead;

public abstract class GameLoopAbstract implements Runnable{
	
	protected static boolean isWin;
	protected GameFrame gf;
	protected GamePanel gp;
//	protected Player serverPlayer;
//	protected Player clientPlayer;
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
}
