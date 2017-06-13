package GUI;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class NicknamePanel extends SPanel{
	
	protected StartPanel sp;
	
	public NicknamePanel(StartPanel sp){
		this.sp = sp;
		setLayout(new BorderLayout());
		JLabel nick = new JLabel("Spielername: ");
		JTextField input = new JTextField(15);
		JButton start = new JButton("Spiel starten");
		start.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				sp.startGame(input.getText());
			}
		});
		add(nick, BorderLayout.WEST);
		add(input, BorderLayout.CENTER);
		add(start, BorderLayout.EAST);
		
	}
}
