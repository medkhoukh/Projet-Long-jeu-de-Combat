package jeu.Menus.Gameplay;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.scene.text.Font;

public class CercleMobile extends StackPane{
    private Circle circle;
    private Text texte;

    private int xPos;
    private int yPos;

    private int id;

    private char toucheAssociee;

    private final double deltaX;

    private double rayon = 50;

    private boolean estPresse;

    private static final Color[] CouleurCercle = {Color.RED, Color.ORANGE, Color.GREEN};

    private static final char[] toucheValide = {'Q', 'S', 'D'};

    private static final int[] VITESSE_DEFILEMENT = {1, 2, 3};

    public CercleMobile(int identifiant, int difficulte) {
        super();
        xPos = 1100;
        yPos = 0;

        deltaX = - VITESSE_DEFILEMENT[difficulte];

        id = identifiant;

        estPresse = false;

        toucheAssociee = toucheValide[identifiant];
        // Créer le cercle
        circle = new Circle(rayon, CouleurCercle[identifiant]);
        
        circle.setStroke(CouleurCercle[identifiant].darker());
        circle.setStrokeWidth(15);

        texte = new Text(String.valueOf(toucheAssociee));
        texte.setFont(new Font(50));
        texte.setFill(Color.WHITE);

        this.setTranslateX(xPos);
        this.setTranslateY(yPos);
        this.getChildren().addAll(circle,texte);
    }

    public void update() {
        xPos += deltaX;
        this.setTranslateX(xPos);
        if (estPresse == true) {
            rayon = rayon - 1.0;
            circle.setRadius(rayon);
        }
    }

    public void valider() {
        estPresse = true;
    }

    public StackPane getCircle() {
        return this;
    }

    public char getTouche() {
        return toucheAssociee;
    }

    public int getX() {
        return xPos;
    }

    public int getY() {
        return yPos;
    }

    public Text getTexte() {
        return texte;
    }

    public double getRayon() {
        return rayon;
    }

    public boolean estPresse() {
        return estPresse;
    }

    public int getIdentifiant() {
        return id;
    }
}
