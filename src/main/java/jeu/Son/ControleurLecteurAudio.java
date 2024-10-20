package jeu.Son;

import java.util.HashMap;

public class ControleurLecteurAudio {

    private static HashMap<String, LecteurAudio> lecteursAudio = new HashMap<>();

    public ControleurLecteurAudio() {

    }

    public void ajouterLecteurAudio(String nom, LecteurAudio lecteurAudio) {
        lecteursAudio.put(nom, lecteurAudio);
    }

    public static LecteurAudio getLecteurAudio(String lecteur) {
        return lecteursAudio.get(lecteur);
    }
}
