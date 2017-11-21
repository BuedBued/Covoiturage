package be.acq.pojo;

import java.util.ArrayList;

public class Calendrier {
	private ArrayList<Balade> listeBalade;
	
	public Calendrier() {}
	public Calendrier(ArrayList<Balade> listeBalade) {
		this.listeBalade = listeBalade;
	}
	
	//Getter
	public ArrayList<Balade> getListeBalade(){return this.listeBalade;}
	//Setter
	public void setListeBalade(ArrayList<Balade> listeBalade) {this.listeBalade = listeBalade;}
}
