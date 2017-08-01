package GUI;



import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import NetworkObjects.ExternalIP;
import NetworkObjects.OwnIPAddress;

/*
 * Das Panel listet verschiedene Einstellungen auf, die ein Spieler vor dem Starten ändern kann
 */
public class SettingsPanel extends SPanel{
	
	/// - Variables - ///
	private static final long serialVersionUID = -7198251507030778750L;
	protected int WIDTH;
	protected int HEIGHT;
	protected StartPanel sp;
	
	/// - Methods - ///
	// - Constructor - //
	public SettingsPanel(StartPanel sp){
		this.sp = sp;
		setLayout(new GridLayout(10, 1));
		JButton portal = new JButton("Portal einschalten");
		portal.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(StartPanel.gf.getPortalOn()){
					portal.setText("Portal einschalten");
					StartPanel.gf.setPortalOn(false);
				}else{
					portal.setText("Portal ausschalten");
					StartPanel.gf.setPortalOn(true);
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
				StartPanel.gf.setGameSize(40);
			}
		});
		JButton medium = new JButton("Mittleres Spielfeld");
		medium.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				StartPanel.gf.setGameSize(60);
			}
		});
		JButton large = new JButton("Großes Spielfeld");
		large.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				StartPanel.gf.setGameSize(100);
			}
		});
		
		gSP.add(small, BorderLayout.WEST);
		gSP.add(medium, BorderLayout.CENTER);
		gSP.add(large, BorderLayout.EAST);
		add(gSP);
		JPanel ipP = new JPanel();
		ipP.setLayout(new BorderLayout());
		JPanel labelP = new JPanel();
		labelP.setLayout(new GridLayout(2,1));
		JPanel label2P = new JPanel();
		label2P.setLayout(new GridLayout(2,1));
		JLabel externalIPL = new JLabel("Externe IP-Adresse:");
		JLabel externalIP = new JLabel(ExternalIP.getExternalIP());
		JLabel lokalIPL = new JLabel("Lokale IP-Adresse:");
		JLabel lokalIP = new JLabel(OwnIPAddress.getIP());
		labelP.add(externalIPL);
		labelP.add(lokalIPL);
		label2P.add(externalIP);
		label2P.add(lokalIP);
		ipP.add(labelP, BorderLayout.WEST);
		ipP.add(label2P, BorderLayout.EAST);
		add(ipP);
		sp.fix(this);
	}
	
	

}
