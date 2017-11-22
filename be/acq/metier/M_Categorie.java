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
		cal.setCalendrier(c.getCalendrier());
		if(cal.ajouterBalade(c)) {
			//c.setCalendrier(cal.getCalendrier());
			DAO_Categorie dao_c = new DAO_Categorie(CovoiturageCon.getInstance());
			if(dao_c.updatePourBalade(c)) {
				b = true;
			}
			else
				System.out.println("Erreur mise à jour catégorie de la balade");
		}
		return b;
	}
	
	public void afficherCalendrierCovoiturage(Membre m) {
		M_Calendrier cal = new M_Calendrier();
		cal.setCalendrier(c.getCalendrier());
		cal.afficherBaladeCovoiturage(m);
	}
	
	public void afficherCalendrier(Membre m) {
		M_Calendrier cal = new M_Calendrier();
		//cal.setCalendrier(c.getCalendrier());
		cal.afficherBalade(m,c);
	}
}