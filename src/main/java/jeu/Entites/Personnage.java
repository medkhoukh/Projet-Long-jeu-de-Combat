package jeu.Entites;

import jeu.ObjetsCombats.ObjetsCombat;

public class Personnage {
    private String nom;
    private int vie;
    private int attaque;
    private int defense;

    public Personnage(String nom, int vie, int attaque, int defense) {
        this.nom = nom;
        this.vie = vie;
        this.attaque = attaque;
        this.defense = defense;
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

    public void setAttaque(int attaque) {
        this.attaque = attaque;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public void appliquerAmelioration(ObjetsCombat objet) {
        if (objet.getTypeAttaque()) {
            this.attaque += objet.getBonus();
        } else {
            this.defense += objet.getBonus();
        }
    }

    public void recevoirDegats(int degats) {
        int degatsReduits = degats - defense;
        if (degatsReduits > 0) {
            this.vie -= degatsReduits;
        }
    }

    public void attaquer(Ennemi adversaire) {
        adversaire.recevoirDegats(this.attaque);
    }
}
