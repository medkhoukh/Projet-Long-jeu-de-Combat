package jeu.Menus.Gameplay;

public class TimerPartie {

    private long debutPartie;

    public TimerPartie () {
    }

    public void debut () {
        debutPartie = System.currentTimeMillis();
    }

    // En ms
    public long getTempsEcoule () {
        return System.currentTimeMillis() - debutPartie;
    }
}

