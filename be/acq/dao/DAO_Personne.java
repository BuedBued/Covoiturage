package be.acq.dao;
import be.acq.pojo.*;
import java.sql.*;
public class DAO_Personne extends DAO<Personne> {
	public DAO_Personne(Connection conn){
		super(conn);
	}
	
	public boolean create(Personne o){
		boolean b = false;
		PreparedStatement stmt = null;
		ResultSet res = null;
		try {			
			stmt = connect.prepareStatement("SELECT idPersonne FROM Personne WHERE nom = ? AND prenom = ?",
					ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			stmt.setString(1, o.getNom());
			stmt.setString(2, o.getPrenom());
			res = stmt.executeQuery();
			if(res.first())
				System.out.println("Cette personne est déjà inscrite");
			else {
				//Preparation de la commande SQL
				stmt = connect.prepareStatement("INSERT INTO Personne (nom,prenom,dateNaissance,telephone,mail) "
						+ "VALUES (?,?,?,?,?)");
				stmt.setString(1, o.getNom());
				stmt.setString(2, o.getPrenom());
				stmt.setString(3,o.getDate());
				stmt.setString(4, o.getTelephone());
				stmt.setString(5, o.getEmail());
				//Execution de la commande SQL
				stmt.executeUpdate();
				//Récupération de l'id
				stmt = connect.prepareStatement("SELECT idPersonne FROM Personne WHERE nom = ? AND prenom = ?",
						ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
				stmt.setString(1, o.getNom());
				stmt.setString(2, o.getPrenom());
				res = stmt.executeQuery();
				if(res.first()) {
					o.setId(res.getInt("idPersonne"));
					b = true;
				}
			}	
		}
		catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		return b;
	}
	
	public boolean delete(Personne o){
		boolean b = false;
		PreparedStatement stmt = null;
		try {
			//Preparation de la commande SQL
			stmt = connect.prepareStatement("DELETE FROM Personne WHERE idPersonne = ?");
			stmt.setInt(1, o.getId());
			//Execution de la commande SQL
			stmt.executeUpdate();
			b = true;
		}
		catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		return b;
	}
	
	public boolean update(Personne o){
		boolean b = false;
		PreparedStatement stmt = null;
		try {
			//Preparation de la commande SQL
			stmt = connect.prepareStatement("UPDATE Personne SET nom = ?, prenom = ?, dateNaissance = ?, "
					+ "telephone = ?, mail = ? WHERE idPersonne = ?");
			stmt.setString(1, o.getNom());
			stmt.setString(2, o.getPrenom());
			stmt.setString(3,o.getDate());
			stmt.setString(4, o.getTelephone());
			stmt.setString(5, o.getEmail());
			stmt.setInt(6, o.getId());
			//Execution de la commande SQL
			stmt.executeUpdate();
			b = true;
		}
		catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		return b;
	}
	
	public Personne find(int id){
		Personne p = new Personne();
		try{
			ResultSet result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Personne WHERE idPersonne = " +id);
			if(result.first()){
				p = new Personne(result.getString("nom"),result.getString("prenom"), result.getString("dateNaissance"),
						result.getString("telephone"), result.getString("mail"));
			}
			else
				p = null;
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return p;
	}
}
