package be.acq.pojo;

import java.util.ArrayList;

public class Balade {
	private int idBalade;
	private String dateBalade;
	private String lieuBalade;
	private ArrayList<Vehicule> listeVehicule;
	
	public Balade() {}
	public Balade(String dateBalade, String lieuBalade, ArrayList<Vehicule> listeVehicule) {
		this.dateBalade = dateBalade;
		this.lieuBalade = lieuBalade;
		this.listeVehicule = listeVehicule;
	}
	
	//Getters
	public int getIdBalade() {return this.idBalade;}
	public String getDate() {return this.dateBalade;}
	public String getLieu() {return this.lieuBalade;}
	public ArrayList<Vehicule> getListeVehicule(){return this.listeVehicule;}
	//Setters
	public void setIdBalade(int id) {this.idBalade = id;}
	public void setDate(String dateBalade) {this.dateBalade = dateBalade;}
	public void setLieu(String lieuBalade) {this.lieuBalade = lieuBalade;}
	public void setListeVehicule(ArrayList<Vehicule> listeVehicule) {this.listeVehicule = listeVehicule;}
}
