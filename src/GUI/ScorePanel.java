package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

/*
 * 
 */
public class ScorePanel extends SPanel{
	
	/// - Variables - ///
	private static final long serialVersionUID = 1L;
	
	/// - Methods - ///
	// - Constructor - //
	public ScorePanel(int gridSize, StartPanel sp, int componentSize){
		setPreferredSize(new Dimension(gridSize * componentSize, 20));
		sp.fix(this);
	}

	@Override
	protected void paintComponent(Graphics g) {
		/*
		 * Paint methode, dass den Score des Spielers auf das Panel schreibt.
		 */
		super.paintComponent(g);
		setBackground(Color.BLACK);
		g.setColor(Color.WHITE);
		g.drawString(StartPanel.gf.getServerPlayer().getName() + ": " + StartPanel.gf.getServerPlayer().getScore(), 10, 10);
		g.drawString(StartPanel.gf.getClientPlayer().getName() + ": " + StartPanel.gf.getClientPlayer().getScore(), 200, 10);
		
		
	}
}
