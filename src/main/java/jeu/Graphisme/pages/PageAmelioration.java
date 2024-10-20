package jeu.Graphisme.pages;

import jeu.ObjetsCombats.*;
import jeu.Entites.Personnage;
import jeu.Jeu;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.layout.StackPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.util.ArrayList;
import java.net.URL;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;

public class PageAmelioration extends VBox {
    private HBox wrapObjets = new HBox(20);
    private Button currentAttaque;
    private Button currentDefense;

    public PageAmelioration(Personnage joueur) {
        super(20);

        DropShadow neon = new DropShadow();
        neon.setColor(Color.CYAN);
        neon.setRadius(10);
        neon.setSpread(0.5);
        this.setAlignment(Pos.CENTER);

        ObjetsCombatInit objetsInit = new ObjetsCombatInit();
        ArrayList<ObjetsCombat> objets = objetsInit.getObjs();

        Scene sceneAmelioration = new Scene(this);

        for (int i = 0; i < objetsInit.getTaille(); i++) {
            int index = i;
            Button button = new Button();
            button.setStyle("-fx-background-color : purple");
            button.setOnAction(event -> {
                joueur.appliquerAmelioration(objets.get(index));
                if (objets.get(index).getTypeAttaque()) {
                    if (currentAttaque != null) {
                        currentAttaque.setStyle("-fx-background-color : purple");
                    }
                    button.setStyle("-fx-background-color : blue");
                    this.currentAttaque = button;
                }
                if (!objets.get(index).getTypeAttaque()) {
                    if (currentDefense != null) {
                        currentDefense.setStyle("-fx-background-color : purple");
                    }
                    button.setStyle("-fx-background-color : blue");
                    this.currentDefense = button;
                }
            });
            String imagePath = "/Objets/" + objets.get(index).getImPath();
            URL imageURL = getClass().getResource(imagePath);
            if (imageURL == null) {
                System.err.println("Ressource non trouvée : " + imagePath);
                continue;
            }
            Image image = new Image(imageURL.toExternalForm());
            ImageView imageView = new ImageView(image);
            imageView.setPreserveRatio(true);

            StackPane stack = new StackPane();
            Label label = new Label("+ " + objets.get(index).getBonus() + (objets.get(index).getTypeAttaque() ? " atk" : " def"));
            label.setStyle("-fx-font-weight: bold; -fx-font-size: 16pt;");
            stack.getChildren().addAll(imageView, label);
            button.setGraphic(stack);
            wrapObjets.getChildren().add(button);

            // Bindings for responsive design
            imageView.fitWidthProperty().bind(sceneAmelioration.widthProperty().multiply(0.1));
        }

        wrapObjets.setAlignment(Pos.CENTER);
        this.getChildren().add(wrapObjets);

        Button boutonRetour = new Button("Retour");
        boutonRetour.setMinSize(200, 50);
        boutonRetour.setOnAction(e -> PageMenuPrincipal.demarer());
        this.getChildren().add(boutonRetour);
        boutonRetour.setStyle("-fx-background-color : transparent;" +
                "-fx-text-fill : #00ffff;" +
                "-fx-border-color : #00ffff;" +
                "-fx-border-radius : 5px;" +
                "-fx-border-width : 2px;" +
                "-fx-font-weight : bold;");
        boutonRetour.setEffect(neon);

        Image backgroundIm = new Image(getClass().getResource("/Elements/arriere-plan-menu.jpg").toExternalForm());
        BackgroundImage background = new BackgroundImage(backgroundIm,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(1.0, 1.0, true, true, false, false));
        this.setBackground(new Background(background));

        Jeu.getBaseGraphique().setScene(sceneAmelioration);
        Jeu.getBaseGraphique().setTitle("Amélioration");

        // Bindings for responsive design
        boutonRetour.prefWidthProperty().bind(sceneAmelioration.widthProperty().multiply(0.25));
        boutonRetour.prefHeightProperty().bind(sceneAmelioration.heightProperty().multiply(0.1));
    }

    public static void demarer(Personnage joueur) {
        new PageAmelioration(joueur);
    }
}
