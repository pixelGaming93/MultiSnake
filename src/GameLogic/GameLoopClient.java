package GameLogic;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import GUI.GameFrame;
import GUI.GamePanel;
import GUI.SPanel;
import Player.Player;
import Player.ServerPlayer;
import Points.Point;
import Snake.Direction;
import Snake.SnakeComponent;
import Snake.SnakeHead;

public class GameLoopClient extends GameLoopAbstract{
	
	/// - Variables - /// 
	public static Socket client;
	
	public GameLoopClient(SPanel gp, Player clientPlayer, Point point, GameFrame gf){
		isWin = false;
		this.gp = (GamePanel)gp;
		this.gf = gf;
		this.clientPlayer = clientPlayer;
		this.serverPlayer = null;
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
		
		try{
			client = new Socket(gf.getIpAdd(), gf.getPortAdd());
		
		if(client.isConnected()){
			while(true){
				long now = System.nanoTime();
				delta += (now-lastTime) / ns;
				lastTime = now;
				
				while(delta>= 1){
					update();
					updates++;
					delta--;
				}
				
				ObjectOutputStream sendClientPlayer = new ObjectOutputStream(client.getOutputStream());
				sendClientPlayer.writeObject(clientPlayer);
				sendClientPlayer.flush();
				
				ObjectInputStream getServerPlayer = new ObjectInputStream(client.getInputStream());
				serverPlayer = (ServerPlayer) getServerPlayer.readObject();
				gf.serverPlayer = (ServerPlayer) serverPlayer;
				
				ObjectInputStream getPoint = new ObjectInputStream(client.getInputStream());
				point = (Point) getPoint.readObject();
				gf.point = point;
				
				render();
				frames++;
				
				if(System.currentTimeMillis() - timer > 1000){
					timer += 1000;
					gp.setTitle(gp.getTitle() + " | " + updates + " ups, " + frames + " fps");
					updates = 0;
					frames = 0;
				}
			}
		}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void move() {
		if(gp.getKey().up && !clientPlayer.getSnakeHead().getDirection().equals(Direction.DOWN)) clientPlayer.getSnakeHead().goUP();
		if(gp.getKey().down && !clientPlayer.getSnakeHead().getDirection().equals(Direction.UP)) clientPlayer.getSnakeHead().goDown();
		if(gp.getKey().right && !clientPlayer.getSnakeHead().getDirection().equals(Direction.LEFT)) clientPlayer.getSnakeHead().goRight();
		if(gp.getKey().left && !clientPlayer.getSnakeHead().getDirection().equals(Direction.RIGHT)) clientPlayer.getSnakeHead().goLeft();		
	}

	@Override
	public void abstractUpdate() {
		System.out.println(clientPlayer.getSnakeHead().toString());
		clientPlayer.getSnakeHead().move(gp.getWidth(), gp.getHeight());
		String nd = clientPlayer.getSnakeHead().getDirection();
		for(SnakeComponent sc : clientPlayer.getSnake()) {
			sc.setNextDirection(nd);
			nd = sc.getDirection();
			sc.move(gp.getWidth(), gp.getHeight());
			sc.switchDirection();
		}
		
if(clientPlayer.getScore()%10 == 0){
	gameSpeed += 5;
}
	}

	@Override
	public void collision() {
	}

	@Override
	public boolean getIsWin() {
		return isWin;
	}

}
