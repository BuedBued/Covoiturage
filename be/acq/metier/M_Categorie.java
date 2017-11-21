package be.acq.metier;
import be.acq.dao.*;
import be.acq.pojo.*;
public class M_Categorie {
	private Categorie c;
	public M_Categorie() {}
	public Categorie getCategorie() {return this.c;}
	public void setCategorie(Categorie c) {this.c = c;}
	
	public boolean ajouterBaladeCalendrier() {
		boolean b = false;
		M_Calendrier cal = new M_Calendrier();
		if(cal.ajouterBalade()) {
			c.setCalendrier(cal.getCalendrier());
			DAO_Categorie dao_c = new DAO_Categorie(CovoiturageCon.getInstance());
			if(dao_c.updatePourBalade(c)) {
				System.out.println("[DEBUG] Mise à jour catégorie de la balade");
				b = true;
			}
			else
				System.out.println("Erreur mise à jour catégorie de la balade");
		}
		return b;
	}
}