package jeu.Graphisme.objetsGraphiques;
public interface CombattantInterface {
	/**
	 * Attaquer un adversaire.
	 * @param adversaire : adversaire de type CombattantInterface.
	 */
	public void attaquer(CombattantInterface adversaire);
	
	/**
	 * Absorber des dégats reçus.
	 * @param degats : les dégats recus.
	 */
    public void defendre(int degats);

    
    public String getNom();
    public void setNom(String nom);
    public int getDegats();
    public void setDegats(int degats);
    public int getVitalite();
    public void setVitalite(int vitalite);
    
    /**
 	 * retourne si le combattant est vivant ou non.
 	 * @return boolean.
 	 */
 	public boolean estVivant();
}
