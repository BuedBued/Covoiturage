package be.acq.metier;
import be.acq.clavier.Clavier;
import be.acq.pojo.*;
import java.util.ArrayList;

public class M_Calendrier {
	private Calendrier cal;
	public M_Calendrier() {}
	public Calendrier getCalendrier() {return this.cal;}
	public void setCalendrier(Calendrier cal) {this.cal = cal;}
	
	public boolean ajouterBalade(Categorie c) {
		boolean res = false;
		M_Balade mb = new M_Balade();
		if(mb.creerBalade(c)){
			if (cal==null) {
				cal = new Calendrier();
				cal.setListeBalade(new ArrayList<Balade>());
			}
				cal.getListeBalade().add(mb.getBalade());
				res =true;
		}
		return res;
	}
	public void afficherBalade(Membre m) {
		ArrayList<Balade> listeBalade = cal.getListeBalade();
		for (int i = 0; i< listeBalade.size(); i++) {
			System.out.println((i+1)+". Lieu : " +listeBalade.get(i).getLieu() +"       Date : "+
		listeBalade.get(i).getDate());
		}
		System.out.println("Desirez-vous vous inscrire à une balade? Si oui, indiquez la balade où vous voulez vous "
				+ "inscrire");
		System.out.println("Sinon, indiquez 0");
		int choix;
		do {
			System.out.print("Votre choix : ");
			choix = Clavier.lireInt();
			if (choix<0 || choix>listeBalade.size())
				System.out.println("Erreur encodage");
		}
		while(choix<0 || choix>listeBalade.size());
		if (choix!=0) {
			M_Balade b = new M_Balade();
			b.setBalade(listeBalade.get(choix+1));
			if(b.inscrireVehicule(m))
				System.out.println("Inscription réussie");
		}
	}
	
	public void afficherBaladeCovoiturage(Membre m) {
		ArrayList<Balade> listeBalade = cal.getListeBalade();
		for (int i = 0; i< listeBalade.size(); i++) {
			System.out.println((i+1)+". Lieu : " +listeBalade.get(i).getLieu() +"       Date : "+
		listeBalade.get(i).getDate());
		}
		System.out.println("Cherchez-vous un covoiturage pour une balade? Si oui, indiquez la balade en question");
		System.out.println("Sinon, indiquez 0");
		int choix;
		do {
			System.out.print("Votre choix : ");
			choix = Clavier.lireInt();
			if (choix<0 || choix>listeBalade.size())
				System.out.println("Erreur encodage");
		}
		while(choix<0 || choix>listeBalade.size());
		if (choix!=0) {
			M_Balade b = new M_Balade();
			b.setBalade(listeBalade.get(choix+1));
			if(b.inscrireCovoiturage(m))
				System.out.println("Inscription covoiturage réussie");
		}
	}
}