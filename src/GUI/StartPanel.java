package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
 

public class StartPanel extends SPanel{

	private static final long serialVersionUID = 5890557562020940174L;
	protected int WIDTH;
	protected int HEIGHT;
	protected static GameFrame gf;
	protected StartPanel sp;
	
	public StartPanel(int width, int height){
		this(width,height,gf);
	}
	
	public StartPanel(int WIDTH, int HEIGHT, GameFrame gframe){
		this.WIDTH = WIDTH;
		this.HEIGHT = HEIGHT;
		gf = gframe;
		sp = this;
		
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setLayout(new BorderLayout());
		JPanel bPanel = new JPanel();
		bPanel.setLayout(new GridLayout(3, 1));
		JButton start = new JButton("Start");
		start.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				gf.getContentPane().remove(0);
				gf.setLayout(new BorderLayout());
				gf.setSP(new ScorePanel(100,sp,gf.componentSize));
				gf.add(gf.sp,BorderLayout.NORTH);
				gf.setP(new GamePanel(100,sp,gf.componentSize));
				gf.add(gf.p,BorderLayout.CENTER);
 				gf.startGame();
			}
		});
		bPanel.add(start);
		JButton settings = new JButton("Spiel-Optionen");
		settings.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				gf.getContentPane().remove(0);
				gf.setP(new SettingsPanel(sp));
			}
		});
		bPanel.add(settings);
		JButton cancel = new JButton("Spiel beenden");
		cancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		bPanel.add(cancel);
		add(bPanel, BorderLayout.CENTER);
		
	}
	
	public void fix(SPanel panel){
		gf.add(panel);
		gf.align();
	}

}
