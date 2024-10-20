package jeu.Menus.Gameplay;

import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.animation.AnimationTimer;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;

public class PanneauDeJeu extends Pane {
    private List<CercleMobile> cercles;
    // Etats de jeu
    private static enum EtatJeu { Pret, EnJeu, Fini };
    private EtatJeu etatJeu;

    // BPM de la musique
    private int BPM;

    // Durée musique en seconde
    private int dureeMusique;

    private Indicateur indicateur;

    private static final char[] TOUCHES_VALIDES = {'Q', 'S', 'D'};

    private static final int[] PLAGE_OD = {40, 30, 20};

    private static final int[] LISTE_BPM = {158, 155, 40, 138};
    private static int od;

    public PanneauDeJeu(String nomMusique, int difficulte, int numeroMusique) {
        this.setFocusTraversable(true);

        BPM = LISTE_BPM[numeroMusique];
        dureeMusique = LISTE_BPM[numeroMusique];

        new TimerPartie();
        cercles = new ArrayList<>();
        //lireFichierJSON("src/Carte/" + musique + ".json");
        etatJeu = EtatJeu.EnJeu;

        indicateur = new Indicateur();
        
        od = PLAGE_OD[difficulte];

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                try{
                    MAJ();
                    repaint();
                } catch ( ConcurrentModificationException e ) {}
            }
        };
        timer.start();
    }

    private void repaint() {
        getChildren().clear();
        for (CercleMobile cercle : cercles) {
            getChildren().add(cercle);
        }
        Circle indRouge = indicateur.getIndRouge();
        Circle indOrange = indicateur.getIndOrange();
        Circle indVert = indicateur.getIndVert();
        Circle invis = indicateur.getInvis();
        getChildren().addAll(indRouge, indOrange, indVert, invis);
        getChildren().addAll(indicateur.getCircle1(), indicateur.getCircle2());
    }

    /**
     * Cette classe gère la pression clavier de l'utilisateur
     * Voir les différents codes ici : https://docs.oracle.com/javase/8/javafx/api/javafx/scene/input/KeyCode.html
     *
     * @param keyCode le code de l'entrée clavier
     */
    public void entreeUtilisateur(KeyCode keyCode, boolean pression, boolean impulsion) {
        boolean entreeValide = false;
        for ( char toucheValide : TOUCHES_VALIDES) {
            if (keyCode.getName().charAt(0) == toucheValide) {
                entreeValide = true;
            }
        }
        if (entreeValide) {
            gestionEntreeValide(keyCode.getName().charAt(0), pression, impulsion);
        } else {
            gestionEntreeInvalide();
        }
    }

    /**
     * Mise à jour de l'état du jeu.
     */
    public void MAJ() {
        if (etatJeu != EtatJeu.EnJeu) return;

        

        /*
        // Vérifier si le temps écoulé est supérieur à la durée de la musique
        if (tempsEcoule > dureeMusiqueMs) {
            etatJeu = EtatJeu.Fini;
        }
        */

        // Mise à jour des cercles
        List<CercleMobile> cerclesARetirer = new ArrayList<>();
        for (CercleMobile cercle : cercles) {
            cercle.update();
            if (cercle.getX() < 0) {
                cerclesARetirer.add(cercle);
                cercles.remove(cercle);
                gestionEntreeInvalide();
            }
        }
        for (CercleMobile cercle : cerclesARetirer) {
            cercles.remove(cercle);
        }
    }

    /**
     * Gestion de l'entrée valide par l'utilisateur.
     *
     * @param entree
     */
    public void gestionEntreeValide(char entree, boolean pression, boolean impulsion) {
        indicateur.interaction(pression, entree);
        
        if (impulsion) {
            for (CercleMobile cercle : cercles) {
                if ( Math.abs(cercle.getX() - indicateur.getX() + 50) < od && entree == cercle.getTouche()) {
                    cercle.valider();
                    break;
                }
            }
            impulsion = false;
        }
        
    }

    /**
     * Gestion de l'entrée invalide par l'utilisateur.
     */
    public void gestionEntreeInvalide() {
        // Gérer l'entrée invalide
        // Par exemple : joueur.recevoirDegats(ennemi.getAttaque());
    }

    public void addCercle(CercleMobile cercle) {
        cercles.add(cercle);
    }

    public void removeCercle(CercleMobile cercle) {
        cercles.remove(cercle);
    }

    public int getBPM() {
        return BPM;
    }

    public int getNbBattements() {
        return (int)Math.floor(((double)BPM * dureeMusique) / 60.0);
    }

    public void setEtatJeuFini() {
        etatJeu = EtatJeu.Fini;
    }
}
