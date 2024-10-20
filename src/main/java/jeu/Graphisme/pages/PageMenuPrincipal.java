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
import jeu.Entites.Personnage;
import jeu.Jeu;

public class PageMenuPrincipal extends VBox {
    static PageMenuPrincipal page = null;
    private Personnage joueur;

    private PageMenuPrincipal() {
        super(20); // Espacement entre les boutons
        this.setAlignment(Pos.CENTER);
        this.joueur = new Personnage("Joueur", 100, 10, 5); // Exemple de personnage du joueur

        DropShadow neon = new DropShadow();
        neon.setColor(Color.CYAN);
        neon.setRadius(10);
        neon.setSpread(0.5);

        Button boutonQuitter = new Button("Quitter");
        boutonQuitter.setStyle("-fx-background-color: transparent;" +
                "-fx-text-fill: #00ffff;" +
                "-fx-border-color: #00ffff;" +
                "-fx-border-radius: 5px;" +
                "-fx-border-width: 2px;" +
                "-fx-font-weight: bold;");
        boutonQuitter.setEffect(neon);

        Button boutonJouer = new Button("Jouer");
        boutonJouer.setStyle("-fx-background-color: transparent;" +
                "-fx-text-fill: #00ffff;" +
                "-fx-border-color: #00ffff;" +
                "-fx-border-radius: 5px;" +
                "-fx-border-width: 2px;" +
                "-fx-font-weight: bold;");
        boutonJouer.setEffect(neon);

        Button boutonParametres = new Button("Paramètres");
        boutonParametres.setStyle("-fx-background-color: transparent;" +
                "-fx-text-fill: #00ffff;" +
                "-fx-border-color: #00ffff;" +
                "-fx-border-radius: 5px;" +
                "-fx-border-width: 2px;" +
                "-fx-font-weight: bold;");
        boutonParametres.setEffect(neon);

        Button boutonAmelioration = new Button("Amélioration");
        boutonAmelioration.setStyle("-fx-background-color: transparent;" +
                "-fx-text-fill: #00ffff;" +
                "-fx-border-color: #00ffff;" +
                "-fx-border-radius: 5px;" +
                "-fx-border-width: 2px;" +
                "-fx-font-weight: bold;");
        boutonAmelioration.setEffect(neon);

        // Ajouter les actions aux boutons
        boutonQuitter.setOnAction(e -> System.exit(0));
        boutonJouer.setOnAction(e -> choisirNiveau());
        boutonParametres.setOnAction(e -> ouvrirParametres());
        boutonAmelioration.setOnAction(e -> PageAmelioration.demarer(joueur));

        // Ajouter les boutons au VBox
        this.getChildren().addAll(boutonJouer, boutonParametres, boutonAmelioration, boutonQuitter);

        Scene ecranMenuPrincipal = new Scene(this);
        Stage baseGraphique = Jeu.getBaseGraphique();
        baseGraphique.setScene(ecranMenuPrincipal);
        baseGraphique.setTitle("Menu Principal");

        // Bindings pour le redimensionnement des boutons
        boutonJouer.prefWidthProperty().bind(ecranMenuPrincipal.widthProperty().multiply(0.3));
        boutonJouer.prefHeightProperty().bind(ecranMenuPrincipal.heightProperty().multiply(0.1));
        boutonParametres.prefWidthProperty().bind(ecranMenuPrincipal.widthProperty().multiply(0.3));
        boutonParametres.prefHeightProperty().bind(ecranMenuPrincipal.heightProperty().multiply(0.1));
        boutonAmelioration.prefWidthProperty().bind(ecranMenuPrincipal.widthProperty().multiply(0.3));
        boutonAmelioration.prefHeightProperty().bind(ecranMenuPrincipal.heightProperty().multiply(0.1));
        boutonQuitter.prefWidthProperty().bind(ecranMenuPrincipal.widthProperty().multiply(0.3));
        boutonQuitter.prefHeightProperty().bind(ecranMenuPrincipal.heightProperty().multiply(0.1));

        // background
        Image backgroundIm = new Image(getClass().getResource("/Elements/arriere-plan-menu.jpg").toExternalForm());
        BackgroundImage background = new BackgroundImage(backgroundIm,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(1.0, 1.0, true, true, false, false));
        this.setBackground(new Background(background));
    }

    private void ouvrirParametres() {
        PageParametres.demarer();
    }

    private void choisirNiveau() {
        PageNiveau.demarer(joueur, Jeu.getDifficulte());
    }

    public static void demarer() {
        page = new PageMenuPrincipal();
    }

    public static void close() {
        throw new UnsupportedOperationException("Unimplemented method 'close'");
    }
}
