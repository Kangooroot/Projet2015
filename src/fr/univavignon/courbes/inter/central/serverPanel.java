package fr.univavignon.courbes.inter.central;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import fr.univavignon.courbes.inter.ClientConnectionHandler;
import fr.univavignon.courbes.inter.simpleimpl.MainWindow;
import fr.univavignon.courbes.inter.simpleimpl.SettingsManager;
import fr.univavignon.courbes.inter.simpleimpl.MainWindow.PanelName;
import fr.univavignon.courbes.inter.simpleimpl.SettingsManager.NetEngineImpl;
import fr.univavignon.courbes.network.ClientCommunication;
import fr.univavignon.courbes.network.kryonet.ClientCommunicationKryonetImpl;
import fr.univavignon.courbes.network.simpleimpl.client.ClientCommunicationImpl;


/**
 * Panel qui représente une ligne du ServerListPanel
 * Regroupe les informations concernant chaque serveur (nom, capacité, ip)
 * 
 * @author	uapv1400768 - DRISSI Rémi
 */
public class serverPanel extends JPanel implements ItemListener, ClientConnectionHandler
{
	private final MainWindow mainWindow;
	private final String name;
	private final String password;
	private final int parity;
	private final int players;
	private final int maxPlayers;
	private final JLabel nameLabel;
	private final JLabel capacityLabel;
	private final PasswordArea passwordArea;
	
	private final String serverIP;
	private final String serverPort;
	
	serverPanel(MainWindow mainWindow, int i, String servers[][])
	{
		this.mainWindow = mainWindow;
		parity = i;
		name = servers[i][0];
		password = servers[i][1];
		System.out.println(servers[i][1]);
		players = Integer.parseInt(servers[i][2]);
		maxPlayers =  Integer.parseInt(servers[i][3]);
		serverIP = servers[i][4];
		serverPort = servers[i][5];
		
		setPreferredSize(new Dimension(mainWindow.getPreferredSize().width, 30));
		setMaximumSize(new Dimension(mainWindow.getPreferredSize().width, 30));
		setLayout(new BorderLayout());
		if(i%2 == 1) setBackground(new Color(175,207,227));
		else setBackground(new Color(105,173,216));
		
		JPanel namePanel = new JPanel();
		JLabel lock = new JLabel();
		lock.setBorder(new EmptyBorder(0,10,0,0));
		
		namePanel.setOpaque(false);
		nameLabel = new JLabel(name);
		nameLabel.setFont(new Font("Liberation sans",Font.BOLD,15));
		nameLabel.setForeground(new Color(0,48,103));
		nameLabel.setBorder(new EmptyBorder(0,10,0,0));
		if(password.equals("_NO_PWD_")) {nameLabel.setBorder(new EmptyBorder(0,30,0,0));}
		else {lock.setIcon(new ImageIcon("res/images/icon_lock.png"));}
		namePanel.add(lock);
		namePanel.add(nameLabel);
		
		JPanel passwordPanel = new JPanel();
		passwordPanel.setOpaque(false);
		passwordPanel.setLayout(new BoxLayout(passwordPanel, BoxLayout.X_AXIS));
		/*passwordArea = new PasswordArea(password);
		passwordArea.setFont(new Font("Liberation sans",Font.BOLD,15));
		passwordArea.setForeground(new Color(0,124,199));
		passwordArea.setPreferredSize(new Dimension(400, 30));
		passwordArea.setMaximumSize(new Dimension(400, 30));
		passwordArea.setVisible(false);*/
		passwordArea = new PasswordArea(mainWindow, password, this);
		passwordPanel.setBorder(new EmptyBorder(0,30,0,0));
		passwordPanel.add(passwordArea);
		
		
		JPanel capacityPanel = new JPanel();
		capacityLabel = new JLabel(players+" / "+maxPlayers);
		capacityLabel.setFont(new Font("Liberation sans",Font.BOLD,15));
		capacityLabel.setForeground(new Color(0,77,126));
		capacityPanel.setOpaque(false);
		capacityLabel.setBorder(new EmptyBorder(0,0,0,30));
		capacityPanel.add(capacityLabel);
		
		add(namePanel, BorderLayout.WEST);
		add(passwordPanel, BorderLayout.CENTER);
		add(capacityPanel, BorderLayout.EAST);
		//serverPanel.add(server);
		
		addMouseListener(new MouseListener() {
			@Override
	        public void mouseClicked(MouseEvent e) {
				nextStep();
				if(!password.equals("_NO_PWD_") && PasswordArea.getFocused()!=null && passwordArea != PasswordArea.getFocused()) PasswordArea.getFocused().setVisible(false);
				PasswordArea.setFocused(passwordArea);
			}

	        @Override
	        public void mousePressed(MouseEvent e) {}

	        @Override
	        public void mouseReleased(MouseEvent e) {}

	        @Override
	        public void mouseEntered(MouseEvent e) {
	        	if(players < maxPlayers) setBackground(new Color(63,174,82));
	        	else setBackground(new Color(240,101,86));
	        	setCursor(new Cursor(Cursor.HAND_CURSOR));
	        	nameLabel.setForeground(new Color(233,255,234));
	        	capacityLabel.setForeground(new Color(233,255,234));
	        }

	        @Override
	        public void mouseExited(MouseEvent e) {
	        	if(parity%2 == 1) setBackground(new Color(175,207,227));
	    		else setBackground(new Color(105,173,216));
	        	setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
	        	nameLabel.setForeground(new Color(0,77,126));
	        	capacityLabel.setForeground(new Color(0,77,126));
	        }
		});
	}
	
