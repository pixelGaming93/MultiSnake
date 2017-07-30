package Player;

import java.io.Serializable;

public class ServerPlayer extends Player implements Serializable{

	/// - Variables - ///
	private static final long serialVersionUID = 1L;

	/// - Methods - ///
	// - Constructor - //
	
	public ServerPlayer() {
		
	}
	public ServerPlayer(int componentSize, String name) {
		super(componentSize, name);
	}

}
