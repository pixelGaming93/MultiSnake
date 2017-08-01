package GUI;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;


/*
 * Panel zum Starten eines Servers
 */
public class ServerStartPanel extends SPanel{

	/// - Variables - ///
	public static final long serialVersionUID = 1L;
	public GameFrame gf;
	public StartPanel sp;
	
	/// - Methods - ///
	// - Constructor - //
	public ServerStartPanel(GameFrame gf, StartPanel sp){
		this.gf = gf;
		this.sp = sp;
		setLayout(new GridLayout(3, 2));
		JLabel nick = new JLabel("Spielername: ");
		add(nick);
		JTextField input = new JTextField(15);
		add(input);
		JLabel port = new JLabel("Port: ");
		add(port);
		JTextField input2 = new JTextField(4);
		add(input2);
		JLabel freeLabel = new JLabel("");
		add(freeLabel);
		JButton start = new JButton("Spiel starten");
		add(start);
		start.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				gf.setName(input.getText());
				gf.setPortAdd(Integer.parseInt(input2.getText()));
				sp.waitForOpponent(input.getText());
			}
		});
	}
}
