package be.acq.dao;
import be.acq.pojo.*;
import java.sql.*;
public class DAO_Personne extends DAO<Personne> {
	public DAO_Personne(Connection conn){
		super(conn);
	}
	public boolean create(Personne obj){
		return false;
	}
	
	public boolean delete(Personne obj){
		return false;
	}
	
	public boolean update(Personne obj){
		return false;
	}
	public Personne find(int id){
		Personne p = new Personne();
		try{
			ResultSet result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Personne WHERE idPersonne = " +id);
			if(result.first()){
				p = new Personne(result.getString("nom"),result.getString("prenom"), result.getDate("dateNaissance"),
						result.getString("telephone"), result.getString("mail"));
			}
			else
				p = null;
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return p;
	}
}
