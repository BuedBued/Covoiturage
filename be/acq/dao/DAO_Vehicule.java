package be.acq.dao;
import be.acq.pojo.*;
import java.sql.*;
import java.util.ArrayList;
public class DAO_Vehicule extends DAO<Vehicule>{
	public DAO_Vehicule(Connection conn){
		super(conn);
	}
	public boolean create(Vehicule obj){
		boolean b = false;
		//Variables nécessaires pour la base de données
		PreparedStatement stmt = null;
		ResultSet res = null;
		try {
			//On a déjà testé si le véhicule existe auparavant ! 
			//Insertion dans la table Vehicule
			stmt = connect.prepareStatement("INSERT INTO Vehicule (maxPlace,idMembre) VALUES (?,?)");
			stmt.setInt(1, obj.getPlaceMax());
			stmt.setInt(2, obj.getConducteur().getIdMembre());
			//Execution de la commande SQL
			stmt.executeUpdate();
			//Récupération de l'id
			stmt = connect.prepareStatement("SELECT idVehicule FROM Vehicule WHERE idMembre = ?",
					ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			stmt.setInt(1, obj.getConducteur().getIdMembre());
			res = stmt.executeQuery();
			if(res.first()) {
				//Insertion de l'id dans le vehicule
				obj.setIdVehicule(res.getInt("idVehicule"));
				b=true;
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return b;
	}
	
	public boolean delete(Vehicule obj){
		return false;
	}
	
	public boolean update(Vehicule obj){
		return false;
	}
	
	public Vehicule find(int id) {
		return null;
	}
	
	public Vehicule findFromConducteur(int id) {
		Vehicule retour = null;
		//Variables nécessaires pour la base de données
		PreparedStatement stmt = null;
		ResultSet res = null;
		try {
			//Test si la personne existe !
			stmt = connect.prepareStatement("SELECT * FROM Vehicule WHERE idMembre = ?",
					ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			stmt.setInt(1, id);
			res = stmt.executeQuery();
			if(res.first()) {
				retour = new Vehicule();
				retour.setPlaceMax(res.getInt("maxPlace"));
				retour.setIdVehicule(res.getInt("idVehicule"));
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return retour;
	}
	
	public ArrayList<Vehicule> selectionTousVehicule(Balade b){
		ArrayList<Vehicule> listVehicule = null;
		PreparedStatement stmt = null;
		ResultSet res = null;
		try{
			listVehicule = new ArrayList<Vehicule>();
			stmt = connect.prepareStatement("SELECT maxPlace, v.idVehicule FROM Vehicule v INNER JOIN LigneCovoiturage l"
					+ " ON v.idVehicule = l.idVehicule WHERE idBalade = ? AND maxPlace > (SELECT count(*) FROM "
					+ "LigneCovoiturage WHERE idBalade = ? GROUP BY idVehicule) GROUP BY idVehicule",
					ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			stmt.setInt(1, b.getIdBalade());
			stmt.setInt(2, b.getIdBalade());
			res = stmt.executeQuery();
			while(res.next()) {
				Vehicule tmp_v = new Vehicule();
				tmp_v.setPlaceMax(res.getInt("maxPlace"));
				tmp_v.setIdVehicule(res.getInt("idVehicule"));
				listVehicule.add(tmp_v);
			}
			return listVehicule;
		}
		catch(SQLException e){
			e.printStackTrace();
			return null;
		}
	}
}