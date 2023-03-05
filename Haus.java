import java.awt.Point;
/**
 * Das Zielfeld, welches die Siegesbedingung darstellt
 * 
 * @author Mirko Feldmann
 */
public class Haus extends Feld
{
    private boolean letztesHaus = false;
    
    /**
     * Konstruktor des Hauses
     * 
     * @param Farbe des Feldes
     * @param Dateipfad des zu lesenden Bildes
     * @param Koordinaten des Feldes
     */
    public Haus(Farbe farbe, String dateiname, Point koordinaten)
    {
        super(farbe, dateiname, koordinaten);
    }
    
    /**
     * Konstruktor des Hauses
     * 
     * @param Farbe des Feldes
     * @param Dateipfad des zu lesenden Bildes
     * @param Ist es das letzte Hausfeld
     * @param Koordinaten des Feldes
     */
    public Haus(Farbe farbe, String dateiname, boolean letztesHaus, Point koordinaten)
    {
        super(farbe, dateiname, koordinaten);
        this.letztesHaus = letztesHaus;
    }
    
    /**
     * Gibt an, ob das Feld von Figuren begangen werden darf
     * 
     * @return Wahrheitswert
     */
    public boolean istBegebar(Farbe farbe)
    {
        if(farbe == this.farbe)
        {
            return true;
        }
        return false;
    }
    
    /**
     * Gibt an, ob es sich um das hinterste Haus handelt
     * 
     * @retrun Wahrheitswert
     */
    public boolean istLetztesHaus()
    {
        return letztesHaus; 
    }
}
