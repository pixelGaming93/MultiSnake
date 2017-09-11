package Player;


import java.io.Serializable;
import java.util.ArrayList;

import Snake.SnakeBody;
import Snake.SnakeComponent;
import Snake.SnakeHead;

public class Player implements Serializable{
	
	private static final long serialVersionUID = 1L;
	public String name;
	public long score;
	public SnakeHead sh;
	public ArrayList<SnakeComponent> snake = new ArrayList<SnakeComponent>();
	public boolean isPortal;
	
	/// - Methods - ///
	
	// - Constructor - //
	
	public Player() {
		
	}
	
	public Player(int componentSize, String name){
		this.name = name;
		score = 0;
		sh = new SnakeHead(componentSize);
		snake.add(new SnakeBody(sh));
		/*
		 * Standardgröße der Schlange
		 * 1 Kopf + 4 Körperteile
		 */
		for(int i = 0; i < 4; i++){
			snake.add(new SnakeBody(snake.get(i)));
		}
	}
	
	public Player(int componentSize, String name, int x, int y, String direction) {
		this.name = name;
		score = 0;
		sh = new SnakeHead(componentSize, x, y, direction);
		snake.add(new SnakeBody(sh));
		/*
		 * Standardgröße der Schlange
		 * 1 Kopf + 4 Körperteile
		 */
		for(int i = 0; i < 4; i++){
			snake.add(new SnakeBody(snake.get(i)));
		}
	}
	
	public void hit() {
		score -= 1;
		ArrayList<SnakeComponent> store = snake;
		store.remove(snake.size()-1);
		snake = store;
	}
	
	// - Getter & Setter - //
	public SnakeHead getSnakeHead(){
		return sh;
	}
	
	public ArrayList<SnakeComponent> getSnake(){
		return snake;
	}
	
	public void addPointToSnake(){
		snake.add(new SnakeBody(snake.get(snake.size()-1)));
	}
	
	public long getScore(){
		return score;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getName(){
		return name;
	}
	
	public void addScore(int score){
		this.score += score;
	}
	
	public void setPortalOn(boolean isPortal){
		sh.setPortalOn(isPortal);
		for(SnakeComponent sc : snake){
			sc.setPortalOn(isPortal);
		}
	}
	
	public boolean getPortalOn() {
		return sh.isPortal;
	}
	
	
	@Override
	public String toString() {
		return name + " - " + score + " - " + sh.getX() + " - " + sh.getY();
	}
}
