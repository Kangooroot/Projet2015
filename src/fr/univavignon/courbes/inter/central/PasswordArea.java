package fr.univavignon.courbes.inter.central;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;

import fr.univavignon.courbes.inter.simpleimpl.MainWindow;

/**
 * @author Rémi Drissi
 */
public class PasswordArea extends JTextArea implements KeyListener, FocusListener {

	private final String password;
	private final serverPanel s; 
	private final MainWindow mainWindow;
	private static PasswordArea focused = null;
	private final String placeholder = "Mot de passe du serveur";

	/** Crée un champ de texte de type password
	 * @param mainWindow
	 * 	fenetre principale
	 * @param password 
	 * 	mot de passe du serveur
	 * @param s 
	 * 	contient ce champ de texte
	 **/
    public PasswordArea(MainWindow mainWindow, String password, serverPanel s) {
    	setText(placeholder);
    	setForeground(new Color(200,200,200));
    	this.getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "none");
    	addFocusListener(this);
        addKeyListener(this);
        this.s = s;
        this.password = password;
        this.mainWindow = mainWindow;
		setFont(new Font("Liberation sans",Font.BOLD,15));
		setPreferredSize(new Dimension(400, 28));
		setMaximumSize(new Dimension(400, 28));
		setVisible(false);
    }
    
    /** Get du champ selectionné
	 * @return
	 * 	retourne le cham pde texte actuellement selectionné
	 **/
	public static PasswordArea getFocused(){return focused;}
	/** Set le champ selectionné
	 * @param
	 * 	passwordarea a selectionner
	 **/
	public static void setFocused(PasswordArea p){focused = p;}
	
    @Override
    public void keyTyped(KeyEvent e) {
    }
    
    /** detecte l'appui sur une touche du clavier 
     * 	
     * Valide la conexion au serveur lors de l'appui sur la touche entrée
     * **/
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
        	if(this.getText().equals(password))
        	{
        		s.connect();
        	}
        	else {
        		if(this.getText().equals(""))
        		{
        			JOptionPane.showMessageDialog(mainWindow, 
            				"<html><span color='black'>Vous n'avez pas spécifié de mot de passe !</span></html>");
        		}
        		else {
        			JOptionPane.showMessageDialog(mainWindow, 
            				"<html><span color='red'>Le mot de passe entré est invalide</span></html>");
        		}
        	}
        }
    }
    
    @Override
    public void keyReleased(KeyEvent e) {
    }
    
    @Override
    public void focusGained(FocusEvent e) {
    	if(getText().equals(placeholder))
    		setForeground(new Color(0,124,199));
    		setText("");
    }
    
    @Override
    public void focusLost(FocusEvent e) {
    	setForeground(new Color(200,200,200));
		setText(placeholder);
    	setVisible(false);
    }
}

