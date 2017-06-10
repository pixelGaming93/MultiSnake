package GameLogic;

import java.awt.Rectangle;

import GUI.GamePanel;
import GUI.SPanel;
import Player.Player;
import Points.Point;
import Snake.SnakeComponent;
import Snake.SnakeHead;

public class GameLoop implements Runnable{
	
	protected boolean isWin;
	protected GamePanel gp;
	protected Player player;
	protected Point point;
	protected double gameSpeed;
	
	public GameLoop(SPanel gp, Player player, Point point){
		isWin = false;
		this.gp = (GamePanel)gp;
		this.player = player;
		this.point = point;
		gameSpeed = 15.0;
	}
	
	@Override
	public void run() {
		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		final double ns = 1000000000.0 / gameSpeed;
		double delta = 0;
		int frames = 0;
		int updates = 0;
		
		while(!isWin){
			long now = System.nanoTime();
			delta += (now-lastTime) / ns;
			lastTime = now;
			while(delta >= 1){
				update();
				updates++;
				delta--;
			}
			render();
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000){
				timer += 1000;
//				System.out.println(updates + " ups, " + frames + " fps");
				gp.setTitle(gp.getTitle() + " | " + updates + " ups, " + frames + " fps");
				updates = 0;
				frames = 0;
			}
		}
	}

	public void render(){
		gp.repaint();
		gp.paintScore();
	}
	
	public void update(){
		gp.getKey().update();
		if(gp.getKey().up) player.getSnakeHead().goUP();
		if(gp.getKey().down) player.getSnakeHead().goDown();
		if(gp.getKey().right) player.getSnakeHead().goRight();
		if(gp.getKey().left) player.getSnakeHead().goLeft();

		player.getSnakeHead().move();
		String nd = player.getSnakeHead().getDirection();
		for(SnakeComponent sc : player.getSnake()){
			sc.setNextDirection(nd);
			nd = sc.getDirection();
			sc.move();
			sc.switchDirection();
		}
		
		
		
//		Collison
		SnakeHead sh = player.getSnakeHead();
		point = gp.getCurrentPoint();
		if(collisionPoint(player.getSnakeHead())){
			gp.createNewPoint();
			player.addPointToSnake();
			player.addScore(point.getScore());
		}
		if(collisionSelf(player)){
			isWin = true;
		}
	}
	
	public void setIsWin(boolean isWin){
		this.isWin = isWin;
	}

	private boolean collisionPoint(SnakeHead sh){
		return point.getBounds().intersects(sh.getBounds());
	}
	
	private boolean collisionSelf(Player p){
		boolean r;
		for(SnakeComponent sc : p.getSnake()){
			r = p.getSnakeHead().getBounds().intersects(sc.getBounds());
			if(r) return r;
		}
		return false;
	}
	


}
