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
		System.out.println("******************************");
		System.out.println("Connexion");
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
				m.fonctionnalitesMembre();
			}
			else if (utilisateur.getClass().equals(Responsable.class)) {
				M_Responsable r = new M_Responsable();
				r.setResponsable((Responsable)utilisateur);
				r.fonctionnalitesResponsable();
			}
			else if (utilisateur.getClass().equals(Tresorier.class)) {
				M_Tresorier t = new M_Tresorier();
				t.setTresorier((Tresorier)utilisateur);
				t.fonctionnalitesTresorier();
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
	
	public void inscription() {
		DAO_Personne dao_p = new DAO_Personne(CovoiturageCon.getInstance());
		Personne tmp_p = encodage();
		if(dao_p.create(tmp_p)) { //Si la personne a été créée dans la base de donnée
			this.p = tmp_p;
			fonctionnalitesPersonne();
		}
		//Sinon, il y aura un message d'erreur affiché venant de la méthode create(Personne o) de la classe DAO_Personne 
	}
	
	public Personne encodage() {
		String p_nom, p_prenom, p_date, p_tel, p_mail, p_mdp;
		System.out.println("******************************");
		System.out.println("Inscription Personne");
		System.out.print("Nom : ");
		p_nom = Clavier.lireString();
		System.out.print("Prenom : ");
		p_prenom = Clavier.lireString();
		System.out.print("Date de naissance : ");
		p_date = Clavier.lireString();
		System.out.print("Numero de telephone : ");
		p_tel = Clavier.lireString();
		System.out.print("Adresse mail : ");
		p_mail = Clavier.lireString();
		System.out.print("Mot de passe : ");
		p_mdp = Clavier.lireString();
		Personne tp = new Personne(p_nom, p_prenom, p_date, p_tel, p_mail);
		tp.setMDP(p_mdp);
		return tp;
	}
	
	public void fonctionnalitesPersonne() {
		int choix;
		System.out.println("********************************");
		System.out.println("Bienvenue " +p.getNom() +" " +p.getPrenom());
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
			devenirMembre();
		}
	}
	public void devenirMembre() {
		M_Membre m = new M_Membre();
		m.setMembre((Membre)this.p);
		m.inscriptionMembre();
	}
}
