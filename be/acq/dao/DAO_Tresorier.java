package be.acq.dao;
import be.acq.pojo.*;
import java.sql.*;
public class DAO_Tresorier extends DAO<Tresorier>{
	public DAO_Tresorier(Connection conn){
		super(conn);
	}
	public boolean create(Tresorier obj){
		return false;
	}
	
	public boolean delete(Tresorier obj){
		return false;
	}
	
	public boolean update(Tresorier obj){
		return false;
	}
	
	public Tresorier find(int id) {
		return null;
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
