package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
 
/*
 * Erstellt das Panel für das Start-Menü
 * 
 */
public class StartPanel extends SPanel{

	private static final long serialVersionUID = 5890557562020940174L;
	
	/// - Variables - ///
	protected int WIDTH;
	protected int HEIGHT;
	protected static GameFrame gf;
	protected static boolean isMultiPlayer = false;
	
	/// - Methods - ///
	// - Constructor - //
	public StartPanel(int width, int height){
		this(width,height,gf);
	}
	
	// - Constructor - //
	public StartPanel(int WIDTH, int HEIGHT, GameFrame gframe){
		this.WIDTH = WIDTH;
		this.HEIGHT = HEIGHT;
		gf = gframe;
		
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setLayout(new BorderLayout());
		createStartPanel();
		gf.setResizable(false);
		
	}
	
	public void createStartPanel(){
		JPanel bPanel = new JPanel();
		bPanel.setLayout(new GridLayout(6, 1));
		// Startet einen Server und wartet auf einen Client um das Multiplayer-Spiel zu spielen
		JButton serverStart = new JButton("Server starten");
		serverStart.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				isMultiPlayer = true;
				gf.getContentPane().remove(0);
				GameFrame.mpMode = true;
				gf.setStorePanel(new ServerStartPanel(gf,StartPanel.this));
				gf.add(gf.getStorePanel());
				gf.pack();
			}
		});
		bPanel.add(serverStart);
		//Versucht einen Server zu finden um das Multiplayer-SPiel zu spielen
		JButton joinServer = new JButton("Server beitreten");
		joinServer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				isMultiPlayer = true; 
				gf.getContentPane().remove(0);
				GameFrame.mpMode = true;
				gf.setStorePanel(new ServerConnectPanel(gf,StartPanel.this));
				gf.add(gf.getStorePanel());
				gf.pack();
			}
		});
		bPanel.add(joinServer);
		//Startet das Spiel als Singleplayer
		JButton singelPlayer = new JButton("Singleplayer");
		singelPlayer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				isMultiPlayer = false;
				gf.getContentPane().remove(0);
				GameFrame.spMode = true;
				gf.setStorePanel(new NicknamePanel(StartPanel.this));
				gf.add(gf.getStorePanel());
				gf.pack();
			}
		});
		bPanel.add(singelPlayer);
		//Startet das Spiel vs KI
		JButton vsKI = new JButton("vs. KI");
		vsKI.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				gf.getContentPane().remove(0);
				GameFrame.spMode = true;
				gf.setStorePanel(new KIPanel(StartPanel.this));
				gf.add(gf.getStorePanel());
				gf.pack();
			}
		});
		bPanel.add(vsKI);
		//Öffnet das Option's-Fenster um Einstellungen zu ändern
		JButton settings = new JButton("Spiel-Optionen");
		settings.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				gf.getContentPane().remove(0);
				gf.setStorePanel(new SettingsPanel(StartPanel.this));
				gf.pack();
			}
		});
		bPanel.add(settings);
		//Schließt das Menü
		JButton cancel = new JButton("Spiel beenden");
		cancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		bPanel.add(cancel);
		//-----
		add(bPanel, BorderLayout.CENTER);
	}
	
	public void startGame(String name){
		/*
		 * Nimmt Grundeinstellungen vor und startet dann den Thread für das Spiel
		 */
		prepareGame();
		gf.startGame(name);
	}
	
	public void startKIGame(String name, int difficult) {
		/*
		 * Nimmt Grundeinstellungen vor und startet dann den Thread für das KI-Spiel
		 */
		prepareGame();
		gf.startKIGame(name, difficult);
	}
	
	public void startMGame(){
		/*
		 * Startet das Spiel ohne vorher Grundeinstellungen zu ändern. Wichtig um ein Verlorenes Spiel neu zu starten.
		 */
		startGame(gf.getServerPlayer().getName());
	}
	
	public void startSGame() {
		startGame(gf.getSinglePlayer().getName());
	}

	public void waitForOpponent(String name){
		/*
		 * Wartet auf einen anderen Spieler //Server
		 */
		prepareGame();
		gf.startMultiGame(name);
	}	
	
	public void connectToServer(String name){
		/*
		 * Versucht eine Verbindung herzustellen //Client 
		 */
		prepareGame();
		gf.connectAndStart(name);
	}
	
	public void prepareGame(){
		/*
		 * Grundeinstellungen
		 */
		gf.getContentPane().remove(0);
		gf.setLayout(new BorderLayout());
		gf.setSettingPanel(new ScorePanel(gf.getGameSize(),this,gf.getComponentSize()));
		gf.add(gf.getSettingPanel(),BorderLayout.NORTH);
		gf.setStorePanel(new GamePanel(gf.getGameSize() ,this,gf.getComponentSize(),gf));
		gf.add(gf.getStorePanel(),BorderLayout.CENTER);
	}
	
	public void fix(SPanel panel){
		/*
		 * Hilft dabei die GUI anständig darzustellen
		 */
		gf.add(panel);
		gf.setResizable(false);
		gf.align();
	}
	
	// - Getter & Setter - //
	public boolean getIsMultiPlayer(){
		return isMultiPlayer;
	}
	
	public void setIsMultiPlayer(boolean isMultiPlayer){
		this.isMultiPlayer = isMultiPlayer;
	}

}
