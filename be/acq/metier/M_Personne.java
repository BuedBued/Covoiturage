package be.acq.metier;
import be.acq.dao.*;
import be.acq.pojo.*;
import be.acq.clavier.*;
public class M_Personne {
	private Personne p;
	public M_Personne() {}
	
	public void connexion() {
		Object utilisateur;
		String p_nom, p_prenom, p_mdp;
		System.out.print("Nom :");
		p_nom = Clavier.lireString();
		System.out.println();
		System.out.print("Prenom :");
		p_prenom = Clavier.lireString();
		System.out.println();
		System.out.print("MDP :");
		p_mdp = Clavier.lireString();
		System.out.println();
		DAO_Personne dao_p = new DAO_Personne(CovoiturageCon.getInstance());
		utilisateur= dao_p.selectFromConnection(p_nom, p_prenom, p_mdp);
		if(utilisateur!=null) {
			if(utilisateur.getClass().equals(Membre.class)) {
				
			}
			else if (utilisateur.getClass().equals(Responsable.class)) {
				
			}
			else if (utilisateur.getClass().equals(Tresorier.class)) {
				
			}
			else {
				p = (Personne)utilisateur;
				fonctionnalitesPersonne();
			}
				
		}
		else {
			System.out.println("Erreur de connexion !");
		}
	}
	
	public void fonctionnalitesPersonne() {
		int choix;
		System.out.println("Que voulez-vous faire?");
		System.out.println("1. Devenir Membre");
		System.out.println("2. Quitter");
		do {
			System.out.print("Votre choix :");
			choix = Clavier.lireInt();
			if(choix !=1 && choix!=2) {
				System.out.println("Erreur encodage");
			}
		}
		while(choix !=1 && choix!=2);
		if(choix ==1) {
			
		}
	}
	public void inscriptionMembre() {
		
	}
}
