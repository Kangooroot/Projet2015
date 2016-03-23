package fr.univavignon.courbes.inter.central;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.*;

import fr.univavignon.courbes.common.Player;
import fr.univavignon.courbes.inter.simpleimpl.MainWindow;
import fr.univavignon.courbes.inter.simpleimpl.MainWindow.PanelName;
import fr.univavignon.courbes.inter.simpleimpl.local.AbstractLocalPlayerSelectionPanel;
import fr.univavignon.courbes.inter.simpleimpl.local.LocalPlayerConfigPanel;

public class ServerListPanel extends AbstractLocalPlayerSelectionPanel implements ItemListener
{
	/** Numéro de série */
	private static final long serialVersionUID = 100L;
	/** Title du panel */
	private static final String TITLE = "Liste des serveurs disponibles";
	/** Nombre de serveurs */
	private static final int SERVER_NBR = 1;
	/** Nombre minimal de joueurs recquis pour la partie */
	private static final int MIN_PLYR_NBR = 1;
	/** Nombre maximal de joueurs autorisé pour la partie */
	private static final int MAX_PLYR_NBR = 1;
	/** Texte associé à la combobox */
	private static final String COMBO_TEXT = "Nombre de joueurs : ";
	/** Title du panel */
	private static final String BOX_LABEL = "Connexion directe : ";
	
	/**
	 * Crée et initialise le panel permettant de sélectionner
	 * les participants à une partie locale.
	 * 
	 * @param mainWindow
	 * 		Fenêtre contenant ce panel.
	 */
	public ServerListPanel (MainWindow mainWindow)
	{	super(mainWindow,TITLE);
	}
	
	@Override
	protected void previousStep()
	{	mainWindow.clientPlayer = null;
		mainWindow.displayPanel(PanelName.MAIN_MENU);
	}
	
	@Override
	protected void nextStep()
	{
		
	}
	
	@Override
	public int getMinPlayerNbr()
	{	return MIN_PLYR_NBR;
	}
	
	@Override
	protected int getMaxPlayerNbr()
	{	return MAX_PLYR_NBR;
	}

	@Override
	protected String getComboText()
	{	return COMBO_TEXT;
	}
	
	@Override
	protected void initContent()
	{	super.initContent();
		
		// on désactive le combo
		playerNbrCombo.setEnabled(false);
		comboLabel.setEnabled(false);
		
		// on sort les couleurs
		for(LocalPlayerConfigPanel lpcp: selectedProfiles)
			lpcp.removeColor();
		
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
		
		JLabel publicLabel = new JLabel(BOX_LABEL);
		panel.add(publicLabel);
		
		
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
