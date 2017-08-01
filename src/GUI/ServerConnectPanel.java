package GUI;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

/*
 * Pannel zum Connecten eines offenen Servers
 */
public class ServerConnectPanel extends SPanel{
	
	/// - Variables - ///
	public static final long serialVersionUID = 1L;
	public GameFrame gf;
	public StartPanel sp;
	
	/// - Methods - ///
	// - Constructor - //
	public ServerConnectPanel(GameFrame gf, StartPanel sp){
		this.gf = gf;
		this.sp = sp;
		setLayout(new GridLayout(3, 3));
		JLabel nick = new JLabel("Spielername: ");
		add(nick);
//		JTextField input = new JTextField("Spieler 2");
		JTextField input = new JTextField(15);
		add(input);
		JButton start = new JButton("Spiel beitreten");
		add(start);
		JLabel port = new JLabel("Port: ");
		add(port);
//		JTextField input2 = new JTextField("2000");
		JTextField input2 = new JTextField(4);
		add(input2);
		JLabel freeLabel = new JLabel("");
		add(freeLabel);
		JLabel ip = new JLabel("IP-Adresse: ");
		add(ip);
		JTextField input3 = new JTextField("93.195.210.197");
//		JTextField input3 = new JTextField("192.168.2.130");
//		JTextField input3 = new JTextField(15);
		add(input3);
		start.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				gf.setName(input.getText());
				gf.setPortAdd(Integer.parseInt(input2.getText()));
				gf.setIpAdd(input3.getText());
				sp.connectToServer(input.getText());
			}
		});
	}
	

}
