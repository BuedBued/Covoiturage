package be.acq.dao;
import be.acq.pojo.*;
import java.sql.*;
import java.util.ArrayList;

public class DAO_Categorie extends DAO<Categorie>{
	public DAO_Categorie(Connection conn){
		super(conn);
	}
	//Fonction pas nécessaire
	public boolean create(Categorie obj){
		return false;
	}
	//Fonction pas nécessaire
	public boolean delete(Categorie obj){
		return false;
	}
	//Fonction pas nécessaire
	public boolean update(Categorie obj){
		return false;
	}
	
	public boolean updatePourBalade(Categorie obj){
		boolean b = false;
		int idCat = obj.getIdCategorie();
		PreparedStatement stmt = null;
		try {
			//Preparation de la commande SQL
			stmt = connect.prepareStatement("UPDATE Balade SET idCategorie = ? WHERE idCategorie = ?");
			stmt.setInt(1, idCat);
			stmt.setInt(2, 0);
			//Execution de la commande SQL
			stmt.executeUpdate();
			b = true;
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return b;
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
	
	public ArrayList<Categorie> selectionToutesCategories() {
		ArrayList<Categorie> listCategorie = null;
		PreparedStatement stmt = null;
		ResultSet res = null;
		try{
			listCategorie = new ArrayList<Categorie>();
			stmt = connect.prepareStatement("SELECT * FROM Categorie",
					ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			res = stmt.executeQuery();
			while(res.next()) {
				Categorie tmp_c = new Categorie(res.getString("nomCategorie"));
				tmp_c.setIdCategorie(res.getInt("idCategorie"));
				listCategorie.add(tmp_c);
			}
			return listCategorie;
		}
		catch(SQLException e){
			e.printStackTrace();
			return null;
		}
	}
}
