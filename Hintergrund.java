import java.awt.Point;
/**
 * Leeres Feld zur Darstellung des Hintergrundes
 * 
 * @author Mirko Feldmann
 */
public class Hintergrund extends Feld
{
    /**
     * Konstruktor des Hintergrundfeldes
     * 
     * @param Dateipfad des zu lesenden Bildes
     * @param Koordinaten des Feldes
     */
    public Hintergrund(String dateiname, Point koordinaten)
    {
        super(Farbe.KEINE, dateiname, koordinaten);
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
