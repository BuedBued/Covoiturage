package be.acq.dao;
import be.acq.pojo.*;
import java.sql.*;
public class DAO_Membre extends DAO<Membre>{
	public DAO_Membre(Connection conn){
		super(conn);
	}
	public boolean create(Membre obj){
		return false;
	}
	
	public boolean delete(Membre obj){
		return false;
	}
	
	public boolean update(Membre obj){
		return false;
	}
	
	public Membre find(int id) {
		return null;
	}
	public Membre findFromIdPersonne(int id) {
		Membre m = null;
		try{
			ResultSet result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Membre WHERE idPersonne = " +id);
			if(result.first()){
				m = new Membre();
				m.setSolde(result.getDouble("solde"));
				m.setIdMembre(result.getInt("idMembre"));
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return m;
	}
}
