package be.acq.pojo;
public class Tresorier extends Personne{
	private int idTresorier;
	public Tresorier() {super();}
	public Tresorier(String nom, String prenom, String date, String tel, String email) 
	{
		super(nom,prenom,date,tel,email);
	}
	
	//Getter
	public int getIdTresorier() {return this.idTresorier;}
	//Setter
	public void setIdTresorier(int id) {this.idTresorier = id;}
}
