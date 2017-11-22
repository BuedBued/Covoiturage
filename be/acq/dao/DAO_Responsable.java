package be.acq.dao;
import be.acq.pojo.*;
import java.sql.*;
public class DAO_Responsable extends DAO<Responsable>{
	public DAO_Responsable(Connection conn){
		super(conn);
	}
	public boolean create(Responsable obj){
		return false;
	}
	
	public boolean delete(Responsable obj){
		return false;
	}
	
	public boolean update(Responsable obj){
		return false;
	}
	
	public Responsable find(int id) {
		return null;
	}
	public Responsable findFromIdPersonne(int id) {
		Responsable m = null;
		PreparedStatement stmt = null;
		ResultSet result = null;
		try{
			stmt = connect.prepareStatement("SELECT * FROM Responsable r INNER JOIN Categorie c ON r.idCategorie = "
					+ "c.idCategorie WHERE idPersonne = ?",ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			stmt.setInt(1,id);
			result= stmt.executeQuery();
			if(result.first()){
				m = new Responsable();
				m.setIdResponsable(result.getInt("idResponsable"));
				m.setCategorie(new Categorie(result.getString("nomCategorie")));
				m.getCategorie().setIdCategorie(result.getInt("idCategorie"));
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return m;
	}
}
