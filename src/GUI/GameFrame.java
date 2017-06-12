package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import GameLogic.GameLoop;
import Input.Keyboard;
import Player.Player;
import Points.NormalPoint;
import Points.Point;

public class GameFrame extends JFrame{
	
	private static final long serialVersionUID = -2363170020622765848L;
	protected static String title = "Multiplayersnake";
	protected static boolean isPortal = false;
	protected static int gameSize = 60;
	protected int componentSize = 5;
	protected SPanel sp;
	protected SPanel p;
	protected Player p1;
	protected Point point;
	protected Thread t;
	protected GameLoop gl;
	protected Keyboard key;
	
	public GameFrame(int width, int height){
		JMenuBar jmb = new JMenuBar();
		setJMenuBar(jmb);
		addSubmenu(jmb);
		
		p = new StartPanel(300,100,this);
		add(p);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public synchronized void startGame(){
		initialPlayer();
		initialPoint();
		
		t = new Thread(gl = new GameLoop(p, p1, point));
		t.start();
	}
	
	public synchronized void stopGame(){
		gl.setIsWin(true);
	}
	
	public void initialPlayer(){
		p1 = new Player(componentSize);
		p1.setPortalOn(isPortal);
	}
	
	public void initialPoint(){
		point = new NormalPoint(((int)(Math.random()*((GamePanel)p).getGridSize()))*componentSize,((int)(Math.random()*((GamePanel)p).getGridSize()))*componentSize);
	}
	
	public void addSubmenu(JMenuBar jmb){
		JMenu JMOption = new JMenu("Optionen");
		jmb.add(JMOption);
		JMenuItem JMIClose = new JMenuItem("Beenden");
		JMIClose.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		JMenuItem JMIBack = new JMenuItem("Zurück");
		JMIBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				stopGame();
				getContentPane().remove(0);
				p = new StartPanel(300,100);
				add(p);
				align();
			}
		});
		JMOption.add(JMIClose);
		JMOption.add(JMIBack);
	}
	
	public void setSP(SPanel panel){
		this.sp = panel;
	}

	public void setP(SPanel panel){
		this.p = panel;
	}
	
	public Player getPlayer(){
		return p1;
	}
	
	public Point getPoint(){
		return point;
	}
	
	public static void setPortalOn(boolean portal){
		isPortal = portal;
	}
	
	public static boolean getPortalOn(){
		return isPortal;
	}
	
	public void paintScore(){
		sp.repaint();
	}
	
	public void setGameSize(int gameSize){
		this.gameSize = gameSize;
	}
	
	public void align(){
		pack();
		setLocationRelativeTo(null);
		repaint();
	}
	
}
