package be.acq.pojo;

public class Membre extends Personne {
	private int idMembre;
	private double solde;
	private Categorie cat;
	
	public Membre() {super();}
	public Membre(double solde, Categorie cat) {
		super();
		this.solde = solde;
		this.cat = cat;
	}
	public Membre(String nom, String prenom, String date, String tel, String email, double solde, Categorie cat) {
		super(nom,prenom,date,tel,email);
		this.solde = solde;
		this.cat = cat;
	}
	//Getter
	public int getIdMembre() {return this.idMembre;}
	public double getSolde() {return this.solde;}
	public Categorie getCategorie() {return this.cat;}
	//Setter
	public void setSolde(double solde) {this.solde = solde;}
	public void setCategorie(Categorie cat) {this.cat = cat;}
	public void setIdMembre(int id) {this.idMembre = id;}
}
