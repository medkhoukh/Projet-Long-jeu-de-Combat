

package jeu.Graphisme.objetsGraphiques;

public interface AttaqueInterface {

	/**
	 * Debuter une attaque en fonction de la probabilité de toucher
	 * @param attaquant
	 * @param victime
	 * @return
	 */
	public int debuterAttaque(CombattantInterface attaquant, CombattantInterface victime);

    
    public String getNom();
    public void setNom(String nom);
    public String getDescription();
    public void setDescription(String description);
    public double getProbabiliteToucher();
    public void setProbabiliteToucher(double chanceToucher); 
}
