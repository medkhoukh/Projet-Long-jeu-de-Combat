package jeu.ObjetsCombats;

public class ObjetsCombat {
    private boolean typeAttaque;
    private int bonus;
    private String imagePath;

    public ObjetsCombat(boolean typeAttaque, int bonus, String imagePath) {
        this.typeAttaque = typeAttaque;
        this.bonus = bonus;
        this.imagePath = imagePath;
    }

    public boolean getTypeAttaque(){
        return this.typeAttaque;
    }
    public int getBonus(){
        return this.bonus;
    }
    public String getImPath(){
        return this.imagePath;
    }
}
