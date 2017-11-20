package be.acq.pojo;

public class Categorie {
	private String nomCategorie;
	private Calendrier calendrier;
	
	public Categorie() {}
	public Categorie(String nom) {
		this.nomCategorie = nom;
	}
	public Categorie(String nom, Calendrier calendrier) {
		this.nomCategorie = nom;
		this.calendrier = calendrier;
	}
	
	//Getter
	public String getNom() {return nomCategorie;}
	public Calendrier getCalendrier() {return this.calendrier;}
	//Setter
	public void setNom(String nomCategorie) {this.nomCategorie = nomCategorie;}
	public void setCalendrier(Calendrier calendrier) {this.calendrier = calendrier;}
}
