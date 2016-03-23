package fr.univavignon.courbes.inter.central;
import java.awt.Component;
import java.awt.Dimension;
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
	/** Nombre de serveurs */
	private static int SERVER_NB = 0;
	/** Texte associé à la combobox */
	private static final String COMBO_TEXT = "Nombre de joueurs : ";
	
	
	
	/**
	 * Crée et initialise le panel permettant de sélectionner
	 * les participants à une partie locale.
	 * 
	 * @param mainWindow
	 * 		Fenêtre contenant ce panel.
	 */
	public ServerListPanel (MainWindow mainWindow)
	{	super(mainWindow,TITLE);
		try {
			SERVER_NB = Integer.parseInt(HttpRequests.get("connection.php?req=server_nbr"));
		}
		catch(IOException e) {
			System.out.println(e);
		}
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
	{	/*super.initContent();*/
		
		// on désactive le combo
		/*playerNbrCombo.setEnabled(false);
		comboLabel.setEnabled(false);*/
		
		// on sort les couleurs
		/*for(LocalPlayerConfigPanel lpcp: selectedProfiles)
			lpcp.removeColor();*/
		
		// on rajoute la check box
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
