package GameLogic;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

import GUI.GameFrame;
import GUI.GamePanel;
import GUI.SPanel;
import NetworkObjects.PlayerPointDTO;
import Player.Player;
import Points.Point;
import Snake.Direction;
import Snake.SnakeComponent;
import Snake.SnakeHead;

public class GameLoopClient extends GameLoopAbstract{
	
	/// - Variables - /// 
	public static Socket client;
	public static InetSocketAddress server;
	
	public GameLoopClient(SPanel gp, Player clientPlayer, Point point, GameFrame gf){
		isWin = false;
		this.gp = (GamePanel)gp;
		this.gf = gf;
//		this.clientPlayer = clientPlayer;
//		this.serverPlayer = null;
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
			
			ObjectOutputStream outputDTO = new ObjectOutputStream(client.getOutputStream());
			ObjectInputStream inputDTO = new ObjectInputStream(client.getInputStream());
			
			PlayerPointDTO output = new PlayerPointDTO();
			PlayerPointDTO input;
			
			output.player = gf.clientPlayer;
			outputDTO.writeObject(output);
			outputDTO.flush();
			outputDTO.reset();
			
			input = (PlayerPointDTO)inputDTO.readObject();
			gf.clientPlayer = input.player;
			
			point = input.point;
			gf.point = point;

			while(true){
				long now = System.nanoTime();
				delta += (now-lastTime) / ns;
				lastTime = now;
				
				while(delta>= 1){
					update();
					updates++;
					delta--;
				}
				
				output.player = gf.clientPlayer;
				outputDTO.writeObject(output);
				outputDTO.flush();
				outputDTO.reset();
				
				input = (PlayerPointDTO)inputDTO.readObject();
				gf.serverPlayer = input.player;
				
				point = input.point;
				gf.point = point;
				
				render();
				frames++;
				
				if(System.currentTimeMillis() - timer > 1000){
					timer += 1000;
//					gp.setTitle(gf.getTitle() + " | " + updates + " ups, " + frames + " fps");
					updates = 0;
					frames = 0;
					
				}
			}
			//Wenn ein Spieler boolischen Variable false dann verloren und lost screen
		}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void move() {
		if(gp.getKey().up && !gf.clientPlayer.getSnakeHead().getDirection().equals(Direction.DOWN)) gf.clientPlayer.getSnakeHead().goUP();
		if(gp.getKey().down && !gf.clientPlayer.getSnakeHead().getDirection().equals(Direction.UP)) gf.clientPlayer.getSnakeHead().goDown();
		if(gp.getKey().right && !gf.clientPlayer.getSnakeHead().getDirection().equals(Direction.LEFT)) gf.clientPlayer.getSnakeHead().goRight();
		if(gp.getKey().left && !gf.clientPlayer.getSnakeHead().getDirection().equals(Direction.RIGHT)) gf.clientPlayer.getSnakeHead().goLeft();		
	}

	@Override
	public void abstractUpdate() {
		gf.clientPlayer.getSnakeHead().move(gp.getWidth(), gp.getHeight());
		String nd = gf.clientPlayer.getSnakeHead().getDirection();
		for(SnakeComponent sc : gf.clientPlayer.getSnake()) {
			sc.setNextDirection(nd);
			nd = sc.getDirection();
			sc.move(gp.getWidth(), gp.getHeight());
			sc.switchDirection();
		}
		
		if(gf.clientPlayer.getScore()%10 == 0){
			gameSpeed += 5;
		}
	}

	@Override
	public void collision() {
		SnakeHead sh = gf.clientPlayer.getSnakeHead();
		point = gp.getCurrentPoint();
		if(collisionPoint(sh)) {
			gf.clientPlayer.addPointToSnake();
			gf.clientPlayer.addScore(point.getScore());
		}
	}

	@Override
	public boolean getIsWin() {
		return isWin;
	}

}
