package be.acq.pojo;
import java.sql.Date;

public class Responsable extends Personne {
	private Categorie cat;
	public Responsable() {super();}
	public Responsable(Categorie cat) {
		super();
		this.cat = cat;
	}
	public Responsable(String nom, String prenom, Date date, String tel, String email, Categorie cat) 
	{
		super(nom,prenom,date,tel,email);
		this.cat = cat;
	}
	
	//Getter
	public Categorie getCategorie() {return this.cat;}
	//Setter
	public void setCategorie(Categorie cat) {this.cat = cat;}
}
