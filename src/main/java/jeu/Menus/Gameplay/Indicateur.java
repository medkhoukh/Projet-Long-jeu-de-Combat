package jeu.Menus.Gameplay;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Indicateur {
    private Circle circle1;
    private Circle circle2;

    private Circle indROUGE;
    private Circle indORANGE;
    private Circle indVERT;

    private Circle invis;

    private int xPos;
    private int yPos;

    private final double rayon = 60;

    public Indicateur() {
        xPos = 100;
        yPos = 50;

        // Créer le cercle
        circle1 = new Circle(rayon, Color.TRANSPARENT);
        circle1.setStroke(Color.BLACK);
        circle1.setStrokeWidth(15);
        circle1.setCenterX(xPos);
        circle1.setCenterY(yPos);

        circle2 = new Circle(rayon - 10, Color.TRANSPARENT);
        circle2.setStroke(Color.TRANSPARENT);
        circle2.setStrokeWidth(12);
        circle2.setCenterX(xPos);
        circle2.setCenterY(yPos);

        invis = new Circle(rayon * 3, Color.TRANSPARENT);
        invis.setCenterX(xPos);
        invis.setCenterY(3*yPos);

        indROUGE = new Circle(rayon / 4, Color.RED);
        indROUGE.setCenterX(xPos - 60);
        indROUGE.setCenterY(yPos + 100);

        indORANGE = new Circle(rayon / 4, Color.ORANGE);
        indORANGE.setCenterX(xPos);
        indORANGE.setCenterY(yPos + 100);

        indVERT = new Circle(rayon / 4, Color.GREEN);
        indVERT.setCenterX(xPos + 60);
        indVERT.setCenterY(yPos + 100);
    }

    public void interaction(boolean pression, char entree) {
        if ( pression == true) {
            circle1.setStroke(Color.TRANSPARENT);
            switch (entree) {
                case 'Q':
                    circle2.setStroke(Color.RED);
                    break;
                case 'S':
                    circle2.setStroke(Color.ORANGE);
                    break;
                case 'D':
                    circle2.setStroke(Color.GREEN);
                    break;
            }
        } else {
            circle1.setStroke(Color.BLACK);
            circle2.setStroke(Color.TRANSPARENT);
        }
    }

    public Circle getCircle1() {
        return circle1;
    }

    public Circle getCircle2() {
        return circle2;
    }

    public Circle getIndRouge() {
        return indROUGE;
    }

    public Circle getIndVert() {
        return indVERT;
    }

    public Circle getIndOrange() {
        return indORANGE;
    }

    public Circle getInvis() {
        return invis;
    }

    public int getX() {
        return xPos;
    }

    public int getY() {
        return yPos;
    }

    public double getRayon() {
        return rayon;
    }
}
