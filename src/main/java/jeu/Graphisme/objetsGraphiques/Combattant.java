package jeu.Graphisme.objetsGraphiques;
public abstract class Combattant implements CombattantInterface {
	 public String nom;
     public int vitalite;
     public int degats;

     public void AbstractCombattant() {}
     
     /**
      * Constructeur de Combattant
      * @param nom
      * @param vitalite
      * @param degat
      */
     public Combattant(String nom, int vitalite, int degat){
         this.nom = nom;
         this.vitalite = vitalite;
         this.degats = degat;
     }

     public String toString()
     {
         return "Nom: " + nom + ", Vitalité: " + vitalite + ", Dégâts: " + degats + "\n";

     }
     
     public void attaquer(CombattantInterface adversaire)
     {
    	 adversaire.defendre(this.degats);
    	 System.out.println(this.nom + " vient de frapper " + adversaire.getNom() + " et lui a causé " + this.degats + " points de     dégâts.");

     }

     public void defendre(int degats)
     {
    	 this.vitalite -= degats;
     }
     
     public int getVitalite() {
 		return vitalite;
 	}

 	public void setVitalite(int vitalite) {
 		this.vitalite = vitalite;
 	}

 	public int getDegats() {
 		return degats;
 	}

 	public void setDegats(int degat) {
 		this.degats = degat;
 	}

 	public String getNom() {
 		return nom;
 	}

 	public void setNom(String nom) {
 		this.nom = nom;
 	}
 	
 	public boolean estVivant() {
 		return this.vitalite > 0;
 	}
}
