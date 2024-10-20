package jeu.Son;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;

public final class LecteurAudio {
    private static final Logger journal = LoggerFactory.getLogger(LecteurAudio.class);
    private MediaPlayer lecteur;
    private double volume;
    private double rythme;
    private double tolérance;
    private boolean dansLeRythme;
    private int points;

    public LecteurAudio(float volume, double rythme, double tolérance) {
        this.volume = volume;
        this.rythme = rythme;
        this.tolérance = tolérance;
        this.points = 0;
    }

    public void charger(String chemin) {
        URL resource = getClass().getResource(chemin);
        if (resource == null) {
            System.err.println("Le fichier audio n'a pas été trouvé: " + chemin);
            return;
        }
        Media media = new Media(resource.toString());
        charger(media);
        journal.info("Piste changée : " + chemin + ", volume : " + volume + ".");
    }

    public void charger(Media media) {
        if (lecteur != null) {
            lecteur.stop();
            lecteur.dispose();
        }
        lecteur = new MediaPlayer(media);
        lecteur.setVolume(volume);
        lecteur.setOnStopped(lecteur::dispose);
        lecteur.setOnEndOfMedia(lecteur::dispose);
        journal.info("Piste changée : " + media.getSource() + ", volume : " + volume);
    }

    public void chargementRapide(String chemin, boolean boucle) {
        arreter();
        charger(chemin);
        if (lecteur != null) {
            if (boucle) {
                boucle();
            }
            jouer();
        }
    }

    public void jouer() {
        if (lecteur != null) {
            lecteur.setOnReady(() -> {
                lecteur.play();
                commencerDétectionRythme();
            });
        }
    }

    public void lire(String identifiant) {
        if (lecteur == null) {
            return;
        }
        if (identifiant.equals("coup-de-piste")) {
            return;
        }
        if (dansLeRythme) {
            points++;
        }
    }

    public void commencerDétectionRythme() {
        dansLeRythme = false;
        if (lecteur != null) {
            lecteur.setOnPlaying(() -> {
                new Thread(() -> {
                    while (lecteur.getStatus() == MediaPlayer.Status.PLAYING) {
                        double tempsActuel = lecteur.getCurrentTime().toMillis() % rythme;
                        if (tempsActuel < tolérance || rythme - tempsActuel < tolérance) {
                            dansLeRythme = true;
                        } else {
                            dansLeRythme = false;
                        }
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            });
        }
    }

    public void pause() {
        if (lecteur != null) {
            lecteur.pause();
        }
    }

    public void reprendre() {
        if (lecteur != null) {
            lecteur.play();
        }
    }

    public void arreter() {
        if (lecteur != null) {
            lecteur.stop();
            lecteur.dispose();
        }
    }

    public void boucle() {
        if (lecteur != null) {
            lecteur.setOnEndOfMedia(() -> lecteur.seek(Duration.millis(0)));
        }
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
        if (lecteur != null) {
            lecteur.setVolume(volume);
        }
    }

    public int getPoints() {
        return points;
    }
}
