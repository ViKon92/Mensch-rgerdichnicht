import java.awt.Point;

/**
 * Erzeugt zu einer Farbe ein passende Figur
 * 
 * @author Mirko Feldmann
 */
public class FigurFactory
{
    public static Figur erzeugeFigur(Farbe farbe, Point position)
    {
        switch(farbe)
        {
            case GELB:
            return new Figur("slime.png", Farbe.GELB, position);
            case ROT:
            return new Figur("slime.png", Farbe.ROT, position);
            case SCHWARZ:
            return new Figur("slime.png", Farbe.SCHWARZ, position);
            case GRUEN:
            return new Figur("slime.png", Farbe.GRUEN, position);
            default: 
            System.out.println("Zu der folgende Farbe konnte keine Figur erstellt werden: "+farbe);
            return null;
        }
    }
}
