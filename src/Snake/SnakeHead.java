package Snake;

public class SnakeHead extends SnakeComponent{
	
	public SnakeHead(int componentSize){
		posX = 40;
		posY = 40;
		width = componentSize;
		height = componentSize;
		direction = Direction.RIGHT;
		speed = width;
	}
}
