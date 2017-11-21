package be.acq.dao;
import be.acq.pojo.*;
import java.sql.*;
public class DAO_Tresorier extends DAO<Tresorier>{
	public DAO_Tresorier(Connection conn){
		super(conn);
	}
	//Fonction non nécessaire
	public boolean create(Tresorier obj){
		return false;
	}
	//Fonction non nécessaire
	public boolean delete(Tresorier obj){
		return false;
	}
	//Fonction non nécessaire
	public boolean update(Tresorier obj){
		return false;
	}
	
	public Tresorier find(int id) {
		Tresorier m = null;
		try{
			ResultSet result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Tresorier t INNER JOIN Personne p ON "
							+ "p.idPersonne = t.idPersonne WHERE idTresorier = " +id);
			if(result.first()){
				m = new Tresorier();
				m.setIdTresorier(result.getInt("idTresorier"));
				m.setId(result.getInt("idPersonne"));
				m.setNom(result.getString("nom"));
				m.setPrenom(result.getString("prenom"));
				m.setDate(result.getString("dateNaissance"));
				m.setTelephone(result.getString("telephone"));
				m.setEmail(result.getString("mail"));
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return m;
	}
	public Tresorier findFromIdPersonne(int id) {
		Tresorier m = null;
		try{
			ResultSet result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Tresorier WHERE idPersonne = " +id);
			if(result.first()){
				m = new Tresorier();
				m.setIdTresorier(result.getInt("idTresorier"));
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return m;
	}
}
