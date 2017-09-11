package Shoots;

import java.awt.Color;
import java.awt.Rectangle;

import GUI.GameFrame;
import Snake.Direction;
import Snake.SnakeComponent;

public class Shoot {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/// - Variables - ///
	protected SnakeComponent sc;
	protected String direction;
	protected int x;
	protected int y;
	protected int range;
	
	protected Color color;
	
	protected int posX;
	protected int posY;
	protected int width;
	protected int height;
	protected int speed;
	
	protected int gfWidth;
	protected int gfHeight;
	
	/// - Methods - /// 
	
	// - Constructor - //
	public Shoot(int x, int y, String direction, SnakeComponent sc, int componentSize, int gfWidth, int gfHeight) {
		this.sc = sc;
		range = GameFrame.getSinglePlayer().getSnake().size() + 1;
		this.direction = direction;
		this.x = x;
		this.y = y;
		width = componentSize;
		height = componentSize;
		speed = componentSize;
		this.gfWidth = gfWidth;
		this.gfHeight = gfHeight;
		color = Color.red;
		if(direction.equals(Direction.UP)) {
			if(GameFrame.isPortal) {
				if(y - speed < 0) {
					posY = gfHeight;
					posX = x;
				}else {
					posY = y - speed;
					posX = x;
				}
			}else {
				if(y - speed < 0) {
					GameFrame.singlePlayerS = null;
				}else {
					posY = y - speed;
					posX = x;
				}
			}
		}
		if(direction.equals(Direction.DOWN)) {
			if(GameFrame.isPortal) {
				if(y + speed > gfHeight) {
					posY = 0;
					posX = 0;
				}else {
					posY = y + speed;
					posX = x;
				}
			}else {
				if(y + speed > gfHeight) {
					GameFrame.singlePlayerS = null;
				}else {
					posY = y + speed;
					posX = x;
				}
			}
		}
		if(direction.equals(Direction.RIGHT)) {
			if(GameFrame.isPortal) {
				if(x + speed > gfWidth) {
					posY = y;
					posX = 0;
				}else {
					posY = y;
					posX = x + speed;
				}
			}else {
				if(x + speed > gfWidth) {
					GameFrame.singlePlayerS = null;
				}else {
					posY = y;
					posX = x + speed;
				}
			}
		}
		if(direction.equals(Direction.LEFT)) {
			if(GameFrame.isPortal) {
				if(x - speed < 0) {
					posY = y;
					posX = width;
				}else {
					posY = y;
					posX = x - speed;
				}
			}else {
				if(x - speed < 0) {
					GameFrame.singlePlayerS = null;
				}else {
					posY = y;
					posX = x - speed;
				}
			}
		}
		
	}
	
	public void move() {
		if(range > 0) {
				if(direction.equals(Direction.UP)) {
					if(GameFrame.isPortal) {
						if(posY - speed < 0) {
							posY = gfHeight;
						}else {
							posY -= speed;
						}
					}else {
						if(posY - speed < 0) {
							GameFrame.singlePlayerS = null;
						}else {
							posY -= speed;
						}
					}
			}
			if(direction.equals(Direction.DOWN)) {
				if(GameFrame.isPortal) {
					if(posY + speed > gfHeight) {
						posY = 0;
					}else {
						posY += speed;
					}
				}else {
					if(posY + speed > gfHeight) {
						GameFrame.singlePlayerS = null;
					}else {
						posY += speed;
					}
				}
			}
			if(direction.equals(Direction.RIGHT)) {
				if(GameFrame.isPortal) {
					if(posX + speed > gfWidth) {
						posX = 0;
					}else {
						posX += speed;
					}
				}else {
					if(posX + speed > gfWidth) {
						GameFrame.singlePlayerS = null;
					}else {
						posX += speed;
					}
				}
			}
			if(direction.equals(Direction.LEFT)) {
				if(GameFrame.isPortal) {
					if(posX - speed < 0) {
						posX = width;
					}else {
						posX -= speed;
					}
				}else {
					if(posX - speed < 0) {
						GameFrame.singlePlayerS = null;
					}else {
						posX -= speed;
					}
				}
			}
			range -= 1;
		}else {
			GameFrame.singlePlayerS = null;
		}
	}
	
	public Rectangle getBounds() {
		return new Rectangle(posX,posY,width,height);
	}
	
	// - Getter & Setter - //
	public int getPosX() {
		return posX;
	}
	
	public void setPosX(int posX) {
		this.posX = posX;
	}
	
	public int getPosY() {
		return posY;
	}
	
	public void setPosY(int posY) {
		this.posY = posY;
	}
	
	public int getWidth() {
		return width;
	}
	
	public void setWidth(int width) {
		this.width = width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public void setHeight(int height) {
		this.height = height;
	}
	
	
	
}
