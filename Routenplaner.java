import java.util.ArrayList;
import java.awt.Point;
import java.util.HashMap;

/**
 * Der Routenplaner erzeugt zu jeder Farbe eine Liste von Koordinaten, welche die Reihenfolge der abzulaufenden Felder beschreibt
 * 
 * @author Mirko Feldmann
 */
public class Routenplaner
{
    private int anzahlSchritte;
    private HashMap<Farbe, ArrayList<Point>> routen = new HashMap<Farbe, ArrayList<Point>>();
    
    /**
     * Konstruktor, welcher zu einem Spielfeld die Route pro Farbe berechnet 
     * 
     *  @param Das Spielfeld als 2D-ArrayList
     *  @param Die Auflistung der Felder nach Klasse
     */
    public Routenplaner(ArrayList<ArrayList<Feld>> spielfeld, HashMap<String, ArrayList<Feld>> felderPerArt)
    {
        anzahlSchritte = getAnzahlSchritte(felderPerArt);
        routenPlanen(spielfeld, felderPerArt);
    }
    
    /**
     * Getter für die ermittelte Route
     * 
     * @param Die Farbe für welche die Route geholt werden soll
     */
    public ArrayList<Point> getRouten(Farbe farbe)
    {
        return routen.get(farbe);
    }
    
    /**
     * Startet die Ermittlung der Routen für alle vorhandenen Farben
     *
     *  @param Das Spielfeld als 2D-ArrayList
     *  @param Die Auflistung der Felder nach Klasse
     */
    private void routenPlanen(ArrayList<ArrayList<Feld>> spielfeld, HashMap<String, ArrayList<Feld>> felderPerArt)
    {
        for(Farbe farbe : Farbe.values())
        {
            if(farbe != Farbe.KEINE)
            {
                routen.put(farbe, routeErmitteln(spielfeld, 
                                                 findeStartfeld(felderPerArt, farbe),
                                                 findeZielfeld(felderPerArt, farbe)));
            }
        }
    }
    
    /**
     * Beginnt die rekursive Ermitllung der Route
     * 
     *  @param Das Spielfeld als 2D-ArrayList
     *  @param Das Startfeld der Route
     *  @param Das Zeilfeld der Route
     */
    private ArrayList<Point> routeErmitteln(ArrayList<ArrayList<Feld>> spielfeld, Feld startfeld, Feld zielfeld)
    {
        ArrayList<Point> loesung = new ArrayList<Point>();
        routeFinden(spielfeld, loesung, startfeld.koordinaten, startfeld.farbe, zielfeld.koordinaten);
        
        return loesung;
    }
    
    /**
     * Rekursive Ermittlung der Route
     * 
     *  @param Das Spielfeld als 2D-ArrayList
     *  @param Die ermittelte Route
     *  @param Die aktuellen Koordinaten
     *  @param Die Farbe für welche die Route ermittelt wird
     *  @param Die Zielkoordniaten der Route
     */
    private boolean routeFinden(ArrayList<ArrayList<Feld>> spielfeld, ArrayList<Point> route, Point startpunkt, Farbe startfarbe, Point endpunkt)
    {
        if(!feldBetretbar(spielfeld, route, startpunkt, startfarbe))
        {
            return false;
        }
        
        if(startpunkt.x == endpunkt.x && startpunkt.y == endpunkt.y)
        {
            return true;
        }
        
        Point links = new Point(startpunkt.x-1,startpunkt.y);
        Point rechts = new Point(startpunkt.x+1,startpunkt.y);
        Point oben = new Point(startpunkt.x,startpunkt.y-1);
        Point unten = new Point(startpunkt.x,startpunkt.y+1);
        
        if(routeFinden(spielfeld, route, links, startfarbe, endpunkt)) //links
        {
            return true;
        }
        
        if(routeFinden(spielfeld, route, rechts, startfarbe, endpunkt)) //rechts
        {
            return true;
        }
        
        if(routeFinden(spielfeld, route, oben, startfarbe, endpunkt)) //oben
        {
            return true;
        }
        
        if(routeFinden(spielfeld, route, unten, startfarbe, endpunkt)) //unten
        {
            return true;
        }
        
        route.remove(startpunkt);
        return false;
    }
    
