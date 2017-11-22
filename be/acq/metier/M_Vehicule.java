package be.acq.metier;
import java.util.ArrayList;

import be.acq.clavier.Clavier;
import be.acq.dao.*;
import be.acq.pojo.*;

//import java.util.ArrayList;

//import be.acq.clavier.*;
public class M_Vehicule {
	private Vehicule v;
	//On instancie un objet DAO qui servira pour tout l'objet
	private DAO_Vehicule dao_v = new DAO_Vehicule(CovoiturageCon.getInstance());
	public M_Vehicule() {}
	public Vehicule getVehicule() {return this.v;}
	public void setVehicule(Vehicule v) {this.v = v;}
	
	public boolean inscriptionVehiculeBalade(Membre m) {
		boolean b = false;
		if(v ==null)
			v = new Vehicule();
		v = dao_v.findFromConducteur(m.getIdMembre());
		if(v == null) {
			encodageVehicule();
			v.setConducteur(m);
			if(dao_v.create(v))
				b=true;
		}
		else {
			v.setConducteur(m);
			b = true;
		}
		return b;
	}
	
	public void encodageVehicule() {
		v = new Vehicule();
		int p_maxPlace;
		System.out.println("**************************");
		System.out.println("Encodage véhicule");
		System.out.print("Place maximum : ");
		p_maxPlace = Clavier.lireInt();
		v.setPlaceMax(p_maxPlace);
	}
	
	public Vehicule selectionnerCovoiturage(Balade b) {
		DAO_Vehicule dao_p = new DAO_Vehicule(CovoiturageCon.getInstance());
		ArrayList<Vehicule> listeVehicule = dao_p.selectionTousVehicule(b);
		if(listeVehicule != null) {
			int taille = listeVehicule.size();
			for(int i = 0; i<taille; i++) {
				System.out.println((i+1) +". Dispo");
			}
			int choix;
			do {
				System.out.print("Choisissez le véhicule de votre choix : ");
				choix = Clavier.lireInt();
				if (choix<0 || choix>taille+1)
					System.out.println("Erreur d'encodage");
			}
			while(choix<0 || choix>taille+1);
			return listeVehicule.get(choix-1);
		}
		else
			return null;
	}
}