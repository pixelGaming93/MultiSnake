package NetworkObjects;

import java.io.Serializable;

import Player.Player;
import Points.Point;

public class PlayerPointDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Point point;
	public Player player;
	
}
