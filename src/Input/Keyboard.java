package Input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
/*
 * Ein KeyListener der die Inputs der Tastatur verwaltet
 */
public class Keyboard implements KeyListener{
	
	/// - Variables - ///
	private boolean[] keys = new boolean[160];
	public boolean up, down, left, right;
	
	/// - Methods - ///
	public void update(){
		up = keys[KeyEvent.VK_UP] || keys[KeyEvent.VK_W];
		down = keys[KeyEvent.VK_DOWN] || keys[KeyEvent.VK_S];
		left = keys[KeyEvent.VK_LEFT] || keys[KeyEvent.VK_A];
		right = keys[KeyEvent.VK_RIGHT] || keys[KeyEvent.VK_D];
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
	}

}
