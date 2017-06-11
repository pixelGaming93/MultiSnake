package GUI;



import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

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
				System.out.println(sp.gf.getPortalOn());
			}
		});
		add(portal);
		sp.fix(this);
	}

}
