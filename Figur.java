import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Observable;
import javax.swing.JFrame;
import java.awt.Point;

/**
 * Diese Klasse stellt eine Spielfigur da
 * 
 * @author Mirko Feldmann
 */
public class Figur extends Observable implements IKlickbar, ActionListener
{
    private JButton klickbaresBild = new JButton();
    private Point startposition;
    private int feldIndex = 0;
    private Farbe farbe;
        
    /**
     * Konstruktor der Figur
     * 
     * @param Der Dateipfad zur Bilddatei
     * @param Die Farbe der Figur
     * @param Die Startposition der Figur
     */
    public Figur(String bildPfad, Farbe farbe, Point startposition)
    {
        this.farbe = farbe;
        this.startposition = startposition;
        buttonImplementieren(bildPfad, startposition);
    }
    
    /**
     * Methode zum Bewegen der Figur
     * 
     * @param Der Punkt, zu welchem sich die Figur bewegen soll
     * @param Das benötigte Offset
     */
    public void bewegenAuf(Point p)
    {
        klickbaresBild.setLocation(p.y * GUI.getOffset(), p.x * GUI.getOffset());
    }
    
    /**
     * Implementierung des Buttons 
     * 
     * @param Dateipfad des Bildes
     * @param Der Startpunkt der Figur
     */
    public void buttonImplementieren(String dateipfad, Point startposition)
    {
        klickbaresBild.setIcon(new ImageIcon(BildDictionary.getBild(dateipfad)));
        klickbaresBild.addActionListener(this);
        klickbaresBild.setSize(64, 64);
        klickbaresBild.setLocation(startposition);
        klickbaresBild.setVisible(true);
    }
    
    /**
     * Hinzufügen des Buttons zum Frame
     * 
     * @param Das JFrame, welches den Button beinhaltet
     */
    public void zeichnen(JFrame frame)
    {
        frame.add(klickbaresBild);
    }
    
    /**
     * Das Klickevent des Buttons
     * 
     * @param Das ActionEvent
     */
    public void actionPerformed(ActionEvent e)
    {
        this.setChanged();
        this.notifyObservers();
    }
    
    /**
     * Getter für die Position der Figur
     * 
     * @return Punkt der Figur
     */
    public Point getPosition()
    {
        return klickbaresBild.getLocation();
    }
    
    /**
     * Getter für die Farbe der Figur
     * 
     * @return Farbe der Figur
     */
    public Farbe getFarbe()
    {
        return farbe;
    }
    
    /**
     * Getter für den Index der Figurroute
     * 
     * @return Farbe der Figur
     */
    public int getFeldIndex()
    {
        return feldIndex;
    }
    
    /**
     * Setter für den Index der Figurroute
     * 
     * @param Neuer Index
     */
    public void setFeldIndex(int index)
    {
        feldIndex = index;
    }
}
