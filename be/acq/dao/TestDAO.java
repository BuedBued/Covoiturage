package be.acq.dao;
import be.acq.pojo.*;

public class TestDAO {

	public static void main(String[] args) {
		DAO<Personne> dao_p = new DAO_Personne(CovoiturageCon.getInstance());
		Personne test = dao_p.find(3);
		System.out.println(test.getNom());
		/*
		Personne test2 = new Personne("Acquisto6", "Cyril", "01/01/01", "MAIL", "TELEPHONE");
		System.out.println("Creation : " + dao_p.create(test2));
		test2.setNom("Acquisto2");
		System.out.println("Update : " + dao_p.update(test2));
		System.out.println(dao_p.find(test2.getId()).getNom());
		System.out.println("Delete : " + dao_p.update(test2));*/
		
	}

}
