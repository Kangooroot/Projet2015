package fr.univavignon.courbes.stats;

import java.io.File;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Valentin Girardon
 */
public class stockStats {

	static int ELO = 0;
	static String joueur = "Default";
	static int partiesJouees = 0;
	static int partiesGagnees = 0;
	static int roundsJouees = 0;
	static int roundsGagnees = 0;
	static int pointsTotal = 0;
	
	/**
	 * Constructeur 3 arguments.
	 * @param j Nom Joueur.
	 * @param stat Nom stat incrémenter.
	 * @param nbr Nombre incrémenter.
	 */
	public stockStats(String j, String stat, int nbr){
		joueur = j;
		majFichier(joueur);
		if(stat == "partiesJouees")	partiesJouees += nbr;
		if(stat == "partiesgagnees")partiesGagnees += nbr;
		if(stat == "roundsJouees")	roundsJouees += nbr;
		if(stat == "roundsGagnees")	roundsGagnees += nbr;
		if(stat == "pointsTotal")	pointsTotal += nbr;
	}
	
	/**
	 * Ce qui va gérer l'écriture fichier.
	 * @param joueur Nom Joueur.
	 * @param stat Nom stat incrémenter.
	 * @param nbr Nombre incrémenter. 
	 */
	public static void eStats(String joueur, String stat, int nbr)
	{
		stockStats statsProfile = new stockStats(joueur,stat,nbr);
		
		try
		{
			
			FileWriter A = new FileWriter(joueur+".txt");
			BufferedWriter B = new BufferedWriter(A);
			String NewE,NewE2,NewE3,NewE4,NewE5,NewE6;
			
			NewE = Integer.toString(statsProfile.ELO);
			B.write(NewE);
			B.write("\n");
			
			B.write(statsProfile.joueur);
			B.write("\n");
			
			NewE2 = Integer.toString(statsProfile.partiesJouees);
			B.write(NewE2);
			B.write("\n");
			
			NewE3 = Integer.toString(statsProfile.partiesGagnees);
			B.write(NewE3);
			B.write("\n");
			
			NewE4 = Integer.toString(statsProfile.roundsJouees);
			B.write(NewE4);
			B.write("\n");
			
			NewE5 = Integer.toString(statsProfile.roundsGagnees);
			B.write(NewE5);
			B.write("\n");
			
			NewE6 = Integer.toString(statsProfile.pointsTotal);
			B.write(NewE6);
			B.write("\n");
			
			B.flush();
			B.close();
			
		}
		
		catch (Exception e) {System.out.println(e.toString());}
	
	}
	
	/**
	 * conserver fichier texte d'une partie à une autre. 
	 * @param joueur Nom joueur.
	 */
	public static void majFichier(String joueur)
	{
		try
		{
			InputStream A = new FileInputStream(joueur+".txt");
			InputStreamReader B = new InputStreamReader(A);
			BufferedReader C = new BufferedReader(B);
			String ligne;
			int nbrLignes = 7;
			int[] var = new int[nbrLignes];
				
			int i = 0;
			ligne = C.readLine();
			ligne = C.readLine();
			i=2;
				
			while ((ligne = C.readLine()) != null)
			{
				var[i] = Integer.parseInt(ligne.substring(0));
				i++;
			}
			stockStats.ELO = var[0];
			stockStats.joueur = joueur;
			stockStats.partiesJouees = var[2];
			stockStats.partiesGagnees = var[3];
			stockStats.roundsJouees = var[4];
			stockStats.roundsGagnees = var[5];
			stockStats.pointsTotal = var[6];
			
			C.close();
		}
		catch (Exception x) 
		{
			System.out.println(x.toString());
		} 
	}
}