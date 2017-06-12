package GUI;



import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class SettingsPanel extends SPanel{
	
	private static final long serialVersionUID = -7198251507030778750L;
	protected int WIDTH;
	protected int HEIGHT;
	protected StartPanel sp;
	
	
	public SettingsPanel(StartPanel sp){
		this.sp = sp;
		setLayout(new GridLayout(10, 1));
		JButton portal = new JButton("Portal einschalten");
		portal.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(sp.gf.getPortalOn()){
					portal.setText("Portal einschalten");
					sp.gf.setPortalOn(false);
				}else{
					portal.setText("Portal ausschalten");
					sp.gf.setPortalOn(true);
				}
			}
		});
		add(portal);
		JPanel gSP = new JPanel();
		gSP.setLayout(new BorderLayout());
		JButton small = new JButton("Kleines Spielfeld");
		small.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				sp.gf.setGameSize(40);
			}
		});
		JButton medium = new JButton("Mittleres Spielfeld");
		medium.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				sp.gf.setGameSize(60);
			}
		});
		JButton large = new JButton("Großes Spielfeld");
		large.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				sp.gf.setGameSize(100);
			}
		});
		gSP.add(small, BorderLayout.WEST);
		gSP.add(medium, BorderLayout.CENTER);
		gSP.add(large, BorderLayout.EAST);
		add(gSP);
		
		sp.fix(this);
	}
	
	

}
