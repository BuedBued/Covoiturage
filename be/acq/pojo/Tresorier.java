package be.acq.pojo;
import java.sql.Date;
public class Tresorier extends Personne{
	public Tresorier() {super();}
	public Tresorier(String nom, String prenom, Date date, String tel, String email) 
	{
		super(nom,prenom,date,tel,email);
	}
}
