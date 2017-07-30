package GameLogic;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import GUI.GameFrame;
import GUI.GamePanel;
import GUI.SPanel;
import Player.ClientPlayer;
import Player.Player;
import Points.Point;
import Snake.Direction;
import Snake.SnakeComponent;
import Snake.SnakeHead;

public class GameLoopServer extends GameLoopAbstract{
	
	/// - Variables - ///
	public static ServerSocket server;
	public static Socket client;
	
	public GameLoopServer(SPanel gp,  Player serverPlayer, Point point, GameFrame gf){
		isWin = false;
		this.gp = (GamePanel)gp;
		this.gf = gf;
		this.serverPlayer = serverPlayer;
		this.point = point;
		gameSpeed = 15.0;
	}

//	@SuppressWarnings("deprecation")
	@Override
	public void run() {
		final double ns = 1000000000.0 / gameSpeed;
		double delta = 0;
		int frames = 0;
		int updates = 0;
		
		try{
			server = new ServerSocket(gf.getPortAdd());
			client = server.accept();
			
			
			if(client.isConnected()){
				long lastTime = System.nanoTime();
				long timer = System.currentTimeMillis();
				while(!isWin){
					long now = System.nanoTime();
					delta += (now-lastTime) / ns;
					lastTime = now;
					
					while(delta>= 1){
						update();
						updates++;
						delta--;
					}
					// Streams
					ObjectInputStream getObj = new ObjectInputStream(client.getInputStream());
					clientPlayer = (ClientPlayer) getObj.readObject();
					gf.clientPlayer = (ClientPlayer) clientPlayer;

					ObjectOutputStream sendSPlayer = new ObjectOutputStream(client.getOutputStream());
					sendSPlayer.writeObject(serverPlayer);
					sendSPlayer.flush();
					
					ObjectOutputStream sendPoint = new ObjectOutputStream(client.getOutputStream());
					sendPoint.writeObject(point);
					sendPoint.flush();
					
					render();
					frames++;
					
					if(System.currentTimeMillis() - timer > 1000){
						timer += 1000;
						gp.setTitle(gp.getTitle() + " | " + updates + " ups, " + frames + " fps");
						updates = 0;
						frames = 0;
					}
				}
				gp.showLostScreen();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	
	@Override
	public void move() {
		if(gp.getKey().up && !serverPlayer.getSnakeHead().getDirection().equals(Direction.DOWN)) serverPlayer.getSnakeHead().goUP();
		if(gp.getKey().down && !serverPlayer.getSnakeHead().getDirection().equals(Direction.UP)) serverPlayer.getSnakeHead().goDown();
		if(gp.getKey().right && !serverPlayer.getSnakeHead().getDirection().equals(Direction.LEFT)) serverPlayer.getSnakeHead().goRight();
		if(gp.getKey().left && !serverPlayer.getSnakeHead().getDirection().equals(Direction.RIGHT)) serverPlayer.getSnakeHead().goLeft();
	}
	
	@Override
	public void abstractUpdate() {
		serverPlayer.getSnakeHead().move(gp.getWidth(), gp.getHeight());
		String nd = serverPlayer.getSnakeHead().getDirection();
		for(SnakeComponent sc : serverPlayer.getSnake()){
			sc.setNextDirection(nd);
			nd = sc.getDirection();
			sc.move(gp.getWidth(), gp.getHeight());
			sc.switchDirection();
		}
		
		if(serverPlayer.getScore()%10 == 0){
			gameSpeed += 5;
		}
		
		if(clientPlayer.getSnakeHead().getIsWin() == true) {
			isWin = true;
		}
	}
	
	@Override
	public void collision() {
		SnakeHead sh1 = serverPlayer.getSnakeHead();
		point = gp.getCurrentPoint();
		if(collisionPoint(sh1)){
			gp.createNewPoint();
			serverPlayer.addPointToSnake();
			serverPlayer.addScore(point.getScore());
		}
		
		if(collisionSelf(serverPlayer)){
			isWin = true;
		}
		
		SnakeHead sh2 = clientPlayer.getSnakeHead();
		point = gp.getCurrentPoint();
		if(collisionPoint(sh2)) {
			gp.createNewPoint();
			clientPlayer.addPointToSnake();
			clientPlayer.addScore(point.getScore());
		}
		if(collisionSelf(clientPlayer)) {
			isWin = true;
		}
	}

	@Override
	public boolean getIsWin() {
		return isWin;
	}


}
