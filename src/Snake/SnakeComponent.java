package Snake;

import java.awt.Rectangle;
import java.io.Serializable;

/*
 * Eine Oberklasse für sowohl Körper als auch Kopf der Schlange.
 */
public abstract class SnakeComponent implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/// - Variables - /// 
	public int posX;
	public int posY;
	public int width;
	public int height;
	public int speed;
	public boolean isWin;
	
	public String direction;
	public String nextDirection;
	public boolean isPortal = false;
	
	/// - Methods - ///
	
	public void move(int width, int height) {
		/*
		 * Steuerung der Schlange, wie bewegt Sie sich
		 * Verschiedene Einstellungen können über Parameter ein oder ausgeschaltet werden
		 * Eine Direction gibt an, in welche Richtung sich der Schlangen-Teil bewegt
		 * -isPortal: Die Schlange kann durch Wände gehen und kommt an der Gegenüberliegenden Seite heraus
		 * -speed: Referenziert auf einen festen Wert. Um diese Einheit bewegt sich die Schlange hinvort. 
		 */
		if(direction.equals(Direction.UP)){
			
			if(isPortal){
				if(posY - speed < 0){
					
					posY = height;
					
				}else{
					
					posY -= speed;
					
				}
			}else{
				if(posY - speed < 0){
					
					setIsWin(true);
					
				}else{
					
					posY -= speed;
					
				}	
			}
		}
		if(direction.equals(Direction.DOWN)){
			if(isPortal){
				if(posY + speed > height){
					
					posY = 0;
					
				}else{
					
					posY += speed;
				
				}
			}else{
				if(posY + speed > height){
					
					setIsWin(true);

				}else{
					
					posY += speed;
				
				}
			}
		}
		if(direction.equals(Direction.RIGHT)){
			if(isPortal){
				if(posX + speed > width){
				
					posX = 0;
			
				}else{
				
					posX += speed;
				
				}
			}else{
				if(posX + speed > width){
				
					setIsWin(true);
				
				}else{
				
					posX += speed;
				
				}
			}
		}
		if(direction.equals(Direction.LEFT)){
			if(isPortal){
				if(posX - speed < 0){
				
					posX = width;
				
				}else{
				
					posX -= speed;
				
				}
			}else{
				if(posX - speed < 0){
				
					setIsWin(true);
				
				}else{
					
					posX -= speed;
					
				}
			}
		}
	}
	
	
	public void goUP(){
		/*
		 * Setzt die Directon der Schlangen-Komponente auf Up. Somit bewegt sich die Schlange nach oben.
		 */
		direction = Direction.UP;
	
	}
	
	public void goDown(){
		/*
		 * Setzt die Directon der Schlangen-Komponente auf Down. Somit bewegt sich die Schlange nach unten.
		 */
		direction = Direction.DOWN;
	
	}
	
	public void goRight(){
		/*
		 * Setzt die Directon der Schlangen-Komponente auf Right. Somit bewegt sich die Schlange nach Rechts.
		 */
		direction = Direction.RIGHT;
	
	}
	
	public void goLeft(){
		/*
		 * Setzt die Directon der Schlangen-Komponente auf Left. Somit bewegt sich die Schlange nach Links.
		 */
		direction = Direction.LEFT;;
	
	}
	
	public void switchDirection(){
		/*
		 * Die Methode wird dafür verwendet die Richtung zu ändern.
		 * Die neue Richtung ist die Richtung vom Vorgänger.
		 */
		direction = nextDirection;
		
	}

	// - Getter & Setter - //
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
	
	
	public Rectangle getBounds(){
	
		return new Rectangle(posX,posY,width,height);
	
	}
	
	public void setPortalOn(boolean isPortal){
	
		this.isPortal = isPortal;
	
	}
	
	public boolean getPortalOn() {
		return isPortal;
	}
	
	public abstract void setIsWin(boolean isWin);
	
	public boolean getIsWin() {
		return isWin;
	}
}
