package jeu.Entites;

public class Ennemi {
    private String nom;
    private int vie;
    private int attaque;

    public Ennemi(String nom, int vie, int attaque) {
        this.nom = nom;
        this.vie = vie;
        this.attaque = attaque;
    }

    public String getNom() {
        return nom;
    }

    public int getVie() {
        return vie;
    }

    public void setVie(int vie) {
        this.vie = vie;
    }

    public int getAttaque() {
        return attaque;
    }

    public void recevoirDegats(int degats) {
        this.vie -= degats;
    }

    public void attaquer(Personnage joueur) {
        joueur.recevoirDegats(this.attaque);
    }
}
