package be.acq.pojo;

import java.util.ArrayList;

public class Vehicule {
	private int idVehicule;
	private int placeMax;
	private Membre conducteur;
	private ArrayList<Membre> listPassager;
	
	public Vehicule() {}
	public Vehicule(int placeMax, Membre conducteur) {
		this.placeMax = placeMax;
		this.conducteur = conducteur;
	}
	
	//Getters
	public int getIdVehicule() {return this.idVehicule;}
	public int getPlaceMax() {return this.placeMax;}
	public Membre getConducteur() {return this.conducteur;}
	public ArrayList<Membre> getPassagers(){return this.listPassager;}
	//Setters
	public void setIdVehicule(int id) {this.idVehicule = id;}
	public void setPlaceMax(int placeMax) {this.placeMax = placeMax;}
	public void setConducteur(Membre conducteur) {this.conducteur = conducteur;}
	public void setPassager(ArrayList<Membre> listPassager) {this.listPassager = listPassager;}
}
