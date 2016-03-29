package fr.univavignon.courbes.inter.central;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import fr.univavignon.courbes.inter.simpleimpl.MainWindow;

public class PasswordArea extends JTextArea implements KeyListener, FocusListener {

	private final String password;
	private final serverPanel s; 
	private final MainWindow mainWindow;
    public PasswordArea(MainWindow mainWindow, String password, serverPanel s) {
        addKeyListener(this);
        this.s = s;
        this.password = password;
        this.mainWindow = mainWindow;
		setFont(new Font("Liberation sans",Font.BOLD,15));
		setForeground(new Color(0,124,199));
		setPreferredSize(new Dimension(400, 28));
		setMaximumSize(new Dimension(400, 28));
		setVisible(false);
    }
    @Override
    public void keyTyped(KeyEvent e) {
    }
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
        	if(this.getText().equals(password))
        	{
        		s.connect();
        	}
        	else {
        		JOptionPane.showMessageDialog(mainWindow, 
    				"<html><span color='red'>Le mot de passe entré est invalide</span></html>");
        	}
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {
    }
    
    @Override
    public void focusGained(FocusEvent e) {}
    
    @Override
    public void focusLost(FocusEvent e) {
    	System.out.println("HELLO");
    	if(this.getText().equals(""))
    	{
    		this.setVisible(false);
    	}
    }
}

