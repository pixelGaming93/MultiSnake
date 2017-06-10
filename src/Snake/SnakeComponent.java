package Snake;

import java.awt.Rectangle;

public class SnakeComponent {
	
	protected int posX;
	protected int posY;
	protected int width;
	protected int height;
	protected int speed;
	
	protected String direction;
	protected String nextDirection;
	
	public void move(){
		if(direction.equals(Direction.UP)) posY -= speed;
		if(direction.equals(Direction.DOWN)) posY += speed;
		if(direction.equals(Direction.RIGHT)) posX += speed;
		if(direction.equals(Direction.LEFT)) posX -= speed;
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
