
/**
 * Write a description of class Figur here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Figur
{
    // instance variables - replace the example below with your own
    private int x;
    private int y;
    private int figurId;
    /**
     * Constructor for objects of class Figur
     */
    public Figur()
    {
        // initialise instance variables
        x = 0;
    }

   public void setFigurId(int id)
   {
      this.figurId = id;
   }
   
    public void setX(int x)
   {
      this.x = x;
   }
   
    public void setY(int y)
   {
      this.y = y;
   }
   
   public int getFigurId()
   {
       return figurId;
   }
   
   public int getX()
   {
       return x;
   }
   
   public int getY()
   {
       return y;
   }
}
