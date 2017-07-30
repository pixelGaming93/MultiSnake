package Snake;

import java.io.Serializable;

/*
 * Die Klasse stellt ein Körperteil der Schlage dar.
 */
public class SnakeBody extends SnakeComponent implements Serializable{
	
	/// - Methods - /// 
	
	// - Constructor - //
	public SnakeBody(SnakeComponent sc){
		width = sc.width;
		height = sc.height;
		direction = sc.direction;
		speed = sc.speed;
		isPortal = sc.isPortal;		
		/*
		 * Die Richtung muss beim erstellen Variable sein
		 * Kommt auf die Position der Schlange und die Ausrichtung dieser an
		 * Die Richtung bezieht sich immer auf die vorlauffende Komponente und
		 * setzt dann den neuen Teil an die richtige Stelle.
		 */
		if(direction.equals(Direction.RIGHT)){
			posX = sc.posX - sc.width;
			posY = sc.posY;
		}
		if(direction.equals(Direction.LEFT)){
			posX = sc.posX + sc.width;
			posY = sc.posY;
		}
		if(direction.equals(Direction.UP)){
			posX = sc.posX;
			posY = sc.posY + sc.height;
		}
		if(direction.equals(Direction.DOWN)){
			posX = sc.posX;
			posY = sc.posY - sc.height;
		}
		
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 * Damit kann zu Test-Zwecken die Position einer Komponente ausgegeben werden.
	 */
	@Override
	public String toString(){
		return "X: "+posX+" Y: "+posY+" D: "+direction;
	}

	@Override
	public void setIsWin(boolean isWin) {
		this.isWin = isWin;
		
	}

}
