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
		try{
			ResultSet result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Responsable WHERE idPersonne = " +id);
			if(result.first()){
				m = new Responsable();
				m.setIdResponsable(result.getInt("idResponsable"));
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return m;
	}
}
