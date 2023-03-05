import java.util.ArrayList;
import java.util.HashMap;
import java.io.*;
import java.awt.Point;
import java.util.Observer;
import java.util.Observable;

/**
 * Beschreiben Sie hier die Klasse Spielfeld.
 * 
 * @author Mirko Feldmann
 */
public class Spielfeld implements Observer
{
    private GUI gui = new GUI();
    private Routenplaner routenplaner;
    
    private ArrayList<Figur> figuren = new ArrayList<Figur>();
    private ArrayList<ArrayList<Feld>> spielfeld = new ArrayList<ArrayList<Feld>>();
    private HashMap<String, ArrayList<Feld>> felderPerArt = new HashMap<String, ArrayList<Feld>>();
    
    public Spielfeld()
    {
        try
        {
            spielfeldErstellen("resources/Spielfeld/Spielbrett.txt");
            routenplaner = new Routenplaner(spielfeld, felderPerArt);
            gui.hintergrundZeichnen(spielfeld);
        }
        catch(Exception e)
        {
            System.out.println("Unbehandelter Fehler!");
        }
    }
    
    @Override
    public void update(Observable o, Object news) 
    {
        Figur figur = (Figur)o;
        Point zielPosition = routenplaner.getRouten(figur.getFarbe()).get(figur.getFeldIndex());
        if(!figuren.stream().anyMatch(f -> f.getPosition().y == zielPosition.x * gui.getOffset() && f.getPosition().x == zielPosition.y * gui.getOffset()))
        {
            figur.bewegenAuf(routenplaner.getRouten(figur.getFarbe()).get(figur.getFeldIndex()));
            figur.setFeldIndex(figur.getFeldIndex() + 1);
        }
    }
    
    /**
     * Initialisierung der zweidimensionalen ArrayList mit Objekten passend zu dem Spielfeldstring
     * 
     * @param Pfad der Datei
     */
    public void spielfeldErstellen(String pfad) 
    {
        int x = 0, y = 0;
        spielfeld.add(new ArrayList<Feld>());
        
        for(String feldstring : spielfeldLesen(pfad).split(" "))
        {               
            if(feldstring.contains(System.getProperty("line.separator"))) //Neue Zeile
            {
                y++;
                x = 0;
                spielfeld.add(new ArrayList<Feld>());
            }
            
            Feld feld = FeldFactory.erzeugeFeld(feldstring.replace(System.getProperty("line.separator"), ""), new Point(y,x));
            
            if(feld instanceof Startposition)
            {
                Figur figur = FigurFactory.erzeugeFigur(feld.getFarbe(), new Point(feld.getGrafischeXKoordinate(), feld.getGrafischeYKoordinate()));
                figur.addObserver(this);
                figur.zeichnen(gui);
                figuren.add(figur);
            }
            
            x++;           
            spielfeld.get(spielfeld.size()-1).add(feld);
            felderPerArt.computeIfAbsent(feld.getClass().getSimpleName(), list -> new ArrayList<Feld>()).add(feld);   
        }
    }
    
    /**
     * Liest eine Textdatei und erzeugt einen String der das Spielfeld beschreibt
     * 
     * @param Pfad der Datei
     * @return Spielfeldzeichenkette
     */
    public String spielfeldLesen(String pfad)
    {
        String gelesenerString;
        
        try(BufferedReader br = new BufferedReader(new FileReader(pfad))) 
        {
            StringBuilder sb = new StringBuilder();
            gelesenerString = br.readLine();
            
            while (gelesenerString != null) 
            {
                if (!gelesenerString.startsWith("/"))
                {
                    sb.append(gelesenerString+System.getProperty("line.separator"));
                }
                gelesenerString = br.readLine();
            }
            return sb.toString().trim();
        } 
        catch(IOException e)
        {
            System.out.println("Dateipfad zum Spielfeld fehlerhaft!");
        }
        return null;
    }
}
