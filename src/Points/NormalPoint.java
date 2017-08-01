package Points;

import java.io.Serializable;

public class NormalPoint extends Point implements Serializable{

	/// - Methods - ///
	
	// - Constructor - //
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NormalPoint() {
		
	}
	
	public NormalPoint(int posX, int posY) {
		this.posX = posX;
		this.posY = posY;
		width = 5;
		height = 5;
		score = 1;
	}

}
