package be.acq.metier;
//import be.acq.dao.*;
import be.acq.pojo.*;
import be.acq.clavier.*;
public class M_Responsable {
	private Responsable r;
	public M_Responsable() {}
	public Responsable getResponsable() {return this.r;}
	public void setResponsable(Responsable r) {this.r = r;}
	
	public void fonctionnalitesResponsable() {
		System.out.println("****************************");
		System.out.println("Responsable");
		System.out.println("1. Ajouter une balade");
		//System.out.println("2. Calculer forfait");
		//System.out.println("3. Devenir membre");
		System.out.println("4. Quitter");
		int choix;
		do {
			System.out.print("Votre choix :");
			choix = Clavier.lireInt();
			if(choix <1 || choix>4) {
				System.out.println("Erreur encodage");
			}
		}
		while(choix <1 || choix>4);
		if(choix ==1) {
			ajouterBaladeCategorie();
		}
		else if(choix ==2) {
			//posterDisponibilite();
		}
		else if(choix ==3) {
			//consulterCalendrier();
		}
	}
	
	public void ajouterBaladeCategorie() {
		M_Categorie c = new M_Categorie();
		c.setCategorie(r.getCategorie());
		if(c.ajouterBaladeCalendrier()) {
			System.out.println("Balade ajoutée avec succès");
			fonctionnalitesResponsable();
		}
	}
}
