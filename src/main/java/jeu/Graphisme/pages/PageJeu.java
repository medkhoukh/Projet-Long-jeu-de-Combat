package jeu.Graphisme.pages;

import jeu.Jeu;
import jeu.Entites.Ennemi;
import jeu.Entites.Personnage;
import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.beans.binding.Bindings;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.stage.Stage;
import javafx.util.Duration;
import jeu.Menus.Gameplay.*;
import jeu.Son.*;

import java.util.Random;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.media.AudioClip;

public class PageJeu extends VBox {
    private Personnage joueur;
    private Ennemi ennemi;
    private ProgressBar joueurVieBar;
    private ProgressBar ennemiVieBar;
    private Timeline timeline;
    private Timeline timelineAffichage;
    private List<CercleMobile> cercles;
    private PanneauDeJeu panneauDeJeu;
    private LecteurAudio lecteurMusique;
    private AudioClip degatSound;
    private AudioClip musiqueVictoire;
    private AudioClip musiqueDefaite;
    private Random random;
    private Image backgroundIm;
    private BackgroundImage background;

    private boolean impulsion;

    private static final int[] LISTE_OFFSET = {300, 250, 100, 150};

    private int offSet; //ms

    public PageJeu(String niveau, Personnage joueur, int difficulte, int numeroMusique) {
        super(20);
        this.setAlignment(Pos.TOP_CENTER);
        this.setPadding(new Insets(20, 20, 20, 20));
        this.offSet = LISTE_OFFSET[numeroMusique];
        this.joueur = joueur;
        this.ennemi = new Ennemi("Robot Ennemi", 200 * (difficulte + 1), 10); // Exemple de robot ennemi
        this.cercles = new ArrayList<>();
        this.panneauDeJeu = new PanneauDeJeu("nomMusique", difficulte, numeroMusique);
        this.lecteurMusique = Jeu.getLecteurAudio();
        this.degatSound = new AudioClip(getClass().getResource("/Musique/Degat.mp3").toString());
        this.degatSound.setVolume(0.1); // Baisser le volume à 10%
        this.musiqueVictoire = new AudioClip(getClass().getResource("/Musique/Victoire.mp3").toString());
        this.musiqueVictoire.setVolume(2);
        this.musiqueDefaite = new AudioClip(getClass().getResource("/Musique/Defaite.mp3").toString());
        this.musiqueDefaite.setVolume(0.3);

        // Charger la musique depuis le classpath
        String cheminMusique = "/Musique/Musique" + numeroMusique + ".mp3";

        Label label = new Label("En cours de jeu: " + niveau);
        label.setStyle("-fx-font-size: 24pt; -fx-font-weight: bold; -fx-text-fill: white; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.5), 0, 0, 0, 4);");

        joueurVieBar = new ProgressBar();
        joueurVieBar.setProgress(1.0); // Vie initiale à 100%
        joueurVieBar.setStyle("-fx-accent: green;");

        ennemiVieBar = new ProgressBar();
        ennemiVieBar.setProgress(1.0); // Vie initiale à 100%
        ennemiVieBar.setStyle("-fx-accent: green;");

        // Charger les images du joueur et de l'ennemi
        Image joueurImage = new Image(getClass().getResourceAsStream("/Elements/Joueur.jpg"));
        ImageView joueurImageView = new ImageView(joueurImage);
        joueurImageView.setPreserveRatio(true);

        Image ennemiImage = new Image(getClass().getResourceAsStream("/Elements/Ennemi" + numeroMusique + ".png"));
        ImageView ennemiImageView = new ImageView(ennemiImage);
        ennemiImageView.setPreserveRatio(true);

        // Disposition horizontale des images et des barres de vie
        VBox joueurBox = new VBox(10, joueurVieBar, joueurImageView);
        joueurBox.setAlignment(Pos.CENTER);

        VBox ennemiBox = new VBox(10, ennemiVieBar, ennemiImageView);
        ennemiBox.setAlignment(Pos.CENTER);

        HBox hboxImages = new HBox(50, joueurBox, panneauDeJeu, ennemiBox);
        hboxImages.setAlignment(Pos.CENTER);
        hboxImages.setPadding(new Insets(50, 0, 0, 0)); // Ajouter un padding en haut pour abaisser les images

        Button boutonRetour = new Button("Retour");
        boutonRetour.setMinSize(200, 50);
        boutonRetour.setOnAction(e -> {
            musiqueVictoire.stop();
            musiqueDefaite.stop();
            if (timeline != null) {
                timeline.stop();
            }
            if (timelineAffichage != null) {
                timelineAffichage.stop();
            }
            if (lecteurMusique != null) {
                lecteurMusique.arreter();
            }
            degatSound.stop();
            Stage stage = (Stage) this.getScene().getWindow();
            stage.setFullScreen(false); // Désactiver le plein écran
            PageMenuPrincipal.demarer();
        });

        VBox vboxTop = new VBox(10, label);
        vboxTop.setAlignment(Pos.TOP_CENTER);

        VBox vboxBottom = new VBox(20, hboxImages, boutonRetour);
        vboxBottom.setAlignment(Pos.BOTTOM_CENTER);

        this.getChildren().addAll(vboxTop, vboxBottom);

        Scene sceneJeu = new Scene(this);
        impulsion = true;

        // Bindings pour redimensionner les éléments en fonction de la taille de la fenêtre
        joueurImageView.fitWidthProperty().bind(sceneJeu.widthProperty().multiply(0.2));
        ennemiImageView.fitWidthProperty().bind(sceneJeu.widthProperty().multiply(0.2));
        boutonRetour.prefWidthProperty().bind(sceneJeu.widthProperty().multiply(0.25));
        boutonRetour.prefHeightProperty().bind(sceneJeu.heightProperty().multiply(0.1));
        label.styleProperty().bind(Bindings.concat("-fx-font-size: ", sceneJeu.widthProperty().multiply(0.03), "px; -fx-font-weight: bold; -fx-text-fill: white; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.5), 0, 0, 0, 4);"));

        sceneJeu.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                panneauDeJeu.entreeUtilisateur(event.getCode(), true, impulsion);
                impulsion = false;
            }
        });

        sceneJeu.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                panneauDeJeu.entreeUtilisateur(event.getCode(), false, false);
                impulsion = true;
            }
        });

        Stage stage = Jeu.getBaseGraphique();
        stage.setScene(sceneJeu);
        stage.setTitle("Jeu - " + niveau);

        // background
        backgroundIm = new Image(getClass().getResource("/Elements/background" + numeroMusique + ".jpg").toExternalForm());
        background = new BackgroundImage(backgroundIm,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(1.0, 1.0, true, true, false, false));
        this.setBackground(new Background(background));

        // Timeline qui gère l'affichage
        timelineAffichage = new Timeline(new KeyFrame(Duration.millis(20), event -> {
            // Mise à jour des cercles
            List<CercleMobile> cerclesARetirer = new ArrayList<>();
            for (CercleMobile cercle : cercles) {
                cercle.update();
                if (cercle.getX() < 0.0 - cercle.getRayon()) {
                    cerclesARetirer.add(cercle);
                    panneauDeJeu.removeCercle(cercle);
                    if (!cercle.estPresse()) {
                        joueur.recevoirDegats(ennemi.getAttaque());
                        degatSound.play(); // Jouer le bruitage
                        changeBarColorTemporarily(joueurVieBar, "red");
                    } else {
                        ennemi.recevoirDegats(joueur.getAttaque());
                        degatSound.play(); // Jouer le bruitage
                        changeBarColorTemporarily(ennemiVieBar, "red");
                    }
                }
            }
            for (CercleMobile cercle : cerclesARetirer) {
                cercles.remove(cercle);
            }
            updateVieBars();
        }));

        // Initialisation de la timeline pour gérer le jeu
        random = new Random();
        // 3*BPM / 60 -> tous les 3 temps il y a un cercle
        timeline = new Timeline(new KeyFrame(Duration.seconds(60.0 / (double) panneauDeJeu.getBPM()), event -> {
            int identifiant = random.nextInt(3);
            CercleMobile nouveauCercle = new CercleMobile(identifiant, difficulte);
            cercles.add(nouveauCercle);
            panneauDeJeu.addCercle(nouveauCercle);

            if (joueur.getVie() <= 0) {
                System.out.println("Le joueur a perdu !");
                timeline.stop();
                timelineAffichage.stop();
                lecteurMusique.arreter();
                panneauDeJeu.setEtatJeuFini();
                backgroundIm = new Image(getClass().getResource("/Elements/Defaite.png").toExternalForm());
                background = new BackgroundImage(backgroundIm,
                        BackgroundRepeat.NO_REPEAT,
                        BackgroundRepeat.NO_REPEAT,
                        BackgroundPosition.CENTER,
                        new BackgroundSize(1.0, 1.0, true, true, false, false));
                this.setBackground(new Background(background));
                label.setText("Vous avez perdu !");
                musiqueDefaite.play();

            }
            if (ennemi.getVie() <= 0) {
                System.out.println("Le joueur a gagné !");
                timeline.stop();
                timelineAffichage.stop();
                lecteurMusique.arreter();
                panneauDeJeu.setEtatJeuFini();
                backgroundIm = new Image(getClass().getResource("/Elements/Victoire.png").toExternalForm());
                background = new BackgroundImage(backgroundIm,
                        BackgroundRepeat.NO_REPEAT,
                        BackgroundRepeat.NO_REPEAT,
                        BackgroundPosition.CENTER,
                        new BackgroundSize(1.0, 1.0, true, true, false, false));
                this.setBackground(new Background(background));
                label.setText("Vous avez gagné !");
                musiqueVictoire.play();
            }
        }));
        Duration delai = Duration.millis(offSet);
        timelineAffichage.setCycleCount(Timeline.INDEFINITE);
        timelineAffichage.setDelay(delai);
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.setDelay(delai);

        if (numeroMusique == 2) {
            lecteurMusique.setVolume(lecteurMusique.getVolume() * 2);
        }
        lecteurMusique.chargementRapide(cheminMusique, true);
        timelineAffichage.play();
        timeline.play();
    }

    private void updateVieBars() {
        joueurVieBar.setProgress((double) joueur.getVie() / 100);
        ennemiVieBar.setProgress((double) ennemi.getVie() / 400);
    }

    private void changeBarColorTemporarily(ProgressBar bar, String color) {
        bar.setStyle("-fx-accent: " + color + ";");
        PauseTransition pause = new PauseTransition(Duration.seconds(0.5));
        pause.setOnFinished(event -> bar.setStyle("-fx-accent: green;"));
        pause.play();
    }

    public static void demarer(String niveau, Personnage joueur, int difficulte, int numeroMusique) {
        new PageJeu(niveau, joueur, difficulte, numeroMusique);
    }
}
