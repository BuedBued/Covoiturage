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
		PreparedStatement stmt = null;
		ResultSet result = null;
		try {
			stmt = connect.prepareStatement("SELECT * FROM Tresorier t INNER JOIN Personne p ON "
					+ "p.idPersonne = t.idPersonne WHERE idTresorier = ?",
					ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			stmt.setInt(1, id);
			result = stmt.executeQuery();
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
		PreparedStatement stmt = null;
		ResultSet result = null;
		try {
			stmt = connect.prepareStatement("SELECT * FROM Tresorier WHERE idPersonne = ?",
					ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			stmt.setInt(1, id);
			result = stmt.executeQuery();
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