    /**
     * Prüft ob ein Feld an einer Position betreten werden darf
     * 
     * Das Regelwerk erfolgt in folgenden Schritten:
     *  1. Das Feld muss Figuren dieser Farbe erlauben
     *  2. Das Feld muss das erste Mal betreten werden
     *  3. Häuser dürfen erst nachdem alle anderen Felder besucht worden sind betreten werden
     *  opt. 4. Der Eintritt ins Haus muss von dem letzten Lauffeld erfolgen
     *  
     *  @param Das Spielfeld als 2D-ArrayList
     *  @param Die ermittelte Route
     *  @param Die Koordinaten des Feldes, welches betreten werden soll
     *  @param Die Farbe, welche versucht das Feld zu betreten
     */
    private boolean feldBetretbar(ArrayList<ArrayList<Feld>> spielfeld, ArrayList<Point> route, Point zuBetretenePosition, Farbe erlaubteFarbe)
    {
        try
        {
            Feld zuBetretenesFeld = spielfeld.get(zuBetretenePosition.x).get(zuBetretenePosition.y);
            
            if(zuBetretenesFeld.istBegebar(erlaubteFarbe) && !route.stream().anyMatch(p -> p.x == zuBetretenePosition.x && p.y == zuBetretenePosition.y))
            {
                if(zuBetretenesFeld instanceof Haus && route.size() >= anzahlSchritte)
                {
                    if(getAktuellesFeld(spielfeld, route.get(route.size()-1)) instanceof Haus
                       || (getAktuellesFeld(spielfeld, route.get(route.size()-1)) instanceof Lauffeld && ((Lauffeld)getAktuellesFeld(spielfeld, route.get(route.size()-1))).istLetztesFeld())) //Ich darf nur ins Haus wenn das aktuelle Feld ein Haus oder das letzte Lauffeld ist
                    {
                        route.add(zuBetretenePosition);
                        return true;
                    }
                }
                else if(!(zuBetretenesFeld instanceof Haus))
                {
                     route.add(zuBetretenePosition);
                    return true;
                }
            }
            
            return false;
        }
        catch(Exception e)
        {
            return false;
        }
    }
    
    /**
     * Ermittelt zu einer Farbe das Startfeld
     * !!! Nur das erste Startfeld wird berücksichtigt !!!
     * 
     * @param HashMap "felderPerArt", welche eine Auflistung der Felder nach Klasse beinhaltet
     * @param Die Farbe für der das Startfeld ermittelt werden soll
     */
    private Feld findeStartfeld(HashMap<String, ArrayList<Feld>> felderPerArt, Farbe farbe)
    {
        return felderPerArt.get("Startfeld").stream().filter(p -> p.getFarbe() == farbe).findFirst().orElse(null);
    }
    
    /**
     * Ermittelt zu einer Farbe das letzte Haus
     * 
     * @param HashMap "felderPerArt", welche eine Auflistung der Felder nach Klasse beinhaltet
     * @param Die Farbe für der das Zielhaus ermittelt werden soll
     */
    private Feld findeZielfeld(HashMap<String, ArrayList<Feld>> felderPerArt, Farbe farbe)
    {
        return felderPerArt.get("Haus").stream().filter(p -> ((Haus)p).istLetztesHaus() && p.getFarbe() == farbe).findFirst().orElse(null);
    }
    
    /**
     * Ermittelt das Feld zu einer bestimmten Koordinate 
     * 
     * @param Das Spielfeld als 2D-ArrayList
     * @param Die Koordinate des Feldes
     */
    private Feld getAktuellesFeld(ArrayList<ArrayList<Feld>> spielfeld, Point letztesFeld)
    {
        return spielfeld.get(letztesFeld.x).get(letztesFeld.y);
    }
    
    /**
     * Ermittelt die Anzahl der Felder, welche durchlaufen werden müssen um ins Haus zu dürfen
     * 
     * @param HashMap "felderPerArt", welche eine Auflistung der Felder nach Klasse beinhaltet
     */
    private int getAnzahlSchritte(HashMap<String, ArrayList<Feld>> felderPerArt)
    {
        return felderPerArt.get("Lauffeld").size() + felderPerArt.get("Startfeld").size();
    }
}
