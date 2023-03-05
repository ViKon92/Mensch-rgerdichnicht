import java.awt.Point;
/**
 * Darstellung eines Feldes, auf welchem sich die Figuren bewegen können
 * 
 * @author Mirko Feldmann
 */
public class Lauffeld extends Feld
{
    private boolean letztesLauffeld = false;
        
    /**
     * Konstruktor des Lauffeldes
     * 
     * @param Dateipfad des zu lesenden Bildes
     * @param Koordinaten des Feldes
     */
    public Lauffeld(String dateiname, Point koordinaten)
    {
        super(Farbe.KEINE, dateiname, koordinaten);
    }
    
    /**
     * Konstruktor des Lauffeldes
     * 
     * @param Dateipfad des zu lesenden Bildes
     * @param Ist dies das letzte Feld vor den Häusern
     * @param Koordinaten des Feldes
     */
    public Lauffeld(String dateiname, boolean letztesLauffeld, Point koordinaten)
    {
        super(Farbe.KEINE, dateiname, koordinaten);
        this.letztesLauffeld = letztesLauffeld;
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
    
    /**
     * Gibt an, ob es sich um das letzte Lauffeld vor den Haus-Feldern handelt
     * 
     * @retrun Wahrheitswert
     */
    public boolean istLetztesFeld()
    {
        return letztesLauffeld; 
    }
}
