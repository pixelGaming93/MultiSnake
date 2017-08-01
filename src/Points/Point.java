package Points;

import java.awt.Rectangle;
import java.io.Serializable;

public class Point implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/// - Variables - ///
	protected int posX;
	protected int posY;
	protected int width;
	protected int height;
	protected int score;
	
	/// - Methods - ///
	
	// - Constructor - // 
	public Point() {
		
	}
	
	// - Getter & Setter - //
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
	
	@Override
	public String toString() {
		return "X: "+getPosX() + " Y: " + getPosY() + " H : " + getHeight() + " W " + getWidth();
	}
	

}
