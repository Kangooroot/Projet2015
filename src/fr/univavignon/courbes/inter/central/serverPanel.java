package fr.univavignon.courbes.inter.central;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
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
	private final int parity;
	private final int players;
	private final int maxPlayers;
	private final JLabel nameLabel;
	private final JLabel capacityLabel;
	
	private final String serverIP;
	private final String serverPort;
	
	serverPanel(MainWindow mainWindow, int i, String servers[][])
	{
		this.mainWindow = mainWindow;
		parity = i;
		players = Integer.parseInt(servers[i][1]);
		maxPlayers =  Integer.parseInt(servers[i][2]);
		serverIP = servers[i][3];
		serverPort = servers[i][4];
		
		setPreferredSize(new Dimension(mainWindow.getPreferredSize().width, 30));
		setMaximumSize(new Dimension(mainWindow.getPreferredSize().width, 30));
		setLayout(new BorderLayout());
		if(i%2 == 1) setBackground(new Color(175,207,227));
		else setBackground(new Color(105,173,216));
		
		JPanel namePanel = new JPanel();
		namePanel.setOpaque(false);
		nameLabel = new JLabel(servers[i][0]);
		nameLabel.setFont(new Font("Liberation sans",Font.BOLD,15));
		nameLabel.setForeground(new Color(0,48,103));
		nameLabel.setBorder(new EmptyBorder(0,30,0,0));
		namePanel.add(nameLabel);
		
		JPanel capacityPanel = new JPanel();
		capacityLabel = new JLabel(servers[i][1]+" / "+servers[i][2]);
		capacityLabel.setFont(new Font("Liberation sans",Font.BOLD,15));
		capacityLabel.setForeground(new Color(0,77,126));
		capacityPanel.setOpaque(false);
		capacityLabel.setBorder(new EmptyBorder(0,0,0,30));
		capacityPanel.add(capacityLabel);
		
		add(namePanel, BorderLayout.WEST);
		add(capacityPanel, BorderLayout.EAST);
		//serverPanel.add(server);
		
		addMouseListener(new MouseListener() {
			@Override
	        public void mouseClicked(MouseEvent e) {
				nextStep();
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
		if(players >= maxPlayers) {
		JOptionPane.showMessageDialog(mainWindow, 
			"<html>Le maximum de joueurs a déja été atteint pour ce serveur" +
			"<br/><center><b>"+players+" / "+maxPlayers+"</b></center></html>");
		}
		else if(0 < players && players < maxPlayers) {
			mainWindow.displayPanel(PanelName.CLIENT_GAME_PLAYER_SELECTION);
		}
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
	private boolean connect()
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
