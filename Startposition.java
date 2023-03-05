import java.awt.Point;
/**
 * Hier befinden sich die Figuren zu Beginn des Spieles
 * 
 * @author Mirko Feldmann
 */
public class Startposition extends Feld
{
    /**
     * Konstruktor der Startposition
     * 
     * @param Farbe des Feldes
     * @param Dateipfad des zu lesenden Bildes
     * @param Koordinaten des Feldes
     */
    public Startposition(Farbe farbe, String dateiname, Point koordinaten)
    {
        super(farbe, dateiname, koordinaten);
    }
    
    /**
     * Gibt an, ob das Feld von Figuren begangen werden darf
     * 
     * @return Wahrheitswert
     */
    public boolean istBegebar(Farbe farbe)
    {
        return false;
    }
}
