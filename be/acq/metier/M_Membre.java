package be.acq.metier;
import be.acq.dao.*;
import be.acq.pojo.*;

import java.util.ArrayList;

import be.acq.clavier.*;
public class M_Membre {
	private Membre m;
	public M_Membre() {}
	public Membre getMembre() {return this.m;}
	public void setMembre(Membre m) {this.m = m;}
	
	public void inscriptionMembre() {
		DAO_Categorie dao_c = new DAO_Categorie(CovoiturageCon.getInstance());
		ArrayList<Categorie> listeCategorie = dao_c.selectionToutesCategories();
		System.out.println("Choix de la catégorie : ");
		int limite = listeCategorie.size();
		int choix;
		for (int i = 0; i<limite; i++) {
			System.out.println(listeCategorie.get(i).getIdCategorie() +". " + listeCategorie.get(i).getNom());
		}
		do {
			System.out.print("Votre choix : ");
			choix = Clavier.lireInt();
			if(choix <0 || choix>limite) {
				System.out.println("Erreur encodage");
			}
		}
		while(choix <0 || choix>limite);
		m.setCategorie(listeCategorie.get(choix));
		m.setSolde(20); //Cotisation annuelle
		DAO_Membre dao_m = new DAO_Membre(CovoiturageCon.getInstance());
		if(dao_m.create(m)) {
			//Fonction membres
		}	
	}
	
	public void fonctionnalitesMembre() {
		System.out.println("****************************");
		System.out.println("Membre");
		System.out.println("1. Payer son solde");
		System.out.println("2. Poster disponibilité");
		System.out.println("3. Consulter calendrier");
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
			payerSolde();
		}
		else if(choix ==2) {
			//posterDisponibilite();
		}
		else if(choix ==3) {
			//consulterCalendrier();
		}
	}
	
	public void payerSolde() {
		m.setSolde(0);
		System.out.println("Solde payé");
		fonctionnalitesMembre();
	}
}
