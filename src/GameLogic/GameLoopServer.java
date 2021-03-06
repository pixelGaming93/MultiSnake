package GameLogic;

import java.io.EOFException;
import java.io.IOException;
import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JOptionPane;

import GUI.GameFrame;
import GUI.GamePanel;
import GUI.MultiSnake;
import GUI.SPanel;
import Logging.SnakeLogger;
import NetworkObjects.PlayerPointDTO;
import Player.Player;
import Points.Point;
import Snake.Direction;
import Snake.SnakeComponent;
import Snake.SnakeHead;

public class GameLoopServer extends GameLoopAbstract{
	
	/// - Variables - ///
	public static ServerSocket server;
	public static Socket client;
	public static ObjectOutputStream outputDTO;
	public static ObjectInputStream inputDTO;
	
	public GameLoopServer(SPanel gp,  Player serverPlayer, Point point, GameFrame gf){
		isWin = false;
		this.gp = (GamePanel)gp;
		this.gf = gf;
//		this.serverPlayer = serverPlayer;
		this.point = point;
		gameSpeed = 15.0;
	}

//	@SuppressWarnings("deprecation")
	@Override
	public void run() {
		final double ns = 1000000000.0 / gameSpeed;
		double delta = 0;
		@SuppressWarnings("unused")
		int frames = 0;
		@SuppressWarnings("unused")
		int updates = 0;
		
		try{
			
			server = new ServerSocket(gf.getPortAdd());
			client = server.accept();
			
			
			if(client.isConnected()){
				inputDTO = new ObjectInputStream(client.getInputStream());
				outputDTO = new ObjectOutputStream(client.getOutputStream());
				
				PlayerPointDTO output = new PlayerPointDTO();
				PlayerPointDTO input = new PlayerPointDTO();
				
				
				gf.clientPlayer = ((PlayerPointDTO)inputDTO.readObject()).player;
				gf.clientPlayer.setPortalOn(gf.getPortalOn());
				
				output.player = gf.clientPlayer;
				output.point = gf.point;
				outputDTO.writeObject(output);
				outputDTO.flush();
				outputDTO.reset();
				
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
					input = (PlayerPointDTO) inputDTO.readObject();
					gf.clientPlayer = input.player;
					
					output.player = gf.serverPlayer;
					output.point = gf.point;
					
					outputDTO.writeObject(output);
					outputDTO.flush();
					outputDTO.reset();
					
					render();
					frames++;
					
					if(System.currentTimeMillis() - timer > 1000){
						timer += 1000;
//						gp.setTitle(gf.getTitle() + " | " + updates + " ups, " + frames + " fps");
						updates = 0;
						frames = 0;
					}
				}
				
			}
		}catch (EOFException ee) {
			JOptionPane.showMessageDialog(gf, "Die Verbindung wurde unerwartet beendet!","Error", JOptionPane.ERROR_MESSAGE);
			backToStartPanel();
		}catch(Exception e){
			SnakeLogger sl = new SnakeLogger();
			sl.log.severe(sl.getException(e.getClass().getName(), e.getMessage(), e.getStackTrace()));
		}
	}

	
	@Override
	public void move() {
		if(gp.getKey().up && !gf.serverPlayer.getSnakeHead().getDirection().equals(Direction.DOWN)) gf.serverPlayer.getSnakeHead().goUP();
		if(gp.getKey().down && !gf.serverPlayer.getSnakeHead().getDirection().equals(Direction.UP)) gf.serverPlayer.getSnakeHead().goDown();
		if(gp.getKey().right && !gf.serverPlayer.getSnakeHead().getDirection().equals(Direction.LEFT)) gf.serverPlayer.getSnakeHead().goRight();
		if(gp.getKey().left && !gf.serverPlayer.getSnakeHead().getDirection().equals(Direction.RIGHT)) gf.serverPlayer.getSnakeHead().goLeft();
	}
	
	@Override
	public void abstractUpdate() {
		gf.serverPlayer.getSnakeHead().move(gp.getWidth(), gp.getHeight());
		String nd = gf.serverPlayer.getSnakeHead().getDirection();
		for(SnakeComponent sc : gf.serverPlayer.getSnake()){
			sc.setNextDirection(nd);
			nd = sc.getDirection();
			sc.move(gp.getWidth(), gp.getHeight());
			sc.switchDirection();
		}
		
		if(gf.serverPlayer.getScore()%10 == 0){
			gameSpeed += 5;
		}
		
		if(gf.clientPlayer.getSnakeHead().getIsWin() == true) {
			isWin = true;
		}
	}
	
	@Override
	public void collision() {
		SnakeHead sh1 = gf.serverPlayer.getSnakeHead();
		point = gp.getCurrentPoint();
		if(collisionPoint(sh1)){
			gp.createNewPoint();
			gf.serverPlayer.addPointToSnake();
			gf.serverPlayer.addScore(point.getScore());
		}
		
		if(collisionSelf(gf.serverPlayer)){
			isWin = true;
			//Wenn ein Spieler boolischen Variable false dann verloren und lost screen
			gp.showLostScreen();
		}
		
		SnakeHead sh2 = gf.clientPlayer.getSnakeHead();
		point = gp.getCurrentPoint();
		if(collisionPoint(sh2)) {
			gp.createNewPoint();
		}
		if(collisionSelf(gf.clientPlayer)) {
			isWin = true;
			gp.showWinScreen();
		}
		if(coolisionOtherSnake(gf.serverPlayer, gf.clientPlayer)) {
			isWin = true;
			//Wenn ein Spieler boolischen Variable false dann verloren und lost screen
			gp.showLostScreen();
		}
		if(coolisionOtherSnake(gf.clientPlayer, gf.serverPlayer)) {
			isWin = true;
			gp.showWinScreen();
		}
	}

	@Override
	public boolean getIsWin() {
		return isWin;
	}
	
	@Override
	public void closeStreams() {
		try {
			outputDTO.close();
			inputDTO.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
