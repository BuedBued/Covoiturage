package be.acq.pojo;

public class Descente extends VTT{
	private String typeAmortisseur;
	private String typeFrein;
	public Descente() {super("Descente");}
	public Descente(String typeAmortisseur, String typeFrein){
		super("Descente");
		this.typeAmortisseur = typeAmortisseur;
		this.typeFrein = typeFrein;
	}
	public Descente(int taille, int diametre, int vitesse, String matiere, String typeAmortisseur, String typeFrein) {
		super("Descente", taille, diametre, vitesse, matiere);
		this.typeAmortisseur = typeAmortisseur;
		this.typeFrein = typeFrein;
	}
	
	//Getters
	public String getAmortisseur() {return this.typeAmortisseur;}
	public String getFrein() {return this.typeFrein;}
	//Setters
	public void setAmortisseur(String typeAmortisseur) {this.typeAmortisseur = typeAmortisseur;}
	public void setFrein(String typeFrein) {this.typeFrein = typeFrein;}
}
