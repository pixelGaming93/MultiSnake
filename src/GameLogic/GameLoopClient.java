package GameLogic;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ConnectException;
import java.net.Socket;

import javax.swing.JOptionPane;

import GUI.GameFrame;
import GUI.GamePanel;
import GUI.SPanel;
import Logging.SnakeLogger;
import NetworkObjects.PlayerPointDTO;
import Player.Player;
import Points.Point;
import Snake.Direction;
import Snake.SnakeComponent;
import Snake.SnakeHead;

public class GameLoopClient extends GameLoopAbstract{
	
	/// - Variables - /// 
	public static Socket client;
	public static ObjectOutputStream outputDTO;
	public static ObjectInputStream inputDTO;
	
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
		@SuppressWarnings("unused")
		int frames = 0;
		@SuppressWarnings("unused")
		int updates = 0;
		
		try{
			client = new Socket(gf.getIpAdd(), gf.getPortAdd());
			
			
		
		if(client.isConnected()){
			
			outputDTO = new ObjectOutputStream(client.getOutputStream());
			inputDTO = new ObjectInputStream(client.getInputStream());
			
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

			while(!isWin){
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
					updates = 0;
					frames = 0;
					
				}
			}
			//Wenn ein Spieler boolischen Variable false dann verloren und lost screen
		}
		
		}catch (EOFException ee) {
			JOptionPane.showMessageDialog(gf, "Die Verbindung wurde unerwartet beendet!","Error", JOptionPane.ERROR_MESSAGE);
			backToStartPanel();
		}
		
		catch (ConnectException ce) {
			JOptionPane.showMessageDialog(gf, "Die Verbindung ist Fehlgeschlagen! Bitte versuchen Sie es erneut.", "Error", JOptionPane.ERROR_MESSAGE);
			backToStartPanel();
		}
		catch (Exception e) {
			SnakeLogger sl = new SnakeLogger();
			sl.log.severe(sl.getException(e.getClass().getName(), e.getMessage(), e.getStackTrace()));
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
		System.out.println("hier bin ich drin");
		if(coolisionOtherSnake(gf.serverPlayer, gf.clientPlayer)) {
			System.out.println("jetzt hab ich gewonnen");
			gp.showWinScreen();
		}
		if(coolisionOtherSnake(gf.clientPlayer, gf.serverPlayer)) {
			System.out.println("jetzt hab ich verloren");
			gp.showLostScreen();
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
