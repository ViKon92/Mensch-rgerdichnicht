import java.awt.image.BufferedImage;
import java.awt.Point;
import java.awt.Graphics;

/**
 * Bündelt alle Informationen und Methoden, welche alle Felderarten gemeinsam haben
 * 
 * @author Mirko Feldmann
 */
public abstract class Feld implements IZeichenbar
{
    protected Farbe farbe;
    protected BufferedImage bild;
    protected Point koordinaten;
    
    /**
     * Konstruktor des Feldes
     * 
     * @param Farbe des Feldes
     * @param Dateipfad des zu lesenden Bildes
     */
    public Feld(Farbe farbe, String bildDateiPfad, Point koordinaten)
    {
        this.farbe = farbe;
        this.koordinaten = koordinaten;
        bild = BildDictionary.getBild(bildDateiPfad);
    }
    
    public void zeichnen(Graphics g, int offset)
    {
        g.drawImage(bild, getGrafischeXKoordinate(), getGrafischeYKoordinate(), null);
    }
    
    /**
     * Gibt an, ob das Feld von Figuren begangen werden darf
     * 
     * @return Wahrheitswert
     */
    public abstract boolean istBegebar(Farbe farbe);
    
    /**
     * Getter für die Farbe des Feldes
     * 
     * @return Farbe des Feldes
     */
    public Farbe getFarbe()
    {
        return farbe;
    }
    
    /**
     * Getter für die Koordinaten des Feldes
     * 
     * @return Koordinaten des Feldes
     */
    public Point getKoordinaten()
    {
        return koordinaten;
    }  
    
    /**
     * Getter für die grafische Koordinaten des Feldes
     * 
     * @return Grafische Koordinaten des Feldes
     */
    public int getGrafischeXKoordinate()
    {
        return koordinaten.y * GUI.getOffset();
    }  
    
    /**
     * Getter für die grafische Koordinaten des Feldes
     * 
     * @return Grafische Koordinaten des Feldes
     */
    public int getGrafischeYKoordinate()
    {
        return koordinaten.x * GUI.getOffset();
    }  
}
