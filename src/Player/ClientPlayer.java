package Player;

import java.io.Serializable;

public class ClientPlayer extends Player implements Serializable{

	/// - Variables - ///
	private static final long serialVersionUID = 1L;

	/// - Methods - ///
	// - Constructor - //
	
	public ClientPlayer() {
		
	}
	public ClientPlayer(int componentSize, String name) {
		super(componentSize, name);
	}
	
	public ClientPlayer(int componentSize, String name, int x, int y, String direction) {
		super(componentSize, name, x, y, direction);
	}
}
