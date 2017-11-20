package be.acq.pojo;

import java.util.List;

public class Vehicule {
	private int placeMax;
	private Membre conducteur;
	private List<Membre> listPassager;
	
	public Vehicule() {}
	public Vehicule(int placeMax, Membre conducteur) {
		this.placeMax = placeMax;
		this.conducteur = conducteur;
	}
	
	//Getters
	public int getPlaceMax() {return this.placeMax;}
	public Membre getConducteur() {return this.conducteur;}
	public List<Membre> getPassagers(){return this.listPassager;}
	//Setters
	public void setPlaceMax(int placeMax) {this.placeMax = placeMax;}
	public void setConducteur(Membre conducteur) {this.conducteur = conducteur;}
}
