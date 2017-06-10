package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

public class ScorePanel extends SPanel{

	protected StartPanel sp;
	
	public ScorePanel(int gridSize, StartPanel sp, int componentSize){
		this.sp = sp;
		setPreferredSize(new Dimension(gridSize * componentSize, 20));
		sp.fix(this);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		setBackground(Color.BLACK);
		g.setColor(Color.WHITE);
		g.drawString(sp.gf.p1.getName() + ": " + sp.gf.p1.getScore(), 10, 10);
		
		
	}
}
