import javax.swing.JFrame;
import java.awt.event.ActionEvent;
import java.awt.Point;

/**
 * Alle auf der GUI anklickbaren Elemente sollten dieses Interface implementieren
 * 
 * @author Mirko Feldmann
 */

public interface IKlickbar
{
    /**
     * In dieser Methode sollten alle Eigenschaften des JButtons definiert werden
     */
    public void buttonImplementieren(String dateipfadBild, Point position);
    
    /**
     * Hier sollte der Button einem JFrame hinzugefügt werden
     */
    public void zeichnen(JFrame frame);
}
