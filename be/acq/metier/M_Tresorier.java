package be.acq.metier;
import be.acq.clavier.Clavier;
import be.acq.dao.*;
import be.acq.pojo.*;

import java.util.ArrayList;
public class M_Tresorier {
	private Tresorier t;
	public M_Tresorier() {}
	public Tresorier getTresorier() {return this.t;}
	public void setTresorier(Tresorier t) {this.t = t;}
	
	public void fonctionnalitesTresorier() {
		int choix;
		do {
			System.out.println("****************************");
			System.out.println("Tresorier");
			System.out.println("1. Consulter les dettes");
			System.out.println("2. Quitter");
			
			do {
				System.out.print("Votre choix : ");
				choix = Clavier.lireInt();
				if(choix !=1 && choix!=2) {
					System.out.println("Erreur encodage");
				}
			}
			while(choix !=1 && choix!=2);
			if(choix ==1) {
				consulterDette();
			}
		}
		while(choix !=2);
	}
	
	public void consulterDette() {
		DAO_Membre dao_m = new DAO_Membre(CovoiturageCon.getInstance());
		ArrayList<Membre> listeDette = dao_m.selectionToutesLesDettes();
		for (int i =0; i<listeDette.size(); i++) {
			System.out.println(listeDette.get(i).getNom() + " " + listeDette.get(i).getPrenom() + "a une dette de :" +
					listeDette.get(i).getSolde());
		}
	}
}
