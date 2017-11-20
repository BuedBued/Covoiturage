package be.acq.dao;
import java.sql.*;

public class CovoiturageCon{

	private static Connection instance = null;
	
	private CovoiturageCon(){
		try{
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			String url = "jdbc:ucanaccess://./Covoiturage.accdb";
			instance = DriverManager.getConnection(url);
		}
		catch(ClassNotFoundException ex){
			System.out.println("Classe de driver introuvable" + ex.getMessage());
		}
		catch (SQLException ex) {
			System.out.println("Erreur JDBC : " + ex.getMessage());
		}
		if (instance == null) {
			System.out.println("La base de données est inaccessible, fermeture du programme.");
        }
	}
	
	public static Connection getInstance() {
		if(instance == null){
			new CovoiturageCon();
		}
		return instance;
	}
}
