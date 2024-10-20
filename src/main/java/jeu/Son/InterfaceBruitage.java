package jeu.Son;

import java.io.IOException;
import javax.sound.sampled.*;

public class InterfaceBruitage {

        Son son;

        public InterfaceBruitage() {
            try {
                son = new Son();
            } catch (UnsupportedAudioFileException e){

            } catch (IOException e) {

            } catch (LineUnavailableException e) {
                
            }

            
        }

        /**
         * Jouer de la musique
         * @param i l'index de la musique (voir dossierSons)
         */
        public void jouerMusique(int i) {
            son.jouerMusique(i);
        }

        public void stopMusique(int i) {

            son.stop(i);
        }

        public void jouerMusiqueMenu(int i) {

            son.jouerMusique(i);
            son.boucle(i);
        }

        public void rejouer(int i) {

            son.rejouer(i);
        }

        public void fermerPiste(int i) {

            son.fermer(i);
        }


    }