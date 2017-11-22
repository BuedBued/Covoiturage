package be.acq.principal;
import be.acq.metier.*;
import be.acq.clavier.*;

public class UtilisationProgramme {
	public static void start() {
		int choix;
		do {
			System.out.println("***********************************");
			System.out.println("Bienvenue sur l'application");
			System.out.println("***********************************");
			System.out.println("Que voulez-vous faire ?");
			System.out.println("1. Connexion");
			System.out.println("2. Inscription");
			System.out.println("3. Quitter");
			do {
				System.out.print("Votre choix (1,2,3) : ");
				choix = Clavier.lireInt();
				System.out.println();
				if (choix<1 || choix >3)
					System.out.println("Erreur d'encodage");
			}
			while(choix<1 || choix>3);
			M_Personne p = new M_Personne();
			switch(choix) {
			case 1:
				p.connexion();
				break;
			case 2:
				p.inscription();
				break;
			case 3:
				conclusion();
				break;
			}
		}
		while(choix !=3);
	}
	public static void conclusion() {
		System.out.println("Aurevoir");
		System.exit(0);
	}
	
	public static void main(String[] args) {
		UtilisationProgramme.start();
	}
}
