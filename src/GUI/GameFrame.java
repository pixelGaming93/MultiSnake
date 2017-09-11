package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import GameLogic.GameLoopAbstract;
import GameLogic.GameLoopClient;
import GameLogic.GameLoopServer;
import GameLogic.GameLoopSingle;
import NetworkObjects.OwnIPAddress;
import Player.KI;
import Player.Player;
import Points.NormalPoint;
import Points.Point;
import Shoots.Shoot;
import Snake.Direction;
import Snake.SnakeComponent;

public class GameFrame extends JFrame{
	
	public static final long serialVersionUID = -2363170020622765848L;
	
	/// - Variables - ///
	public String title = "Multiplayersnake "+OwnIPAddress.getIP();
	public int componentSize = 5;
	public Point point;
	public Thread t;
	public GameLoopAbstract gl;
	
	// - Settings - // 
	public static boolean isPortal = false;
	public static int gameSize = 60;
	
	// - GameMode - //
	public static boolean mpMode = false;
	public static boolean spMode = false;
	
	// - Player - //
	public static Player singlePlayer;
	public Player serverPlayer;
	public Player clientPlayer;
	
	// - KI - //
	public static KI ki;
	
	// - Shoots - //
	public static Shoot singlePlayerS = null;
	
	// - Server - //
	public int portAdd;
	public String ipAdd;
	
	// - Panel - // 
	public SPanel settingPanel;
	public SPanel storePanel;
	
