package Snake;

import java.io.Serializable;

/*
 * Die Klasse stellt den Kopf der Schlage dar.
 */
public class SnakeHead extends SnakeComponent implements Serializable{
	/// - Methods - ///
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// - Constructor - //
	public SnakeHead(int componentSize){
		posX = 40;
		posY = 40;
		width = componentSize;
		height = componentSize;
		direction = Direction.RIGHT;
		speed = width;
	}
	
	public SnakeHead(int componentSize, int x, int y, String direction) {
		posX = x;
		posY = y;
		width = componentSize;
		height = componentSize;
		this.direction = direction;
		speed = width;
	}

	@Override
	public void setIsWin(boolean isWin) {
		this.isWin = isWin;
		
	}
	
	@Override
	public String toString() {
		return posX + " - " + posY + " - " + direction;
	}
}
