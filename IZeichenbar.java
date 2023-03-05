import java.awt.image.BufferedImage;
import java.awt.Graphics;

/**
 * Alle Klassen, welche statisch auf der GUI dargestellt werden sollen, sollten dieses Interfaces implementieren
 * 
 * @author Mirko Feldmann
 */
public interface IZeichenbar
{
    /**
     * Hier sollten fürs Zeichnen benötigten Eigenschaften definiert werden
     */
    public void zeichnen(Graphics g, int offset);
}
