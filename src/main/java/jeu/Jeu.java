package jeu;
import javafx.stage.Stage;
import jeu.Graphisme.pages.PageDemarage;
import javafx.application.Application;
import jeu.Son.LecteurAudio;
/*La classe principale du jeu
 */
public class Jeu extends Application {

    private static LecteurAudio lecteurAudio = new LecteurAudio(0.2f, 1000, 50);

    private static int DIFFICULTE = 1;

    private static Stage baseGraphique;
    public static Stage getBaseGraphique() {
        return baseGraphique;
    }

    /*
     * Le point d'entré du jeu
     */
    @Override
    public void start(Stage baseGraphique) throws Exception {
        Jeu.baseGraphique = baseGraphique;
        // on affiche la fenetre
        baseGraphique.show();
        PageDemarage.demarer();
    }



    /** Fonction dédiée au javaFX pour les cas d'erreurs
     * @param args
     */
    public static void main(String[] args){
        launch(args);
    }

    public static LecteurAudio getLecteurAudio() {
        return lecteurAudio;
    }

    public static void changerVolume(double volume) {
        lecteurAudio.setVolume(volume);
    }


    public static void setDifficulte(int difficulte) {
        DIFFICULTE = difficulte;
    }

    public static int getDifficulte() {
        return DIFFICULTE;
    }

}

