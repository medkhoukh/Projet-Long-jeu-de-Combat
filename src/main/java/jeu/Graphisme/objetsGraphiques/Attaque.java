package jeu.Graphisme.objetsGraphiques;
import java.util.Random;

public class Attaque implements AttaqueInterface {
	
	public int degats;
	public double probabiliteToucher;
	public String nom;
	public String description;
	private int chanceToucher;

	public Attaque(String nom, String description, int degats, double probaToucher) {
	       this.degats = degats;
	       this.probabiliteToucher = probaToucher;
	       this.nom = nom;
	       this.description = description;
	   }

	public int debuterAttaque(CombattantInterface attaquant, CombattantInterface victime) {
		int random = new Random().nextInt(100);
		int degatsInfliges = 0;
		if(random < this.chanceToucher) {
			degatsInfliges = this.degats;
			System.out.println(attaquant.getNom() + " réussit son attaque " + this.nom + " et inflige " + this.degats + " dégâts !");
;
		} else {
			System.out.println("L'attaque " + this.nom + " de " + attaquant.getNom() +  " a raté !");
		}
		return degatsInfliges;
	}

	public String getNom() {
		return this.nom;
	}

	public String getDescription() {
		return this.description;
	}

	public double getProbabiliteToucher() {
		return this.probabiliteToucher;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setProbabiliteToucher(double probaToucher) {
		this.probabiliteToucher = probaToucher;
	}

	@Override
	public String toString() {
		return nom + ", " + description + " [degats=" + degats + ", chanceToucher=" + chanceToucher + "]\n";
	}

	public String getDefinition() {
		return null;
	}

	public String information() {
		return null;
	}
	
	

}
