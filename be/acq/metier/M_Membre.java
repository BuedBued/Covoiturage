package be.acq.metier;
import be.acq.dao.*;
import be.acq.pojo.*;
import be.acq.clavier.*;
public class M_Membre {
	private Membre m;
	public M_Membre() {}
	public Membre getMembre() {return this.m;}
	public void setMembre(Membre m) {this.m = m;}
	
	public void inscriptionMembre() {
		m.setSolde(20); //Cotisation annuelle
	}
}
