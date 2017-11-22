package be.acq.dao;
import be.acq.pojo.*;
import java.sql.*;
import java.util.ArrayList;
public class DAO_Balade extends DAO<Balade>{
	public DAO_Balade(Connection conn){
		super(conn);
	}
	//Non utilisée
	public boolean create(Balade obj){
		return false;
	}
	
	//Non utilisée
	public boolean delete(Balade obj){
		return false;
	}
	//Non utilisée
	public boolean update(Balade obj){
		return false;
	}
	//Non utilisée
	public Balade find(int id) {
		return null;
	}
	
	public ArrayList<Balade> selectionBaladeCategorie(Categorie c){
		ArrayList<Balade> listBalade = null;
		PreparedStatement stmt = null;
		ResultSet res = null;
		try{
			listBalade = new ArrayList<Balade>();
			stmt = connect.prepareStatement("SELECT * FROM Balade WHERE idCategorie = ?",
					ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			stmt.setInt(1, c.getIdCategorie());
			res = stmt.executeQuery();
			while(res.next()) {
				Balade tmp_b = new Balade();
				tmp_b.setDate(res.getString("dateBalade"));
				tmp_b.setIdBalade(res.getInt("idBalade"));
				tmp_b.setLieu(res.getString("lieuBalade"));
				listBalade.add(tmp_b);
			}
			return listBalade;
		}
		catch(SQLException e){
			e.printStackTrace();
			return null;
		}
	}
	
	public boolean createBalade(Balade obj, Categorie c) {
		boolean b = false;
		//Variables nécessaires pour la base de données
		PreparedStatement stmt = null;
		ResultSet res = null;
		try {
			//Test si la personne existe !
			stmt = connect.prepareStatement("SELECT * FROM Balade WHERE dateBalade = ? AND lieuBalade = ?",
					ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			stmt.setString(1, obj.getDate());
			stmt.setString(2, obj.getLieu());
			res = stmt.executeQuery();
			//Si elle existe => Envoie d'un message dans la console et pas de création
			if(res.first())
				System.out.println("Cette balade existe déjà");
			else {
				//Preparation de la commande SQL
				stmt = connect.prepareStatement("INSERT INTO Balade (dateBalade,lieuBalade, idCategorie) VALUES (?,?,?)");
				stmt.setString(1, obj.getDate());
				stmt.setString(2, obj.getLieu());
				stmt.setInt(3, c.getIdCategorie());
				//Execution de la commande SQL
				stmt.executeUpdate();
				//Récupération de l'id
				stmt = connect.prepareStatement("SELECT idBalade FROM Balade WHERE dateBalade = ? AND lieuBalade = ?"
						+ " AND idCategorie = ?", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
				stmt.setString(1, obj.getDate());
				stmt.setString(2, obj.getLieu());
				stmt.setInt(3, c.getIdCategorie());
				res = stmt.executeQuery();
				if(res.first()) {
					obj.setIdBalade(res.getInt("idBalade"));
					b = true;
				}
			}	
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return b;
	}
	
	public boolean inscriptionCovoiturage(Balade b, Vehicule v, Membre m) {
		boolean bool = false;
		//Variables nécessaires pour la base de données
		PreparedStatement stmt = null;
		ResultSet res = null;
		try {
			//Test si le covoiturage existe !
			stmt = connect.prepareStatement("SELECT * FROM LigneCovoiturage WHERE idBalade = ? AND idVehicule = ? "
					+ "AND idMembre = ?",ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			stmt.setInt(1, b.getIdBalade());
			stmt.setInt(2, v.getIdVehicule());
			stmt.setInt(3, m.getIdMembre());
			res = stmt.executeQuery();
			//Si elle existe => Envoie d'un message dans la console et pas de création
			if(res.first())
				System.out.println("Vous êtes déjà inscrit");
			else {
				//Preparation de la commande SQL
				stmt = connect.prepareStatement("INSERT INTO LigneCovoiturage (idBalade,idVehicule,idMembre) VALUES(?,?,?)");
				stmt.setInt(1, b.getIdBalade());
				stmt.setInt(2, v.getIdVehicule());
				stmt.setInt(3, m.getIdMembre());
				//Execution de la commande SQL
				stmt.executeUpdate();
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return bool;
	}
	
	public boolean createCovoiturage(Balade b, Vehicule v) {
		boolean bool = false;
		//Variables nécessaires pour la base de données
		PreparedStatement stmt = null;
		ResultSet res = null;
		try {
			//Test si le covoiturage existe !
			stmt = connect.prepareStatement("SELECT * FROM LigneCovoiturage WHERE idBalade = ? AND idVehicule = ?",
					ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			stmt.setInt(1, b.getIdBalade());
			stmt.setInt(2, v.getIdVehicule());
			res = stmt.executeQuery();
			//Si elle existe => Envoie d'un message dans la console et pas de création
			if(res.first())
				System.out.println("Ce covoiturage existe déjà");
			else {
				//Preparation de la commande SQL
				stmt = connect.prepareStatement("INSERT INTO LigneCovoiturage (idBalade,idVehicule,idMembre) VALUES (?,?,?)");
				stmt.setInt(1, b.getIdBalade());
				stmt.setInt(2, v.getIdVehicule());
				stmt.setInt(3, v.getConducteur().getIdMembre());
				//Execution de la commande SQL
				stmt.executeUpdate();
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return bool;
	}
	
}