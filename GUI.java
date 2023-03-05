import javax.swing.*;
import java.awt.Graphics;
import java.util.ArrayList;
import java.awt.Point;

/**
 * Beschreiben Sie hier die Klasse GUI.
 * 
 * @author Mirko Feldmann
 */
public class GUI extends JFrame
{
    private static final int offset = 64;

    /**
     * Konstruktor für Objekte der Klasse GUI
     */
    public GUI()
    {
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setSize(offset * 12, offset * 12);
        this.setLayout(null);
    }

    /**
     * Diese Methode zeichnet das Spielfeld
     * !!! Die grafische Darstellung der X (Spalte) und Y-Achse (Reihe) unterscheidet sich von der Darstellung im Array[Reihe][Spalte] !!!
     * 
     * @param Das zu zeichnende Spielfeld
     */
    public void hintergrundZeichnen(ArrayList<ArrayList<Feld>> spielfeld)
    {        
        JPanel felderPanel = new JPanel() 
        {
            @Override
            protected void paintComponent(Graphics g)
            {
                super.paintComponent(g);
                
                for(ArrayList<Feld> felder : spielfeld)
                {
                    for(Feld f : felder)
                    { 
                        if(f != null)
                        {
                            f.zeichnen(g, offset);
                        }
                    }
                }
            }
        };
        felderPanel.setSize(offset * 11, offset * 11);
        
        this.add(felderPanel);
        this.setVisible(true);
    }
    
    public static int getOffset()
    {
        return offset;
    }
}
