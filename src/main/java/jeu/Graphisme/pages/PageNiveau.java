package jeu.Graphisme.pages;

import jeu.Entites.Personnage;
import jeu.Jeu;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.paint.Color;

public class PageNiveau extends VBox {
    public PageNiveau(Personnage joueur, int difficulte) {
        super(20);

        DropShadow neon = new DropShadow();
        neon.setColor(Color.CYAN);
        neon.setRadius(10);
        neon.setSpread(0.5);

        this.setAlignment(Pos.CENTER);
        // Liste de niveaux (exemple)
        String[] niveaux = { "Niveau 1", "Niveau 2", "Niveau 3", "Niveau 4" };
        for (int numeroMusique = 0; numeroMusique < 4; numeroMusique++) {
            String niveau = niveaux[numeroMusique];
            Button button = new Button(niveau);

            button.setMinSize(200, 50);
            button.setStyle("-fx-background-color : transparent;" +
                    "-fx-text-fill : #00ffff;" +
                    "-fx-border-color : #00ffff;" +
                    "-fx-border-radius : 5px;" +
                    "-fx-border-width : 2px;" +
                    "-fx-font-weight : bold;");
            button.setEffect(neon);

            final int numeroMusiqueFinal = numeroMusique;
            button.setOnAction(e -> PageJeu.demarer(niveau, joueur, difficulte, numeroMusiqueFinal));
            this.getChildren().add(button);
        }

        Button boutonRetour = new Button("Retour");
        boutonRetour.setStyle("-fx-background-color : transparent;" +
                "-fx-text-fill : #00ffff;" +
                "-fx-border-color : #00ffff;" +
                "-fx-border-radius : 5px;" +
                "-fx-border-width : 2px;" +
                "-fx-font-weight : bold;");
        boutonRetour.setEffect(neon);
        boutonRetour.setMinSize(200, 50);
        boutonRetour.setOnAction(e -> PageMenuPrincipal.demarer());
        this.getChildren().add(boutonRetour);

        Scene sceneNiveau = new Scene(this);
        sceneNiveau.widthProperty().addListener((observable, oldValue, newValue) -> adjustLayout(newValue.doubleValue(), sceneNiveau.getHeight()));
        sceneNiveau.heightProperty().addListener((observable, oldValue, newValue) -> adjustLayout(sceneNiveau.getWidth(), newValue.doubleValue()));

        Jeu.getBaseGraphique().setScene(sceneNiveau);
        Jeu.getBaseGraphique().setTitle("Choix de Niveau");

        // Background
        Image backgroundIm = new Image(getClass().getResource("/Elements/arriere-plan-menu.jpg").toExternalForm());
        BackgroundImage background = new BackgroundImage(backgroundIm,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(1.0, 1.0, true, true, false, false));
        this.setBackground(new Background(background));
    }

    private void adjustLayout(double width, double height) {
        for (javafx.scene.Node node : this.getChildren()) {
            if (node instanceof Button) {
                Button button = (Button) node;
                button.setMinSize(width * 0.25, height * 0.1);
                button.setMaxSize(width * 0.25, height * 0.1);
            }
        }
    }

    public static void demarer(Personnage joueur, int difficulte) {
        new PageNiveau(joueur, difficulte);
    }
}
