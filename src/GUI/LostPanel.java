package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/*
 * Das Panel dass erscheint, wenn ein Spiel Verloren wurde
 */
public class LostPanel extends SPanel{
	
	/// - Variables - ///
	private static final long serialVersionUID = 1L;
	protected int WIDTH;
	protected int HEIGHT;
	
	/// - Methods - ///
	// - Constructor - // 
	public LostPanel(int WIDTH, int HEIGHT, StartPanel sp){
		this.WIDTH = WIDTH;
		this.HEIGHT = HEIGHT;
		setPreferredSize(new Dimension(WIDTH,HEIGHT));
		setLayout(new BorderLayout());
		JPanel bp = new JPanel();
		JLabel lost = new JLabel("Du hast verloren!");
		add(lost, BorderLayout.CENTER);
		JButton restart = new JButton("Neustarten");
		restart.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(GameFrame.mpMode) {
					sp.startMGame();
				}
				
				if(GameFrame.spMode) {
					sp.startSGame();
				}
			}
		});
		JButton back = new JButton("Zurück ins Menü");
		back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				StartPanel.gf.getContentPane().remove(0);
				GameFrame.mpMode = false;
				GameFrame.spMode = false;
				StartPanel.gf.setStorePanel(new StartPanel(300,100));
				StartPanel.gf.add(StartPanel.gf.getStorePanel());
				StartPanel.gf.align();
			}
		});
		bp.setLayout(new GridLayout(1, 2));
		bp.add(restart);
		bp.add(back);
		add(bp, BorderLayout.SOUTH);
	}
	
}
