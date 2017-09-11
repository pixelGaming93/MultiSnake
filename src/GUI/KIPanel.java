package GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class KIPanel extends SPanel {

	/// - Variables - ///
	
	private static final long serialVersionUID = 1L;
	protected StartPanel sp;
	public int difficult = 0;
	
	/// - Methods - ///
	// - Constructor - //
	public KIPanel(StartPanel sp) {
		this.sp = sp;
		setLayout(new BorderLayout());
		JLabel nick = new JLabel("Spielername: ");
		JTextField input = new JTextField(15);
		JButton start = new JButton("Spiel starten");
		start.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				sp.startKIGame(input.getText(), difficult);
			}
		});
		JPanel south = new JPanel();
		south.setLayout(new FlowLayout());
		JLabel diffL = new JLabel("Easy");
		JButton easy = new JButton("Easy");
		easy.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				difficult = 0;
				diffL.setText("Easy");
			}
		});
		JButton medium = new JButton("Medium");
		medium.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				difficult = 1;
				diffL.setText("Medium");
			}
		});
		JButton diff = new JButton("Diff");
		diff.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				difficult = 2;
				diffL.setText("Difficult");
			}
		});
		south.add(diffL);
		south.add(easy);
		south.add(medium);
		south.add(diff);
		add(south, BorderLayout.SOUTH);
		add(nick, BorderLayout.WEST);
		add(input, BorderLayout.CENTER);
		add(start, BorderLayout.EAST);
	}
}
