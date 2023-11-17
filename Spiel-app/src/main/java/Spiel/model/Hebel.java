package Spiel.model;

/**
 * Diese Klasse steht für einen Hebel im Spiel.
 * Ein Hebel welcher in einem Raum steht und vom Spieler eingeschalten werden kann.
 * 
 * @author Leon Retzlaff, Alexander Beyer, Marwin Dietrich
 */

public class Hebel {
    /**
     * Zustand des Hebels, am Anfang ausgeschaltet
     */
    boolean zustand = false;

    /**
     * Konstruktor
     */
    public Hebel() {

    }

    /**
     *
     * @return Zustand des Hebels
     */
    public  boolean getZustand(){
        return zustand;
    }


    /**
     * schaltet den Hebel ein
     */
    public void einschalten(){
        zustand = true;
    }
}
