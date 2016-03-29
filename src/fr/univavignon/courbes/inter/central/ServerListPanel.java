package fr.univavignon.courbes.inter.central;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;


import javax.swing.*;
import javax.swing.border.EmptyBorder;

import fr.univavignon.courbes.common.Player;
import fr.univavignon.courbes.common.Profile;
import fr.univavignon.courbes.inter.simpleimpl.AbstractConfigurationPanel;
import fr.univavignon.courbes.inter.simpleimpl.MainWindow;
import fr.univavignon.courbes.inter.simpleimpl.MainWindow.PanelName;
import fr.univavignon.courbes.inter.simpleimpl.local.AbstractLocalPlayerSelectionPanel;
import fr.univavignon.courbes.inter.simpleimpl.local.LocalPlayerConfigPanel;
import fr.univavignon.courbes.network.central.HttpRequests;

/**
 * Panel affichant la liste des serveurs disponibles créés par d'autres utilisateurs
 * 
 * @author	uapv1400768 - DRISSI Rémi
 */
public class ServerListPanel extends AbstractConfigurationPanel implements ItemListener
{
	/** Numéro de série */
	private static final long serialVersionUID = 100L;
	/** Title du panel */
	private static final String TITLE = "Liste des serveurs disponibles";
	/** Liste des serveurs */
	private static String[][] servers;
	
	
	
	/**
	 * Crée et initialise le panel permettant de sélectionner
	 * les participants à une partie locale.
	 * 
	 * @param mainWindow
	 * 		Fenêtre contenant ce panel.
	 */
	public ServerListPanel (MainWindow mainWindow)
	{	super(mainWindow,TITLE);
		nextButton.setVisible(false);
	}
	
	@Override
	protected void previousStep()
	{
		mainWindow.displayPanel(PanelName.CLIENT_GAME_PLAYER_SELECTION);
	}
	
	@Override
	protected void nextStep()
	{
		
	}

	@Override
	protected void initContent()
	{
		Dimension winDim = mainWindow.getPreferredSize();
		Dimension dim;
		
		JPanel panel = new JPanel();
		BoxLayout layout = new BoxLayout(panel, BoxLayout.Y_AXIS);
		panel.setLayout(layout);
		dim = new Dimension(winDim.width,winDim.height-120);
		panel.setPreferredSize(dim);
		panel.setMaximumSize(dim);
		panel.setMinimumSize(dim);
		
		String get = "server1,1,3|server2,5,5|server3,1,3|server4,1,3";
		String[] first_parse;
		first_parse = get.split("\\|", -1);
		servers = new String[first_parse.length][];
		for(int i = 0; i < first_parse.length; i++) {
			servers[i] = first_parse[i].split(",");
		}
		
		JPanel serverPanel = new JPanel();
		JScrollPane scrollPane = new JScrollPane(serverPanel);
		scrollPane.setBackground(new Color(0,63,122));
		scrollPane.setMinimumSize(new Dimension(winDim.width, winDim.height-160));
		scrollPane.setMaximumSize(new Dimension(winDim.width, winDim.height-160));
		BoxLayout box = new BoxLayout(serverPanel, BoxLayout.Y_AXIS);
		serverPanel.setLayout(box);
		serverPanel.setPreferredSize(new Dimension(winDim.width-35, servers.length*30));
		serverPanel.setMaximumSize(new Dimension(winDim.width-35, servers.length*30));
		
		JPanel top = new JPanel();
		top.setLayout(new BorderLayout());
		top.setMinimumSize(new Dimension(winDim.width, 35));
		top.setMaximumSize(new Dimension(winDim.width, 35));
		JLabel topLabelName = new JLabel("Nom du serveur");
		topLabelName.setBorder(new EmptyBorder(0,30,0,0));
		topLabelName.setFont(new Font("Liberation sans",Font.BOLD,17));
		topLabelName.setForeground(Color.WHITE);
		topLabelName.setOpaque(false);
		JLabel topLabelCapa = new JLabel("Capacité");
		topLabelCapa.setBorder(new EmptyBorder(0,0,0,30));
		topLabelCapa.setFont(new Font("Liberation sans",Font.BOLD,17));
		topLabelCapa.setForeground(Color.WHITE);
		topLabelCapa.setOpaque(false);
		top.add(topLabelName, BorderLayout.WEST);
		top.add(topLabelCapa, BorderLayout.EAST);
		top.setBackground(new Color(0,63,122));
		panel.add(top);
		
		/* On affiche les serveurs disponibles*/
		if(servers.length > 0)
		{
			for(int i = 0; i < servers.length; i++)
			{
				try{Thread.sleep(100);}
				catch(Exception e){System.out.println(e);}
				serverPanel sp = new serverPanel(mainWindow, i, servers);
				serverPanel.add(sp);
			}
		}
		else {
			JPanel noServerPanel = new JPanel();
			JLabel noServerLabel = new JLabel("Il n'y a pas de serveurs actuellement");
			noServerLabel.setForeground(Color.RED);
			noServerPanel.add(noServerLabel);
		}
		
		
		panel.add(scrollPane);
		add(panel);
	}
	
	private JPanel initServerPanel(String[][] servers)
	{
		JPanel serverPanel = new JPanel();
		
		return serverPanel;
	}
	
	/**
	 * Initialise chaque bouton de la même façon.
	 * 
	 * @param text
	 * 		Texte à inclure dans le bouton.
	 * @return
	 * 		Bouton convenablement configuré. 
	 */
	private JButton initButton(String text)
	{	JButton result = new JButton(text);
	
		Font font = getFont();
		font = new Font(font.getName(),Font.PLAIN,25);
		result.setFont(font);
		
		Dimension dim = new Dimension(400,50);
		result.setMaximumSize(dim);
		result.setMinimumSize(dim);
		result.setPreferredSize(dim);
		result.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		result.addActionListener(this);
		
		return result;
	}
	
	
	@Override
	public void itemStateChanged(ItemEvent e)
	{
		
	}
}
