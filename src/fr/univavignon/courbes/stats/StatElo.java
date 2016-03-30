/*package fr.univavignon.courbes.stats;

/**
 * @author Valentin Girardon
 */ /*
public class StatElo 
{
	
	// anciens classement Elo des joueurs
	int ancienEloA = 0;
	int ancienEloB = 0;
	int ancienEloC = 0;
	int ancienEloD = 0;
	int ancienEloE = 0;
	int ancienEloF = 0;
	
	//scores
	double scoreTheorique = 0;
	float scoreReel = 0;
	
	// nouveaux classement elo des joueurs
	int newEloA = 0;
	int newEloB = 0;
	int newEloC = 0;
	int newEloD = 0;
	int newEloE = 0;
	int newEloF = 0;
	
	//coefficient k
	int k = 0;
	
	//moyenne des elo des joueurs
	int moyenneElo = 0;
	
	System.out.println("Calculateur elo \n");
	
	//demande l'entree de l'elo du joueur A
	System.out.println("Ancien elo de A\n");
	ancienEloA = 1200;
	
	//demande l'entree de l'elo du joueur B
	System.out.println("Ancien elo de B\n");
	ancienEloB = 1200;
	
	//demande l'entree de l'elo du joueur C
	System.out.println("Ancien elo de C\n");
	ancienEloC = 1200;
		
	//demande l'entree de l'elo du joueur D
	System.out.println("Ancien elo de D\n");
	ancienEloD = 1200;
	
	//demande l'entree de l'elo du joueur E
	System.out.println("Ancien elo de E\n");
	ancienEloE = 1200;
	
	//demande l'entree de l'elo du joueur F
	System.out.println("Ancien elo de F\n");
	ancienEloF = 1200;
	
	//demande l'entree du score A vs les autres joueurs
	System.out.println("Score : victoire de A sur B -> 1 | victoire de B sur A -> 0 | parti nulle > 0.5");
	scoreReel = 1;
	
	System.out.println("Score : victoire de A sur C -> 1 | victoire de C sur A -> 0 | parti nulle > 0.5");
	scoreReel = 1;
	
	System.out.println("Score : victoire de A sur D -> 1 | victoire de D sur A -> 0 | parti nulle > 0.5");
	scoreReel = 0;
	
	System.out.println("Score : victoire de A sur E -> 1 | victoire de E sur A -> 0 | parti nulle > 0.5");
	scoreReel = 0;
	
	System.out.println("Score : victoire de A sur F -> 1 | victoire de F sur A -> 0 | parti nulle > 0.5");
	scoreReel = 0.5;
	
	//demande l'entree du score B vs les autres joueurs
	System.out.println("Score : victoire de B sur A -> 1 | victoire de A sur B -> 0 | parti nulle > 0.5");
	scoreReel = 0;
	
	System.out.println("Score : victoire de B sur C -> 1 | victoire de C sur B -> 0 | parti nulle > 0.5");
	scoreReel = 1;
	
	System.out.println("Score : victoire de B sur D -> 1 | victoire de D sur B -> 0 | parti nulle > 0.5");
	scoreReel = 1;
	
	System.out.println("Score : victoire de B sur E -> 1 | victoire de E sur B -> 0 | parti nulle > 0.5");
	scoreReel = 1;
	
	System.out.println("Score : victoire de B sur F -> 1 | victoire de F sur B -> 0 | parti nulle > 0.5");
	scoreReel = 0;
	
	//demande l'entree du score C vs les autres joueurs
	System.out.println("Score : victoire de C sur A -> 1 | victoire de A sur C -> 0 | parti nulle > 0.5");
	scoreReel = 0;
	
	System.out.println("Score : victoire de C sur B -> 1 | victoire de B sur C -> 0 | parti nulle > 0.5");
	scoreReel = 0;
	
	System.out.println("Score : victoire de C sur D -> 1 | victoire de D sur C -> 0 | parti nulle > 0.5");
	scoreReel = 1;
	
	System.out.println("Score : victoire de C sur E -> 1 | victoire de E sur C -> 0 | parti nulle > 0.5");
	scoreReel = 0.5;
	
	System.out.println("Score : victoire de C sur F -> 1 | victoire de F sur C -> 0 | parti nulle > 0.5");
	scoreReel = 0.5;
	
	//demande l'entree du score D vs les autres joueurs
	System.out.println("Score : victoire de D sur A -> 1 | victoire de A sur D -> 0 | parti nulle > 0.5");
	scoreReel = 1;
	
	System.out.println("Score : victoire de D sur B -> 1 | victoire de B sur D -> 0 | parti nulle > 0.5");
	scoreReel = 0;
	
	System.out.println("Score : victoire de D sur C -> 1 | victoire de C sur D -> 0 | parti nulle > 0.5");
	scoreReel = 0;
	
	System.out.println("Score : victoire de D sur E -> 1 | victoire de E sur D -> 0 | parti nulle > 0.5");
	scoreReel = 1;
	
	System.out.println("Score : victoire de D sur F -> 1 | victoire de F sur D -> 0 | parti nulle > 0.5");
	scoreReel = 0.5;
	
	//demande l'entree du score E vs les autres joueurs
	System.out.println("Score : victoire de E sur A -> 1 | victoire de A sur E -> 0 | parti nulle > 0.5");
	scoreReel = 0;
		
	System.out.println("Score : victoire de E sur B -> 1 | victoire de B sur E -> 0 | parti nulle > 0.5");
	scoreReel = 0;
		
	System.out.println("Score : victoire de E sur C -> 1 | victoire de C sur E -> 0 | parti nulle > 0.5");
	scoreReel = 0.5;
		
	System.out.println("Score : victoire de E sur D -> 1 | victoire de D sur E -> 0 | parti nulle > 0.5");
	scoreReel = 0;
		
	System.out.println("Score : victoire de E sur F -> 1 | victoire de F sur E -> 0 | parti nulle > 0.5");
	scoreReel = 1;
	
	//demande l'entree du score F vs les autres joueurs
	System.out.println("Score : victoire de F sur A -> 1 | victoire de A sur F -> 0 | parti nulle > 0.5");
	scoreReel = 0.5;
			
	System.out.println("Score : victoire de F sur B -> 1 | victoire de B sur F -> 0 | parti nulle > 0.5");
	scoreReel = 1;
			
	System.out.println("Score : victoire de F sur C -> 1 | victoire de C sur F -> 0 | parti nulle > 0.5");
	scoreReel = 0.5;
			
	System.out.println("Score : victoire de F sur D -> 1 | victoire de D sur F -> 0 | parti nulle > 0.5");
	scoreReel = 0.5;
			
	System.out.println("Score : victoire de F sur E -> 1 | victoire de E sur F -> 0 | parti nulle > 0.5");
	scoreReel = 0;
	
	//calcule la moyenne des Elo
	moyenneElo = (ancienEloA + ancienEloB + ancienEloC + ancienEloD + ancienEloE + ancienEloF)/2;
	
	//calcule de k(coefficient)
	if(moyenneElo < 2000)
	{
		k = 30;
	}
	
	else if(moyenneElo > 2400)
	{
		k = 10;
	}
	else k = 130 - (moyenneElo / 20);
	
	//calcule le score théorique
	scoreTheorique = 1/(1 + Math.pow(10,((double)(ancienEloA-ancienEloB-ancienEloC-ancienEloD-ancienEloE-ancienEloF))/400));
	
	//calcule les nouveaux elo des joueurs
	//(1-scoreReel) - (1-scoreTheorique)
	// = scoreTheorique - scoreReel
	newEloA = (int)(ancienEloA + k * (scoreReel - scoreTheorique));
	newEloB = (int)(ancienEloB + k * (scoreReel - scoreTheorique));
	newEloC = (int)(ancienEloC + k * (scoreReel - scoreTheorique));
	newEloD = (int)(ancienEloD + k * (scoreReel - scoreTheorique));
	newEloE = (int)(ancienEloE + k * (scoreReel - scoreTheorique));
	newEloF = (int)(ancienEloF + k * (scoreReel - scoreTheorique));
	
	//affiche le résultat
	System.out.println("nouvel elo de A = " + newEloA + "\n");
	System.out.println("nouvel elo de B = " + newEloB + "\n");
	System.out.println("nouvel elo de C = " + newEloC + "\n");
	System.out.println("nouvel elo de D = " + newEloD + "\n");
	System.out.println("nouvel elo de E = " + newEloE + "\n");
	System.out.println("nouvel elo de F = " + newEloF + "\n");
	
}*/