import java.awt.Point;

/**
 * Erzeugt zu einem Identifizierungszeichen ein Feld
 * 
 * @author André Hüsing
 */
public class FeldFactory
{
    public static Feld erzeugeFeld(String identifizierungszeichen, Point koordinaten)
    {
        switch(identifizierungszeichen)
        {
            case "0":
            return new Hintergrund("Hintergrund.png", koordinaten);
            case "2":
            return new Lauffeld("Lauffelder.png", koordinaten);
            case "1":
            return new Startposition(Farbe.GELB, "Startposition_Gelb.png", koordinaten);
            case "4":
            return new Startposition(Farbe.GRUEN, "Startposition_Gruen.png", koordinaten);
            case "7":
            return new Startposition(Farbe.ROT, "Startposition_Rot.png", koordinaten);
            case "9":
            return new Startposition(Farbe.SCHWARZ, "Startposition_Schwarz.png", koordinaten);
            case "3":
            return new Haus(Farbe.GRUEN, "Haus_Gruen.png", koordinaten);
            case "5":
            return new Haus(Farbe.GELB, "Haus_Gelb.png", koordinaten);
            case "6":
            return new Haus(Farbe.ROT, "Haus_Rot.png", koordinaten);
            case "8":
            return new Haus(Farbe.SCHWARZ, "Haus_Schwarz.png", koordinaten);
            case "a":
            return new Startfeld(Farbe.GELB, "Startfeld_Gelb.png", koordinaten);
            case "b":
            return new Startfeld(Farbe.GRUEN, "Startfeld_Gruen.png", koordinaten);
            case "c":
            return new Startfeld(Farbe.ROT, "Startfeld_Rot.png", koordinaten);
            case "d":
            return new Startfeld(Farbe.SCHWARZ, "Startfeld_Schwarz.png", koordinaten);
            case "w":
            return new Haus(Farbe.GRUEN, "Haus_Gruen.png", true, koordinaten);
            case "x":
            return new Haus(Farbe.GELB, "Haus_Gelb.png", true, koordinaten);
            case "y":
            return new Haus(Farbe.ROT, "Haus_Rot.png", true, koordinaten);
            case "z":
            return new Haus(Farbe.SCHWARZ, "Haus_Schwarz.png", true, koordinaten);
            case "_":
            return new Lauffeld("Lauffelder.png", true, koordinaten);
            default: 
            System.out.println("Zu dem folgenden Zeichen konnte kein passendes Feld ermittelt werden: "+identifizierungszeichen);
            return null;
        }
    }
}