	private final void nextStep()
	{
		if(password.equals("_NO_PWD_")) {connect();}
		else if (passwordArea.isVisible() == false){
			passwordArea.setVisible(true);
		}
		else {passwordArea.setVisible(false);}
	}
	
	@Override
	public void itemStateChanged(ItemEvent e)
	{
		
	}
	
	/**
	 * Tente de se connecter au serveur.
	 * 
	 * @return
	 * 		Indique si on a au moins pu établir une connexion au serveur ({@code true}) ou pas ({@code false}).
	 */
	public boolean connect()
	{	// on initialise le Moteur Réseau
		ClientCommunication clientCom = null;
		NetEngineImpl netEngineImpl = SettingsManager.getNetEngineImpl();
		switch(netEngineImpl)
		{	case KRYONET:
				clientCom = new ClientCommunicationKryonetImpl();
				break;
			case SOCKET:
				clientCom = new ClientCommunicationImpl();
				break;
		}
		
		mainWindow.clientCom = clientCom;
		clientCom.setErrorHandler(mainWindow);
		clientCom.setConnectionHandler(this);
		
		String ipStr = serverIP;
		clientCom.setIp(ipStr);
		SettingsManager.setLastServerIp(ipStr);
		
		String portStr = serverPort;
		int port = Integer.parseInt(portStr);
		clientCom.setPort(port);
		SettingsManager.setLastServerPort(port);
		
		// puis on se connecte
		boolean result = clientCom.launchClient();
		return result;
	}
	
	@Override
	public void gotRefused()
	{	SwingUtilities.invokeLater(new Runnable()
		{	@Override
			public void run()
			{	JOptionPane.showMessageDialog(mainWindow, 
					"<html>Le serveur a rejeté votre candidature, car il ne reste "
					+ "<br/>pas de place dans la partie en cours de configuration.</html>");
			}
	    });
	}
	
	@Override
	public void gotAccepted()
	{	SwingUtilities.invokeLater(new Runnable()
		{	@Override
			public void run()
			{	mainWindow.clientCom.setConnectionHandler(null);
				mainWindow.displayPanel(PanelName.CLIENT_GAME_WAIT);
			}
	    });
	}
}

