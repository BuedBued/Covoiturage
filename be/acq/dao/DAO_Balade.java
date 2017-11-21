package be.acq.dao;
import be.acq.pojo.*;
import java.sql.*;
public class DAO_Balade extends DAO<Balade>{
	public DAO_Balade(Connection conn){
		super(conn);
	}
	public boolean create(Balade obj){
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
				stmt = connect.prepareStatement("INSERT INTO Balade (dateBalade,lieuBalade, idCategorie) VALUES (?,?)");
				stmt.setString(1, obj.getDate());
				stmt.setString(2, obj.getLieu());
				stmt.setInt(3, 0);
				//Execution de la commande SQL
				stmt.executeUpdate();
				//Récupération de l'id
				stmt = connect.prepareStatement("SELECT idBalade FROM Balade WHERE dateBalade = ? AND lieuBalade = ?",
						ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
				stmt.setString(1, obj.getDate());
				stmt.setString(2, obj.getLieu());
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
	
	public boolean delete(Balade obj){
		return false;
	}
	
	public boolean update(Balade obj){
		return false;
	}
	
	public Balade find(int id) {
		return null;
	}
}