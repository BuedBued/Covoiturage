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
		try {
			//Preparation de la commande SQL
			stmt = connect.prepareStatement("INSERT INTO Personne (nom,prenom,dateNaissance,telephone,mail) "
					+ "VALUES (?,?,?,?,?)");
			stmt.setString(1, o.getNom());
			stmt.setString(2, o.getPrenom());
			stmt.setDate(3,o.getDate());
			stmt.setString(4, o.getTelephone());
			stmt.setString(5, o.getEmail());
			//Execution de la commande SQL
			stmt.executeUpdate();
			//Récupération de l'id
			o.setId(stmt.getGeneratedKeys().getInt(1));
			b = true;
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
	
	public boolean update(Personne obj){
		return false;
	}
	public Personne find(int id){
		Personne p = new Personne();
		try{
			ResultSet result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Personne WHERE idPersonne = " +id);
			if(result.first()){
				p = new Personne(result.getString("nom"),result.getString("prenom"), result.getDate("dateNaissance"),
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
