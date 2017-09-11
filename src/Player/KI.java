package Player;

import Snake.SnakeBody;
import Snake.SnakeHead;

public class KI extends Player{
	
	/// - Methods - ///
	
	// - Constructor - //
	public KI() {
		
	}
	
	public KI(int componentSize, int difficult, int x, int y, String direction) {
		this.name = name;
		score = 0;
		sh = new SnakeHead(componentSize, x, y, direction);
		snake.add(new SnakeBody(sh));
		for(int i = 0; i < 4; i++) {
			snake.add(new SnakeBody(snake.get(i)));
		}
	}
	

}
