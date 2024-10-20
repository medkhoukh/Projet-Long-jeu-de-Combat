package jeu.Graphisme.pages;

import javafx.beans.binding.Bindings;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import jeu.Jeu;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import jeu.Son.*;

public class PageCredits extends VBox {
    static PageCredits page = null;

    private LecteurAudio lecteurMusique;

    private PageCredits() {
        super(20);
        DropShadow neon = new DropShadow();
        neon.setColor(Color.CYAN);
        neon.setRadius(10);
        neon.setSpread(0.5);
        this.setAlignment(Pos.CENTER);

        this.lecteurMusique = Jeu.getLecteurAudio();
        lecteurMusique.chargementRapide("/Musique/Credits.mp3", false);

        Label AG = createLabel("Alexandre Le Gall", neon);
        Label AV = createLabel("Alexandre Vanicotte", neon);
        Label LK = createLabel("Lan Kerlau", neon);
        Label LR = createLabel("Loïs Ruffini", neon);
        Label MK = createLabel("Mohamed Khoukh", neon);
        Label YA = createLabel("Yahia Ait Alla", neon);

        Button retour = new Button("Retour");
        retour.setMinSize(200, 50);
        retour.setOnAction(e -> {PageDemarage.demarer(); lecteurMusique.arreter();});
        retour.setStyle("-fx-background-color: transparent; -fx-text-fill: #00ffff; -fx-border-color: #00ffff; -fx-border-radius: 5px; -fx-border-width: 2px; -fx-font-weight: bold;");
        retour.setEffect(neon);

        this.getChildren().addAll(AG, AV, LK, LR, MK, YA, retour);
        Image backgroundIm = new Image(getClass().getResource("/Elements/arriere-plan-menu.jpg").toExternalForm());
        BackgroundImage background = new BackgroundImage(backgroundIm,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(1.0, 1.0, true, true, false, false));
        this.setBackground(new Background(background));

        Scene sceneParametres = new Scene(this);
        Jeu.getBaseGraphique().setScene(sceneParametres);
        Jeu.getBaseGraphique().setTitle("Crédits");

        // Bindings for responsive design
        bindResponsiveElements(sceneParametres, AG, AV, LK, LR, MK, YA);
        bindResponsiveButton(sceneParametres, retour);
    }

    private Label createLabel(String text, DropShadow neon) {
        Label label = new Label(text);
        label.setStyle("-fx-background-color: transparent; -fx-text-fill: #00ffff; -fx-font-weight: bold;");
        label.setEffect(neon);
        return label;
    }

    private void bindResponsiveElements(Scene scene, Label... labels) {
        for (Label label : labels) {
            label.styleProperty().bind(Bindings.concat("-fx-font-size: ", scene.widthProperty().multiply(0.03), "px; -fx-text-fill: #00ffff; -fx-font-weight: bold;"));
        }
    }

    private void bindResponsiveButton(Scene scene, Button button) {
        button.styleProperty().bind(Bindings.concat("-fx-font-size: ", scene.widthProperty().multiply(0.03), "px; -fx-text-fill: #00ffff; -fx-border-color: #00ffff; -fx-border-radius: 5px; -fx-border-width: 2px; -fx-font-weight: bold;"));
    }

    public static void demarer() {
        new PageCredits();
    }
}
