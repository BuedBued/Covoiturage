package be.acq.dao;
import be.acq.pojo.*;

public class TestDAO {

	public static void main(String[] args) {
		DAO<Personne> dao_p = new DAO_Personne(CovoiturageCon.getInstance());
		Personne test = dao_p.find(1);
		System.out.println(test.getNom());
	}

}
