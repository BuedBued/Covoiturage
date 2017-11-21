package be.acq.dao;
import be.acq.pojo.*;
import java.sql.*;
public class DAO_Personne extends DAO<Personne> {
	public DAO_Personne(Connection conn){
		super(conn);
	}
	
	public boolean create(Personne o){
		boolean b = false;
		
		//Variables nécessaires pour la base de données
		PreparedStatement stmt = null;
		ResultSet res = null;
		try {
			//Test si la personne existe !
			stmt = connect.prepareStatement("SELECT idPersonne FROM Personne WHERE nom = ? AND prenom = ?",
					ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			stmt.setString(1, o.getNom());
			stmt.setString(2, o.getPrenom());
			res = stmt.executeQuery();
			//Si elle existe => Envoie d'un message dans la console et pas de création
			if(res.first())
				System.out.println("Cette personne est déjà inscrite");
			else {
				//Preparation de la commande SQL
				stmt = connect.prepareStatement("INSERT INTO Personne (nom,prenom,dateNaissance,telephone,mail,mdp) "
						+ "VALUES (?,?,?,?,?,?)");
				stmt.setString(1, o.getNom());
				stmt.setString(2, o.getPrenom());
				stmt.setString(3,o.getDate());
				stmt.setString(4, o.getTelephone());
				stmt.setString(5, o.getEmail());
				stmt.setString(6, o.getMDP());
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
			e.printStackTrace();
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
			e.printStackTrace();
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
			e.printStackTrace();
		}
		return b;
	}
	
	public Personne find(int id){
		Personne p = new Personne();
		PreparedStatement stmt = null;
		ResultSet res = null;
		try{
			stmt = connect.prepareStatement("SELECT * FROM Personne WHERE idPersonne = ?",
					ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			stmt.setInt(1, id);
			res = stmt.executeQuery();
			if(res.first()){
				p = new Personne(res.getString("nom"),res.getString("prenom"), res.getString("dateNaissance"),
						res.getString("telephone"), res.getString("mail"));
				p.setId(id);
				p.setMDP(res.getString("mdp"));
			}
			else
				p = null;
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return p;
	}
	
	public Personne selectFromConnection(String nom, String prenom, String mdp) {
		Personne p;
		PreparedStatement stmt = null;
		ResultSet result = null;
		try {
			//Test si la personne existe !
			stmt = connect.prepareStatement("SELECT * FROM Personne WHERE nom = ? AND prenom = ? AND mdp = ?",
					ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			stmt.setString(1, nom);
			stmt.setString(2, prenom);
			stmt.setString(3, mdp);
			result = stmt.executeQuery();
			if(result.first()) {
				String p_nom = result.getString("nom");
				String p_prenom = result.getString("prenom");
				String p_date = result.getString("dateNaissance");
				String p_tel = result.getString("telephone");
				String p_mail = result.getString("mail");
				int p_idPersonne = result.getInt("idPersonne");
				DAO_Membre dao_m = new DAO_Membre(CovoiturageCon.getInstance());
				Membre m = dao_m.findFromIdPersonne(p_idPersonne);
				if(m!=null) {
					m.setNom(p_nom);
					m.setPrenom(p_prenom);
					m.setDate(p_date);
					m.setEmail(p_mail);
					m.setTelephone(p_tel);
					m.setId(p_idPersonne);
					m.setMDP(mdp);
					return m;
				}
				else {
					DAO_Responsable dao_r = new DAO_Responsable(CovoiturageCon.getInstance());
					Responsable r = dao_r.findFromIdPersonne(p_idPersonne);
					if(r!=null) {
						r.setNom(p_nom);
						r.setPrenom(p_prenom);
						r.setDate(p_date);
						r.setEmail(p_mail);
						r.setTelephone(p_tel);
						r.setId(p_idPersonne);
						r.setMDP(mdp);
						return r;
					}
					else {
						DAO_Tresorier dao_t = new DAO_Tresorier(CovoiturageCon.getInstance());
						Tresorier t = dao_t.findFromIdPersonne(p_idPersonne);
						if(t!=null) {
							t.setNom(p_nom);
							t.setPrenom(p_prenom);
							t.setDate(p_date);
							t.setEmail(p_mail);
							t.setTelephone(p_tel);
							t.setId(p_idPersonne);
							t.setMDP(mdp);
							return t;
						}
						else {
							p = new Personne(result.getString("nom"),result.getString("prenom"), result.getString("dateNaissance"),
									result.getString("telephone"), result.getString("mail"));
							p.setId(result.getInt("idPersonne"));
							p.setMDP(mdp);
							return p;
						}
					}
				}
			}
			else {
				p = null;
				return p;
			}
		}
		catch(SQLException e){
			e.printStackTrace();
			return null;
		}
	}
}
