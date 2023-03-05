import java.util.HashMap;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * Hier werden alle verwendeten Bilder festgehalten, um unnötige Dateizugriffe zu minimieren 
 * 
 * @author Mirko Feldmann
 */
public class BildDictionary
{
    private static HashMap<String, BufferedImage> bilder = new HashMap<String, BufferedImage>();
    
    /**
     * Gibt die Bilddatei zu einem Dateipfad zurück
     *
     * @param Dateipfad des Bildes
     */
    public static BufferedImage getBild(String dateipfad)
    {
        if(bilder.containsKey(dateipfad))
        {
            return bilder.get(dateipfad);
        }
        return bildLesen(dateipfad);
    }
    
    /**
     * Ein neues Bild wird gelesen und dem Dictionary hinzugefügt
     * 
     * @param Dateipfad des Bildes
     */
    private static BufferedImage bildLesen(String dateipfad)
    {
        BufferedImage bild = null;
        
        try
        {
            bild = ImageIO.read(new File("resources/"+dateipfad));
            bilder.put(dateipfad, bild);
        }
        catch(IOException e)
        {
            System.out.println("Fehler beim Lesen einer Bilddatei: "+dateipfad);
        }
        return bild;
    }
}
