package fr.univavignon.courbes.sounds;

import  java.io.*;
import javax.sound.sampled.*;
 
/**
 * @author Marie Vergriete
 */
public class Son extends Thread implements Sonbis{
     
    /** son **/
    AudioInputStream audioInputStream = null;
    /**buffer**/
    SourceDataLine line;
    /** chemin du fichier musique**/
    String cheminMusique;
     
    @Override
    public void ChoixMusique(int num)
	{
		if(num == 1)
		{
			this.cheminMusique = "res/sounds/apparait.wav";
		}
		else if(num == 2)
		{
			this.cheminMusique = "res/sounds/bouton.wav";
		}
		else if(num == 3)
		{
			this.cheminMusique = "res/sounds/crash.wav";
		}
		else if(num == 4)
		{
			this.cheminMusique = "res/sounds/fin.wav";
		}
		else if(num == 5)
		{
			this.cheminMusique = "res/sounds/fond.wav";
		}
		else if(num == 6)
		{
			this.cheminMusique = "res/sounds/pris.wav";
		}
		start();
	}
	
    @Override
    public void run(){
        File fichier = new File(cheminMusique);
        try {
        AudioFileFormat format = AudioSystem.getAudioFileFormat(fichier);
        } catch (UnsupportedAudioFileException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
         
        try {
            audioInputStream = AudioSystem.getAudioInputStream(fichier);
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
         
         AudioFormat audioFormat = audioInputStream.getFormat();
         DataLine.Info info = new DataLine.Info(SourceDataLine.class,audioFormat);
          
         try {
             line = (SourceDataLine) AudioSystem.getLine(info);
                        
             } catch (LineUnavailableException e) {
               e.printStackTrace();
               return;
             }
          
        try {
                line.open(audioFormat);
        } catch (LineUnavailableException e) {
                    e.printStackTrace();
                    return;
        }
        line.start();
        try {
            byte bytes[] = new byte[1024];
            int bytesRead=0;
            while ((bytesRead = audioInputStream.read(bytes, 0, bytes.length)) != -1) {
                 line.write(bytes, 0, bytesRead);
                }
        } catch (IOException io) {
            io.printStackTrace();
            return;
        }
    }
}