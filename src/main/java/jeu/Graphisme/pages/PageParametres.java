package jeu.Graphisme.pages;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import jeu.Jeu;
import javafx.geometry.Pos;
import java.net.URL;
import javafx.scene.paint.Color;
import javafx.scene.effect.DropShadow;

public class PageParametres extends VBox {

    public PageParametres() {

        super(20);
        this.setAlignment(Pos.CENTER);

        DropShadow neon = new DropShadow();
        neon.setColor(Color.CYAN);
        neon.setRadius(10);
        neon.setSpread(0.5);

        int difficulte = Jeu.getDifficulte();
        Label labelDifficulte = new Label("Facile");
        labelDifficulte.setStyle("-fx-font-size: 24pt; -fx-font-weight: bold; -fx-text-fill: white; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.5), 0, 0, 0, 4);");
        switch (difficulte) {
            case 0:
                labelDifficulte.setText("Facile");
                break;
            case 1:
                labelDifficulte.setText("Intermédiaire");
                break;
            case 2:
                labelDifficulte.setText("Difficile");
                break;
        }
        this.getChildren().add(labelDifficulte);

        Button buttonFacile = new Button("Facile");
        buttonFacile.setMinSize(200, 50);
        buttonFacile.setOnAction(event -> {Jeu.setDifficulte(0); labelDifficulte.setText("Facile");});
        this.getChildren().add(buttonFacile);
        buttonFacile.setStyle("-fx-background-color : transparent;" +
                "-fx-text-fill : #00ffff;" +
                "-fx-border-color : #00ffff;" +
                "-fx-border-radius : 5px;" +
                "-fx-border-width : 2px;" +
                "-fx-font-weight : bold;");
        buttonFacile.setEffect(neon);

        Button buttonIntermediaire = new Button("Intermédiaire");
        buttonIntermediaire.setMinSize(200, 50);
        buttonIntermediaire.setOnAction(event -> {Jeu.setDifficulte(1); labelDifficulte.setText("Intermédiaire");});
        this.getChildren().add(buttonIntermediaire);
        buttonIntermediaire.setStyle("-fx-background-color : transparent;" +
                "-fx-text-fill : #00ffff;" +
                "-fx-border-color : #00ffff;" +
                "-fx-border-radius : 5px;" +
                "-fx-border-width : 2px;" +
                "-fx-font-weight : bold;");
        buttonIntermediaire.setEffect(neon);

        Button buttonDifficile = new Button("Difficile");
        buttonDifficile.setMinSize(200, 50);
        buttonDifficile.setOnAction(event -> {Jeu.setDifficulte(2); labelDifficulte.setText("Difficile");});
        this.getChildren().add(buttonDifficile);
        buttonDifficile.setStyle("-fx-background-color : transparent;" +
                "-fx-text-fill : #00ffff;" +
                "-fx-border-color : #00ffff;" +
                "-fx-border-radius : 5px;" +
                "-fx-border-width : 2px;" +
                "-fx-font-weight : bold;");
        buttonDifficile.setEffect(neon);

        HBox wrapSon = new HBox(30);

        Label son = new Label("Volume :");
        son.setStyle("-fx-background-color : transparent;" +
                "-fx-text-fill : #00ffff;" +
                "-fx-font-size : 25;" +
                "-fx-font-weight : bold;");
        son.setEffect(neon);
        wrapSon.getChildren().add(son);

        Slider sliderSon = new Slider(0, 100, 5);
        sliderSon.setStyle("-fx-background-color : transparent;" +
                "-fx-text-fill : #00ffff;" +
                "-fx-font-weight : bold;");
        sliderSon.setEffect(neon);
        sliderSon.setValue(Jeu.getLecteurAudio().getVolume()*100);
        // Ajouter la modification du paramètre dans le lecteur audio
        sliderSon.valueProperty().addListener((obs, oldVal, newVal) -> Jeu.getLecteurAudio().setVolume(newVal.doubleValue() / 100));
        wrapSon.getChildren().add(sliderSon);
        wrapSon.setAlignment(Pos.CENTER);
        this.getChildren().add(wrapSon);

        Button boutonRetour = new Button("Retour");
        boutonRetour.setStyle("-fx-background-color : transparent;" +
                "-fx-text-fill : #00ffff;" +
                "-fx-border-color : #00ffff;" +
                "-fx-border-radius : 5px;" +
                "-fx-border-width : 2px;" +
                "-fx-font-weight : bold;");
        boutonRetour.setEffect(neon);
        boutonRetour.setMinSize(200, 50);
        boutonRetour.setOnAction(e -> {PageMenuPrincipal.demarer(); Jeu.getLecteurAudio().setVolume(sliderSon.getValue()/100);});
        this.getChildren().add(boutonRetour);

        Scene sceneParametres = new Scene(this);
        Jeu.getBaseGraphique().setScene(sceneParametres);
        Jeu.getBaseGraphique().setTitle("Paramètres");

        // Binding pour le redimensionnement des boutons
        this.widthProperty().addListener((observable, oldValue, newValue) -> adjustButtonSizes(newValue.doubleValue(), this.getHeight()));
        this.heightProperty().addListener((observable, oldValue, newValue) -> adjustButtonSizes(this.getWidth(), newValue.doubleValue()));

        // Background
        String imagePath = "/Elements/arriere-plan-menu.jpg";
        URL imageURL = getClass().getResource(imagePath);
        Image backgroundIm = new Image(imageURL.toExternalForm());
        BackgroundImage background = new BackgroundImage(backgroundIm,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(1.0, 1.0, true, true, false, false));
        this.setBackground(new Background(background));
    }

    private void adjustButtonSizes(double width, double height) {
        for (javafx.scene.Node node : this.getChildren()) {
            if (node instanceof Button) {
                Button button = (Button) node;
                button.setMinSize(width * 0.3, height * 0.1);
                button.setMaxSize(width * 0.3, height * 0.1);
            } else if (node instanceof HBox) {
                HBox hbox = (HBox) node;
                for (javafx.scene.Node childNode : hbox.getChildren()) {
                    if (childNode instanceof Slider) {
                        Slider slider = (Slider) childNode;
                        slider.setPrefWidth(width * 0.5);
                    }
                }
            }
        }
    }

    public static void demarer() {
        new PageParametres();
    }
}
