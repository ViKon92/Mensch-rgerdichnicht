import java.awt.Point;
/**
 * Nachdem eine Sechs gewürfelt wurde wird eine Figur aus der Statposition auf dieses Feld gesetzt
 * 
 * @author Mirko Feldmann
 */
public class Startfeld extends Feld
{
    /**
     * Konstruktor des Startfeldes
     * 
     * @param Farbe des Feldes
     * @param Dateipfad des zu lesenden Bildes
     * @param Koordinaten des Feldes
     */
    public Startfeld(Farbe farbe, String dateiname, Point koordinaten)
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
        return true;
    }
}
