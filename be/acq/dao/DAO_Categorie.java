package be.acq.dao;
import be.acq.pojo.*;
import java.sql.*;

public class DAO_Categorie extends DAO<Categorie>{
	public DAO_Categorie(Connection conn){
		super(conn);
	}
	//Fonction pas n�cessaire
	public boolean create(Categorie obj){
		return false;
	}
	//Fonction pas n�cessaire
	public boolean delete(Categorie obj){
		return false;
	}
	//Fonction pas n�cessaire
	public boolean update(Categorie obj){
		return false;
	}
	
	public Categorie find(int id) {
		Categorie c = new Categorie();
		PreparedStatement stmt = null;
		ResultSet res = null;
		try{
			stmt = connect.prepareStatement("SELECT nomCategorie FROM Categorie WHERE idCategorie = ?",
					ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			stmt.setInt(1, id);
			res = stmt.executeQuery();
			if(res.first()) {
				String nomCategorie = res.getString("nomCategorie");
				switch(nomCategorie) {
				case "Cyclo" :
					c = new Cyclo();
					break;
				case "VTT" :
					c = new VTT();
					break;
				case "Trialiste" :
					c = new Trialiste();
					break;
				case "Randonneur" :
					c = new Randonneur();
					break;
				case "Descente" :
					c = new Descente();
					break;
				}
			}
			else
				return null;
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return c;
	}
}