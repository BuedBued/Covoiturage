package be.acq.pojo;
import java.sql.Date;
public class Personne {
	private int idPersonne;
	private String nom;
	private String prenom;
	private Date dateNaissance;
	private String telephone;
	private String email;
	
	public Personne() {}
	public Personne(String nom, String prenom, Date dateNaissance, String telephone, String email) {
		this.nom = nom;
		this.prenom = prenom;
		this.dateNaissance = dateNaissance;
		this.telephone = telephone;
		this.email = email;
	}
	
	//Getters
	public int getId() {return this.idPersonne;}
	public String getNom() {return this.nom;}
	public String getPrenom() {return this.prenom;}
	public Date getDate() {return this.dateNaissance;}
	public String getTelephone() {return this.telephone;}
	public String getEmail() {return this.email;}
	//Setters
	public void setId(int id) {this.idPersonne = id;}
	public void setNom(String nom) {this.nom = nom;}
	public void setPrenom(String prenom) {this.prenom = prenom;}
	public void setDate(Date dateNaissance) {this.dateNaissance = dateNaissance;}
	public void setTelephone(String telephone) {this.telephone = telephone;}
	public void setEmail(String email) {this.email = email;}
}