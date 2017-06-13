package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class LostPanel extends SPanel{
	
	protected int WIDTH;
	protected int HEIGHT;
	protected StartPanel sp;
	
	public LostPanel(int WIDTH, int HEIGHT, StartPanel sp){
		this.WIDTH = WIDTH;
		this.HEIGHT = HEIGHT;
		this.sp = sp;
		setPreferredSize(new Dimension(WIDTH,HEIGHT));
		setLayout(new BorderLayout());
		JPanel bp = new JPanel();
		JLabel lost = new JLabel("Du hast verloren!");
		add(lost, BorderLayout.CENTER);
		JButton restart = new JButton("Neustarten");
		restart.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				sp.startGame();
			}
		});
		JButton back = new JButton("Zurück ins Menü");
		back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				sp.gf.getContentPane().remove(0);
				sp.gf.p = new StartPanel(300,100);
				sp.gf.add(sp.gf.p);
				sp.gf.align();
			}
		});
		bp.setLayout(new GridLayout(1, 2));
		bp.add(restart);
		bp.add(back);
		add(bp, BorderLayout.SOUTH);
	}
	
}
