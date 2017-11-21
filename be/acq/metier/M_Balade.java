package be.acq.metier;

import be.acq.clavier.Clavier;
import be.acq.dao.CovoiturageCon;
import be.acq.dao.*;
import be.acq.pojo.*;

public class M_Balade {
	private Balade b;
	public M_Balade() {}
	public Balade getBalade() {return this.b;}
	public void setBalade(Balade b) {this.b = b;}
	
	public boolean creerBalade() {
		DAO_Balade dao_b = new DAO_Balade(CovoiturageCon.getInstance());
		encodageBalade();
		if(dao_b.create(b)) {
			System.out.println("Balade encodée");
			return true;
		}
		else {
			System.out.println("Erreur encodage Balade");
			return false;
		}
	}
	
	public void encodageBalade() {
		String p_lieuBalade, p_dateBalade;
		System.out.println("******************************");
		System.out.println("Ajout d'une balade");
		System.out.print("Lieu de la balade : ");
		p_lieuBalade = Clavier.lireString();
		System.out.print("Date de la balade : ");
		p_dateBalade = Clavier.lireString();
		if(b ==null)
			b = new Balade();
		b.setLieu(p_lieuBalade);
		b.setDate(p_dateBalade);
		//ATTENTION : Encodage d'une balade avec la liste de véhicules vide
	}
}