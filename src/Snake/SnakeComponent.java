package Snake;

import java.awt.Rectangle;

import GameLogic.GameLoop;

public class SnakeComponent {
	
	protected int posX;
	protected int posY;
	protected int width;
	protected int height;
	protected int speed;
	
	protected String direction;
	protected String nextDirection;
	
	public void move(int width, int height){
		if(direction.equals(Direction.UP))
			if(posY - speed > 0){
				posY -= speed;
			}else{
				GameLoop.setIsWin(true);
			}	
		if(direction.equals(Direction.DOWN)) 
			if(posY + speed < height){
				posY += speed;
			}else{
				GameLoop.setIsWin(true);
			}
		if(direction.equals(Direction.RIGHT)) 
			if(posX + speed < width){
				posX += speed;
			}else{
				GameLoop.setIsWin(true);
			}
		if(direction.equals(Direction.LEFT)) 
			if(posX - speed > 0){
				posX -= speed;
			}else{
				GameLoop.setIsWin(true);
			}
	}
	
	public void goUP(){
		direction = Direction.UP;
	}
	
	public void goDown(){
		direction = Direction.DOWN;
	}
	
	public void goRight(){
		direction = Direction.RIGHT;
	}
	
	public void goLeft(){
		direction = Direction.LEFT;;
	}
	
	public int getX(){
		return posX;
	}
	
	public int getY(){
		return posY;
	}
	
	public int getWidth(){
		return width;
	}
	
	public int getHeight(){
		return height;
	}
	
	public String getDirection(){
		return direction;
	}
	
	public void setNextDirection(String nextDirection){
		this.nextDirection = nextDirection;
	}
	
	public void switchDirection(){
		direction = nextDirection;
	}
	
	public Rectangle getBounds(){
		return new Rectangle(posX,posY,width,height);
	}
}
