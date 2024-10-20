package jeu.Son;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.sound.sampled.*;

/**
 * Cette classe gere les bruitages et les musiques
 * Les fichiers sonores doivent etre des .wav et seront presents dans le dossierSons
 * Note : quand on utilise une piste sonore il faut un break
 * @author Lan
 * @version 1.2
 */
public class Son {
    
    // Le nombre de bruitages + la musique de fond
    final int NombreMusique = 3;

    // Tableau contenant les flux audios sortant
    // /!\ a tester, est ce que le meme bruitage peut se superposer
    Clip tabClip[] = new Clip[NombreMusique];

    /**
     *  Constuire un objet son permettant la gestion des bruitages / musiques
     * ( on pourrait creer un autre constructeur pour charger 2+ musiques )
     *
     * @param bgMusic le nom de la musique de fond que l'on veut jouer
     * @throws FileNotFoundException si le fichier audio n'est pas trouvé
     * @throws UnsupportedAudioFileException si le format audio n'est pas supporté
     * @throws IOException si une erreur d'entrée/sortie se produit
     * @throws LineUnavailableException si la ligne audio n'est pas disponible
     */

    public Son() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
  
  
        // les differents bruitages : menu ou inGame
        for (int index = 1; index < NombreMusique; index++) {
            File fichierBruitage = new File("Musique/Bruitage" + index + ".wav");
            AudioInputStream aisBruitage = AudioSystem.getAudioInputStream(fichierBruitage);
            tabClip[index] = AudioSystem.getClip();
            tabClip[index].open(aisBruitage);
            FloatControl gainControl = (FloatControl) 
            tabClip[index].getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(20.0206f);  // Augmenter le volume de 6 dB
        }
    }
    
    /**
     * Jouer la musique dans le flux numero i
     * 
     * @param i le numero du flux
     */
    public void jouerMusique(int i) {

        tabClip[i].start();
    }

    /**
     * Boucler la musique dans le flux numero i jusqu'a appel de la methode stop
     * 
     * @param i le numero du flux
     */
    public void boucle(int i) {

        tabClip[i].loop(Clip.LOOP_CONTINUOUSLY);
    }

    /**
     * Arreter la musique dans le flux numero i
     * 
     * @param i le numero du flux
     */
    public void stop(int i) {

        tabClip[i].stop();
    }

    /**
     * Rejoue la musique depuis le depart
     * 
     * @param i le numero du flux
     */
    public void rejouer(int i) {

        tabClip[i].setMicrosecondPosition(0);
    }

    /**
     * Ferme le flux i
     * 
     * @param i le numero du flux
     */
    public void fermer(int i) {

        tabClip[i].close();
    }

}

