package be.acq.pojo;

import java.util.List;

public class Calendrier {
	private List<Balade> listeBalade;
	
	public Calendrier() {}
	public Calendrier(List<Balade> listeBalade) {
		this.listeBalade = listeBalade;
	}
	
	//Getter
	public List<Balade> getListeBalade(){return this.listeBalade;}
	//Setter
	public void setListeBalade(List<Balade> listeBalade) {this.listeBalade = listeBalade;}
}
