package Player;


import java.util.ArrayList;

import Snake.SnakeBody;
import Snake.SnakeComponent;
import Snake.SnakeHead;

public class Player{
	
	protected String name;
	protected long score;
	protected SnakeHead sh;
	protected ArrayList<SnakeComponent> snake = new ArrayList<SnakeComponent>();
	
	public Player(int componentSize){
		this.name = "Player 1";
		score = 0;
		sh = new SnakeHead(componentSize);
		snake.add(new SnakeBody(sh));
		for(int i = 0; i < 4; i++){
			snake.add(new SnakeBody(snake.get(i)));
		}
	}
	
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
}
