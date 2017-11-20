package be.acq.pojo;

public class Randonneur extends VTT{
	private int taillePetitPignon;
	private int tailleGrandPignon;
	
	public Randonneur() {super("Randonneur");}
	public Randonneur(int petitPignon, int grandPignon){
		super("Randonneur");
		this.taillePetitPignon = petitPignon;
		this.tailleGrandPignon = grandPignon;
	}
	public Randonneur(int taille, int diametre, int vitesse, String matiere, int petitPignon, int grandPignon) {
		super("Randonneur", taille, diametre, vitesse, matiere);
		this.taillePetitPignon = petitPignon;
		this.tailleGrandPignon = grandPignon;
	}
	
	//Getters
	public int getPetitPignon() {return this.taillePetitPignon;}
	public int getGrandPignon() {return this.tailleGrandPignon;}
	//Setters
	public void setPetitPignon(int taille) {this.taillePetitPignon = taille;}
	public void setGrandPignon(int taille) {this.tailleGrandPignon = taille;}
}
