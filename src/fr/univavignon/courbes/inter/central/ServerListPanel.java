package fr.univavignon.courbes.inter.central;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;

import javax.swing.*;

import fr.univavignon.courbes.common.Player;
import fr.univavignon.courbes.inter.simpleimpl.AbstractConfigurationPanel;
import fr.univavignon.courbes.inter.simpleimpl.MainWindow;
import fr.univavignon.courbes.inter.simpleimpl.MainWindow.PanelName;
import fr.univavignon.courbes.inter.simpleimpl.local.AbstractLocalPlayerSelectionPanel;
import fr.univavignon.courbes.inter.simpleimpl.local.LocalPlayerConfigPanel;
import fr.univavignon.courbes.network.central.HttpRequests;

public class ServerListPanel extends AbstractConfigurationPanel implements ItemListener
{
	/** Numéro de série */
	private static final long serialVersionUID = 100L;
	/** Title du panel */
	private static final String TITLE = "Liste des serveurs disponibles";
	/** Liste des serveurs */
	private static String[] servers;
	
	
	
	/**
	 * Crée et initialise le panel permettant de sélectionner
	 * les participants à une partie locale.
	 * 
	 * @param mainWindow
	 * 		Fenêtre contenant ce panel.
	 */
	public ServerListPanel (MainWindow mainWindow)
	{	super(mainWindow,TITLE);
		/*try {
			String get = HttpRequests.get("connection.php?req=server_nbr");
			servers = get.split("\\|", -1);
		}
		catch(IOException e) {
			System.out.println(e);
			
		}*/
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
		int height = 30;
		
		JPanel panel = new JPanel();
		BoxLayout layout = new BoxLayout(panel, BoxLayout.LINE_AXIS);
		panel.setLayout(layout);
		dim = new Dimension((int)(winDim.width),height);
		panel.setPreferredSize(dim);
		panel.setMaximumSize(dim);
		panel.setMinimumSize(dim);
		
		String get = "server1|server1|server1|server1|server1";
		servers = get.split("\\|", -1);
		
		JPanel serverPanel = new JPanel();
		serverPanel.setLayout(new FlowLayout());
		/*JPanel[] s = new JPanel[servers.length];*/
		if(servers.length > 0)
		{
			for(int i = 0; i < servers.length; i++)
			{
				/*s[i] = new JPanel();
				JLabel lbl = new JLabel(servers[i]);
				s[i].add(lbl);
				serverPanel.add(s[i]);*/
				JPanel j = new JPanel();
				JLabel lbl = new JLabel(servers[i]);
				j.add(lbl);
				serverPanel.add(j);
			}
		}
		else {
			JPanel noServerPanel = new JPanel();
			JLabel noServerLabel = new JLabel("Il n'y a pas de serveurs actuellement");
			noServerLabel.setForeground(Color.RED);
			noServerPanel.add(noServerLabel);
		}
		
		panel.add(serverPanel);
		add(panel);
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
