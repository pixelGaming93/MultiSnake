package Snake;

public class SnakeBody extends SnakeComponent{
	
	public SnakeBody(SnakeComponent sc){
		width = sc.width;
		height = sc.height;
		direction = sc.direction;
		speed = sc.speed;
		isPortal = sc.isPortal;		
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
	
	@Override
	public String toString(){
		return "X: "+posX+" Y: "+posY+" D: "+direction;
	}

}
