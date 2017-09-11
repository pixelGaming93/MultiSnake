package GameLogic;

import java.util.ArrayList;

import GUI.GameFrame;
import GUI.GamePanel;
import GUI.SPanel;
import Player.Player;
import Points.Point;
import Snake.Direction;
import Snake.SnakeComponent;
import Snake.SnakeHead;

public class GameLoopSingle extends GameLoopAbstract{
	
	public GameLoopSingle (SPanel gp, Player player, Point point, GameFrame gf) {
		isWin = false;
		this.gp = (GamePanel)gp;
		this.gf = gf;
		this.point = point;
		gameSpeed = 15.0;
	}
	
	@Override
	public void run() {
		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		final double ns = 1000000000.0 / gameSpeed;
		double delta = 0;
		@SuppressWarnings("unused")
		int frames = 0;
		@SuppressWarnings("unused")
		int updates = 0;
		
		while(!isWin) {
			long now = System.nanoTime();
			delta += (now-lastTime) / ns;
			lastTime = now;
			
			while(delta >= 1) {
				update();
				updates++;
				delta--;
			}
			
			render();
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				updates = 0;
				frames = 0;
			}
		}
		
	}
	
	@Override
	public void move() {
		if(gp.getKey().up && !gf.singlePlayer.getSnakeHead().getDirection().equals(Direction.DOWN)) gf.singlePlayer.getSnakeHead().goUP();
		if(gp.getKey().down && !gf.singlePlayer.getSnakeHead().getDirection().equals(Direction.UP)) gf.singlePlayer.getSnakeHead().goDown();
		if(gp.getKey().right && !gf.singlePlayer.getSnakeHead().getDirection().equals(Direction.LEFT)) gf.singlePlayer.getSnakeHead().goRight();
		if(gp.getKey().left && !gf.singlePlayer.getSnakeHead().getDirection().equals(Direction.RIGHT)) gf.singlePlayer.getSnakeHead().goLeft();
		if(gp.getKey().shoot && gf.singlePlayerS == null) {
			if(gf.singlePlayer.score > 0) {
				gf.singlePlayer.getSnakeHead().shoot();
				gf.singlePlayer.score -= 1;
				ArrayList<SnakeComponent> store = gf.singlePlayer.snake;
				store.remove(gf.singlePlayer.snake.size()-1);
				gf.singlePlayer.snake = store;
			}
		}
	}
	
	@Override
	public void abstractUpdate() {
		gf.singlePlayer.getSnakeHead().move(gp.getWidth(), gp.getHeight());
		String nd = gf.singlePlayer.getSnakeHead().getDirection();
		for(SnakeComponent sc : gf.singlePlayer.getSnake()) {
			sc.setNextDirection(nd);
			nd = sc.getDirection();
			sc.move(gp.getWidth(), gp.getHeight());
			sc.switchDirection();
		}
		if(gf.singlePlayer.getScore()%10 == 0) {
			gameSpeed += 5;
		}
		
		if(gf.getShoot() != null) {
			gf.getShoot().move();
		}
		
		if(gf.ki != null) {
			gf.ki.getSnakeHead().move(gp.getWidth(), gp.getHeight());
			String ndKI = gf.ki.getSnakeHead().getDirection();
			for(SnakeComponent sc : gf.ki.getSnake()) {
				sc.setNextDirection(ndKI);
				ndKI = sc.getDirection();
				sc.move(gp.getWidth(), gp.getHeight());
				sc.switchDirection();
			}
		}
	}

	@Override
	public void collision() {
		SnakeHead sh = gf.singlePlayer.getSnakeHead();
		point = gp.getCurrentPoint();
		if(collisionPoint(sh)) {
			gp.createNewPoint();
			gf.singlePlayer.addPointToSnake();
			gf.singlePlayer.addScore(point.getScore());
		}
		
		if(collisionSelf(gf.singlePlayer)) {
			isWin = true	;
			gp.showLostScreen();
		}
		
		if(GameFrame.singlePlayerS != null) {
			if(collisionOtherSnakeWithShoot(GameFrame.singlePlayerS, GameFrame.ki)) {
				GameFrame.ki.hit();
			}
		}
	}
	
	@Override
	public boolean getIsWin() {
		return isWin;
	}

	@Override
	public void closeStreams() {
		// TODO Auto-generated method stub
		
	}
	
}
