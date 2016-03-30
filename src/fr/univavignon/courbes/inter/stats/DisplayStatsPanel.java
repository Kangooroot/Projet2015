package fr.univavignon.courbes.inter.stats;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import fr.univavignon.courbes.common.Round;
import fr.univavignon.courbes.inter.simpleimpl.AbstractConfigurationPanel;
import fr.univavignon.courbes.inter.simpleimpl.MainWindow;
import fr.univavignon.courbes.inter.simpleimpl.MainWindow.PanelName;

public class DisplayStatsPanel extends AbstractConfigurationPanel implements ActionListener
{
	/** Numéro de série */
	private static final long serialVersionUID = 2247L;
	
	private static final String TITLE = "Statistiques des joueurs";
	
	/**
	 * Crée et initialise le panel permettant de sélectionner
	 * les participants locaux à une partie.
	 * 
	 * @param mainWindow
	 * 		Fenêtre contenant ce panel.
	 * @param title
	 * 		Titre du panel.
	 */
	public DisplayStatsPanel(MainWindow mainWindow)
	{	
		super(mainWindow, TITLE);
		nextButton.setVisible(false); 
	}
	
		
	@Override
	protected void initContent()
	{	
		
	}
		
	@Override
	protected void previousStep()
	{
		mainWindow.clientPlayer = null;
		mainWindow.displayPanel(PanelName.MAIN_MENU);
	}
	
	@Override
	protected void nextStep()
	{
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{	super.actionPerformed(e);
		
		if(e.getSource()==backButton)
			previousStep();
	}
}