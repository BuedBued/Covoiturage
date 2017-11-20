package be.acq.pojo;

public class Trialiste extends VTT {
	private int poidsVelo;
	
	public Trialiste() {super("Trialiste");}
	public Trialiste(int poidsVelo){
		super("Trialiste");
		this.poidsVelo = poidsVelo;
	}
	public Trialiste(int taille, int diametre, int vitesse, String matiere, int poids) {
		super("Trialiste", taille, diametre, vitesse, matiere);
		this.poidsVelo = poids;
	}
	
	//Getter
	public int getPoids() {return this.poidsVelo;}
	//Setter
	public void setPoids(int poids) {this.poidsVelo = poids;}
}
