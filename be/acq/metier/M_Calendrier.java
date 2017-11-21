package be.acq.metier;
import be.acq.pojo.*;

import java.util.ArrayList;
public class M_Calendrier {
	private Calendrier cal;
	public M_Calendrier() {}
	public Calendrier getCalendrier() {return this.cal;}
	public void setCalendrier(Calendrier cal) {this.cal = cal;}
	public boolean ajouterBalade() {
		boolean res = false;
		M_Balade mb = new M_Balade();
		if(mb.creerBalade()) {
			if (cal.getListeBalade()==null)
				cal.setListeBalade(new ArrayList<Balade>());
			cal.getListeBalade().add(mb.getBalade());
			res =true;
		}
		return res;
	}
}