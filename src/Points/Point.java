package Points;

import java.awt.Rectangle;

public class Point {
	
	protected int posX;
	protected int posY;
	protected int width;
	protected int height;
	protected int score;
	
	public int getPosX(){
		return posX;
	}
	
	public void setPosX(int posX){
		this.posX = posX;
	}
	
	public int getPosY(){
		return posY;
	}

	public void setPosY(int posY){
		this.posY = posY;
	}
	
	public int getHeight(){
		return height;
	}
	
	public void setHeight(int height){
		this.height = height;
	}
	
	public int getWidth(){
		return width;
	}

	public void setWidth(int width){
		this.width = width;
	}
	
	public Rectangle getBounds(){
		return new Rectangle(posX,posY,width,height);
	}
	
	public int getScore(){
		return score;
	}
	

}
