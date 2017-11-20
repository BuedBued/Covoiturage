package be.acq.metier;
import be.acq.dao.*;
import be.acq.pojo.*;
import be.acq.clavier.*;
public class M_Personne {
	private Personne p;
	public M_Personne() {}
	public Personne getPersonne() {return this.p;}
	public void setPersonne(Personne p) {this.p = p;}
	
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
				M_Membre m = new M_Membre();
				m.setMembre((Membre)utilisateur);
			}
			else if (utilisateur.getClass().equals(Responsable.class)) {
				M_Responsable r = new M_Responsable();
				r.setResponsable((Responsable)utilisateur);
			}
			else if (utilisateur.getClass().equals(Tresorier.class)) {
				M_Tresorier t = new M_Tresorier();
				t.setTresorier((Tresorier)utilisateur);
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
			DAO_Membre dao_m = new DAO_Membre(CovoiturageCon.getInstance());
			/*M_Membre m = new M_Membre();
			m.setMembre()*/
		}
	}
	public void inscriptionMembre() {
		
	}
}
