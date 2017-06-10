package GUI;

import java.awt.Dimension;

public class SettingsPanel extends SPanel{
	
	private static final long serialVersionUID = -7198251507030778750L;
	protected int WIDTH;
	protected int HEIGHT;
	
	public SettingsPanel(int WIDTH, int HEIGHT, StartPanel sp){
		this.WIDTH = WIDTH;
		this.HEIGHT = HEIGHT;
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		sp.fix(this);
	}

}
