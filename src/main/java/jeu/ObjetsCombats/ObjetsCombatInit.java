package jeu.ObjetsCombats;

import java.util.ArrayList;

public class ObjetsCombatInit {
    private int taille;
    private ArrayList<ObjetsCombat> objs;

    public ObjetsCombatInit(){
        this.taille = 4;
        this.objs = new ArrayList<>();
        this.objs.add(new ObjetsCombat(true, 0, "Croix.png"));
        this.objs.add(new ObjetsCombat(false, 0, "Croix.png"));
        this.objs.add(new ObjetsCombat(true, 15, "épée.jpeg"));
        this.objs.add(new ObjetsCombat(false, 10, "bouclier.png"));
    }
    public int getTaille(){return this.taille;}
    public ArrayList<ObjetsCombat> getObjs(){
        return this.objs;
    }
}
