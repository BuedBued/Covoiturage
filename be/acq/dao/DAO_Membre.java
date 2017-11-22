package be.acq.dao;
import be.acq.pojo.*;
import java.sql.*;
import java.util.ArrayList;
public class DAO_Membre extends DAO<Membre>{
	public DAO_Membre(Connection conn){
		super(conn);
	}
	public boolean create(Membre obj){
		boolean b = false;
		//Variables nécessaires pour la base de données
		PreparedStatement stmt = null;
		ResultSet res = null;
		try {
			//Test si la personne existe !
			stmt = connect.prepareStatement("SELECT * FROM Membre WHERE idPersonne = ?",
					ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			stmt.setInt(1, obj.getId());
			res = stmt.executeQuery();
			//Si elle existe => Envoie d'un message dans la console et pas de création
			if(res.first())
				System.out.println("Ce membre est déjà inscrit");
			else {
				//Insertion dans la table Membre
				stmt = connect.prepareStatement("INSERT INTO Membre (solde,idPersonne) VALUES (?,?)");
				stmt.setDouble(1, obj.getSolde());
				stmt.setInt(2, obj.getId());
				//Execution de la commande SQL
				stmt.executeUpdate();
				//Récupération de l'id
				stmt = connect.prepareStatement("SELECT idMembre FROM Membre WHERE idPersonne = ?",
						ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
				stmt.setInt(1, obj.getId());
				res = stmt.executeQuery();
				if(res.first()) {
					//Insertion dans la table LigneCategorie
					obj.setIdMembre(res.getInt("idMembre"));
					stmt = connect.prepareStatement("INSERT INTO LigneCategorie (idMembre,idCategorie) VALUES (?,?)");
					stmt.setInt(1, obj.getIdMembre());
					stmt.setInt(2, obj.getCategorie().getIdCategorie());
					//Execution de la commande SQL
					stmt.executeUpdate();
					if(res.first()) {
						//Si tout est réussi
						return b = true;
					}
					else {
						System.out.println("Insertion catégorie échouée");
					}
				}
				else {
					System.out.println("Insertion Membre échouée");
				}
			}	
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return b;
	}
	
	public boolean delete(Membre obj){
		return false;
	}
	
	public boolean update(Membre obj){
		boolean b = false;
		PreparedStatement stmt = null;
		try {
			//Preparation de la commande SQL
			stmt = connect.prepareStatement("UPDATE Membre SET idPersonne = ?, solde = ?"
					+ " WHERE idMembre = ?");
			stmt.setInt(1, obj.getId());
			stmt.setDouble(2, obj.getSolde());
			stmt.setInt(3, obj.getIdMembre());
			//Execution de la commande SQL
			stmt.executeUpdate();
			b = true;
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return b;
	}
	
	public Membre find(int id) {
		return null;
	}
	
	public ArrayList<Membre> selectionToutesLesDettes(){
		ArrayList<Membre> listeMembre = null;
		PreparedStatement stmt = null;
		ResultSet res = null;
		try{
			listeMembre = new ArrayList<Membre>();
			stmt = connect.prepareStatement("SELECT * FROM Membre M INNER JOIN Personne P ON M.idPersonne = P.idPersonne "
					+ "WHERE solde>0", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			res = stmt.executeQuery();
			while(res.next()) {
				Membre tmp_m = new Membre();
				tmp_m.setNom(res.getString("nom")); 
				tmp_m.setPrenom(res.getString("prenom"));
				tmp_m.setDate(res.getString("dateNaissance"));
				tmp_m.setTelephone(res.getString("telephone"));
				tmp_m.setEmail(res.getString("mail"));
				tmp_m.setSolde(res.getDouble("solde"));
				listeMembre.add(tmp_m);
			}
			return listeMembre;
		}
		catch(SQLException e){
			e.printStackTrace();
			return null;
		}
	}
	public Membre findFromIdPersonne(int id) {
		Membre m = null;
		try{
			ResultSet result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Membre m INNER JOIN LigneCategorie l ON "
							+ "L.idMembre = m.idMembre INNER JOIN Categorie c ON c.idCategorie = l.idCategorie WHERE "
							+ "idPersonne = " +id);
			if(result.first()){
				m = new Membre();
				m.setSolde(result.getDouble("solde"));
				m.setIdMembre(result.getInt("idMembre"));
				String nomCategorie = result.getString("nomCategorie");
				if (nomCategorie.equals("Cyclo"))
					m.setCategorie(new Cyclo());
				else if(nomCategorie.equals("VTT"))
					m.setCategorie(new VTT());
				else if (nomCategorie.equals("Trialiste"))
					m.setCategorie(new Trialiste());
				else if (nomCategorie.equals("Randonneur"))
					m.setCategorie(new Randonneur());
				else
					m.setCategorie(new Descente());
				m.getCategorie().setIdCategorie(result.getInt("idCategorie"));
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return m;
	}
}
