package jeu.Graphisme.pages;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import jeu.Jeu;

public class PageDemarage extends VBox {

    static PageDemarage page = null;

    private PageDemarage() {
        super(20);

        DropShadow neon = new DropShadow();
        neon.setColor(Color.CYAN);
        neon.setRadius(10);
        neon.setSpread(0.5);

        // on place les elements
        Button boutonDemarer = new Button("démarer");
        boutonDemarer.setOnMouseClicked(event -> actionBoutonDemarer());
        boutonDemarer.setMinSize(200, 50);
        boutonDemarer.setStyle("-fx-background-color: transparent;" +
                "-fx-text-fill: #00ffff;" +
                "-fx-border-color: #00ffff;" +
                "-fx-border-radius: 5px;" +
                "-fx-border-width: 2px;" +
                "-fx-font-weight: bold;");
        boutonDemarer.setEffect(neon);

        Button boutonCredits = new Button("crédits");
        boutonCredits.setOnMouseClicked(event -> PageCredits.demarer());
        boutonCredits.setMinSize(200, 50);
        boutonCredits.setStyle("-fx-background-color: transparent;" +
                "-fx-text-fill: #00ffff;" +
                "-fx-border-color: #00ffff;" +
                "-fx-border-radius: 5px;" +
                "-fx-border-width: 2px;" +
                "-fx-font-weight: bold;");
        boutonCredits.setEffect(neon);

        this.setAlignment(Pos.CENTER);
        this.getChildren().addAll(boutonDemarer, boutonCredits);

        // on paramettre la fenetre de demarage
        Scene ecranDemarage = new Scene(this);
        Stage baseGraphique = Jeu.getBaseGraphique();
        baseGraphique.setScene(ecranDemarage);
        baseGraphique.setTitle("Bienvenue");
        baseGraphique.setWidth(640);
        baseGraphique.setHeight(480);

        // Bindings pour le redimensionnement des boutons
        boutonDemarer.prefWidthProperty().bind(ecranDemarage.widthProperty().multiply(0.3));
        boutonDemarer.prefHeightProperty().bind(ecranDemarage.heightProperty().multiply(0.1));
        boutonCredits.prefWidthProperty().bind(ecranDemarage.widthProperty().multiply(0.3));
        boutonCredits.prefHeightProperty().bind(ecranDemarage.heightProperty().multiply(0.1));

        // background
        Image backgroundIm = new Image(getClass().getResource("/Elements/arriere-plan-menu.jpg").toExternalForm());
        BackgroundImage background = new BackgroundImage(backgroundIm,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(1.0, 1.0, true, true, false, false));
        this.setBackground(new Background(background));
    }

    public static void demarer() {
        page = new PageDemarage();
    }

    public static void close() {
        throw new UnsupportedOperationException("Unimplemented method 'close'");
    }

    private void actionBoutonDemarer() {
        PageMenuPrincipal.demarer();
    }
}
