package be.acq.pojo;

public class Cyclo extends Categorie {
	private int taille;
	private int diametreRoue;
	private int nombreVitesse;
	private String matiereVelo;
	
	public Cyclo() {super("Cyclo");}
	public Cyclo(int taille, int diametreRoue, int nombreVitesse, String matiereVelo) {
		super("Cyclo");
		this.diametreRoue = diametreRoue;
		this.taille = taille;
		this.nombreVitesse = nombreVitesse;
		this.matiereVelo = matiereVelo;
	}
	

	//Getter
	public int getTaille() {return this.taille;}
	public int getDiametre() {return this.diametreRoue;}
	public int getVitesse() {return this.nombreVitesse;}
	public String getMatiere() {return this.matiereVelo;}
	//Setter
	public void setTaille(int taille) {this.taille = taille;}
	public void setDiametre(int diametre) {this.diametreRoue = diametre;}
	public void setVitesse(int vitesse) {this.nombreVitesse = vitesse;}
	public void setMatiere(String matiereVelo) {this.matiereVelo = matiereVelo;}
	
}
