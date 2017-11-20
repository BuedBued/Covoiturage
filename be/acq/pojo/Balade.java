package be.acq.pojo;

import java.util.Date;
import java.util.List;

public class Balade {
	private Date dateBalade;
	private String adresseBalade;
	private List<Vehicule> listeVehicule;
	
	public Balade() {}
	public Balade(Date dateBalade, String adresseBalade, List<Vehicule> listeVehicule) {
		this.dateBalade = dateBalade;
		this.adresseBalade = adresseBalade;
		this.listeVehicule = listeVehicule;
	}
	
	//Getters
	public Date getDate() {return this.dateBalade;}
	public String getAdresse() {return this.adresseBalade;}
	public List<Vehicule> getListeVehicule(){return this.listeVehicule;}
	//Setters
	public void setDate(Date dateBalade) {this.dateBalade = dateBalade;}
	public void setAdresse(String adresseBalade) {this.adresseBalade = adresseBalade;}
	public void setListeVehicule(List<Vehicule> listeVehicule) {this.listeVehicule = listeVehicule;}
}