	/// - Constructor - ///
	public GameFrame(){
		JMenuBar jmb = new JMenuBar();
		setJMenuBar(jmb);
		addSubmenu(jmb);
		
		storePanel = new StartPanel(300,200,this);
		add(storePanel);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	/// - Methods - ///
	
	public synchronized void startGame(String name){
		/*
		 * Startet das Spiel 
		 * Initialisiert einen Spieler und einen Punkt
		 * erzeugt einen neuen Thread
		 */
		initialSinglePlayer(name);
		initialPoint();
		t = new Thread(gl = new GameLoopSingle(storePanel, serverPlayer, point, this));
		t.start();
	}
	
	public synchronized void startKIGame(String name, int difficult) {
		/*
		 * Startet das KI-Spiel 
		 * Initialisiert einen Spieler, eine KI und einen Punkt
		 * erzeugt einen neuen Thread
		 */
		initialSinglePlayer(name);
		initialKI(difficult);
		initialPoint();
		t = new Thread(gl = new GameLoopSingle(storePanel, singlePlayer, point, this));
		t.start();
	}
	
	public synchronized void startMultiGame(String name){
		/*
		 * Startet ein Multiplayerspiel
		 * Initialisiert einen neuen Spieler
		 * Startet einen neuen Thread
		 */
		initialServerPlayer(name);
		initialClientOpponent("");
		initialPoint(250, 250);
		t = new Thread(gl = new GameLoopServer(storePanel, serverPlayer, point, this));
		t.start();
	}
	
	public void connectAndStart(String name){
		/*
		 * ???
		 */
		initialClientPlayer(name);
		initialServerOpponent("");
		initialPoint(250,250);
		setClientPlayer(name);
		t = new Thread(gl = new GameLoopClient(storePanel, clientPlayer, point, this));
		t.start();
	}
	
	public synchronized void stopGame(){
		/*
	 n	 * Stopt das Spiel
		 */
		GameLoopAbstract.setIsWin(true);
	}
	
	public void paintSettingPanel(){
		/*
		 * Malt das OptionsPanel neu
		 */
		settingPanel.repaint();
	}
	
	public void align(){
		/*
		 * Setzt das Fenster zurück.
		 * Malt es neu, damit keine Fehler auftreten.
		 */
		pack();
		setLocationRelativeTo(null);
		storePanel.repaint();
	}
	
	// - Initial - //
	public void initialSinglePlayer(String name) {
		singlePlayer = new Player(componentSize, name);
		singlePlayer.setPortalOn(isPortal);
	}
	
	public void initialServerPlayer(String name){
		serverPlayer = new Player(componentSize, name);
		serverPlayer.setPortalOn(isPortal);
	}
	
	public void initialClientPlayer(String name){
		clientPlayer = new Player(componentSize, name, 240, 240, Direction.LEFT);
		clientPlayer.setPortalOn(isPortal);
	}
	
	public void initialServerOpponent(String name){
		serverPlayer = new Player(componentSize, name);
		serverPlayer.setPortalOn(isPortal);
	}
	
	public void initialClientOpponent(String name){
		clientPlayer = new Player(componentSize, name, 240, 240, Direction.LEFT);
		clientPlayer.setPortalOn(isPortal);
	}
	
	public void initialKI(int difficult) {
		ki = new KI(componentSize, difficult, 240, 240, Direction.LEFT);
		ki.setPortalOn(isPortal);
	}
	
	public void initialPoint(){
		point = new NormalPoint(((int)(Math.random()*((GamePanel)storePanel).getGridSize()))*componentSize,((int)(Math.random()*((GamePanel)storePanel).getGridSize()))*componentSize);
	}
	
	public void initialPoint(int x, int y) {
		point = new NormalPoint(x, y);
	}
	
	public static void initialShoot(int x, int y, String direction, SnakeComponent sc, int componentSize) {
		singlePlayerS = new Shoot(x, y, direction, sc, componentSize, gameSize*componentSize, gameSize*componentSize);
	}
	
	// - create Frame - //
	public void addSubmenu(JMenuBar jmb){
		JMenu JMOption = new JMenu("Optionen");
		jmb.add(JMOption);
		//-----
		JMenuItem JMIClose = new JMenuItem("Beenden");
		JMIClose.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		JMOption.add(JMIClose);
		//-----
		JMenuItem JMIBack = new JMenuItem("Zurück");
		JMIBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				stopGame();
				getContentPane().removeAll();
				storePanel = new StartPanel(300,100);
				add(storePanel);
				align();
			}
		});
		JMOption.add(JMIBack);
		//-----
	}

	/// - Getter & Setter - ///
	public String getTitle(){
		return title;
	}
	
	public void setTitle(String title){
		this.title = title;
	}
	
	public Point getPoint(){
		return point;
	}
	
	public void setPoint(Point point){
		this.point = point;
	}
	
	public int getGameSize(){
		return gameSize;
	}
	
	public int getComponentSize(){
		return componentSize;
	}
	
	// - Panel - // 
	public void setStorePanel(SPanel storePanel){
		this.storePanel = storePanel;
	}
	
	public SPanel getStorePanel(){
		return storePanel;
	}

	public void setSettingPanel(SPanel settingPanel){
		this.settingPanel = settingPanel;
	}
	
	public SPanel getSettingPanel(){
		return settingPanel;
	}
	
	// - Settings - // 
	public void setGameSize(int gameSize){
		GameFrame.gameSize = gameSize;
	}

	public void setPortalOn(boolean portal){
		isPortal = portal;
	}

	public boolean getPortalOn(){
		return isPortal;
	}
	
	// - Player - // 
	public static Player getSinglePlayer() {
		return singlePlayer;
	}
	
	public Player getServerPlayer(){
		return serverPlayer;
	}
	
	public Player getClientPlayer(){
		return clientPlayer;
	}

	public int getPortAdd(){
		return portAdd;
	}
	
	public void setPortAdd(int portAdd){
		this.portAdd = portAdd;
	}
	
	public String getIpAdd(){
		return ipAdd;
	}
	
	public void setIpAdd(String ipAdd){
		this.ipAdd = ipAdd;
	}
	
	public void setClientPlayer(String name){
		clientPlayer.setName(name);
	}

	public Shoot getShoot() {
		return singlePlayerS;
	}
	
}
