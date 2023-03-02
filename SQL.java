import java.sql.*;
public class SQL
{
   private static Connection connect()
   {
       String url = "jdbc:sqlite:H:\\Dokumente\\STDM Brüske\\Sp1.db";
       Connection conn = null;
       try
       {
           conn = DriverManager.getConnection(url);
       }
       catch (SQLException e)
       {
            System.out.println(e.getMessage());
       }
       return conn;
   }   
   
  public static void UpdateSpiel(Figur figur, int spielId)
  {     
      String sql = "UPDATE Spiel SET Figur = ?, X = ?, Y = ? WHERE SpielID = ?";
      Connection conn = connect();
      try
      {
         PreparedStatement pstmt = conn.prepareStatement(sql);
          pstmt.setInt(1,figur.getFigurId());
          pstmt.setInt(2, figur.getX());
          pstmt.setInt(3, figur.getY());
          pstmt.setInt(4, spielId);
         pstmt.executeUpdate();
      }
      catch(Exception e)
      {
          System.out.println(e.getMessage());
      }
      finally 
      {
         try 
         {
            if (conn != null) 
            {
               conn.close();
            }
         } 
         catch (SQLException e) 
         {
            System.err.println(e);
         }
        }
  }
  
  
  public static void InsertSpieler(String name, String nutzername, String passwort)
  {
      String sql = "INSERT INTO Spieler(SpielerID,Name,Nutzername,Passwort) VALUES (null,?,?,?)";
      Connection conn = connect();
      try
      {
          PreparedStatement pstmt = conn.prepareStatement(sql);
          pstmt.setString(1,name);
          pstmt.setString(2, nutzername);
          pstmt.setString(3, passwort);
          
          pstmt.executeUpdate();
      }
      catch(Exception e)
      {
          System.out.println(e.getMessage());
      }
      finally 
      {
         try 
         {
            if (conn != null) 
            {
               conn.close();
            }
         } 
         catch (SQLException e) 
         {
            System.err.println(e);
         }
        }
  }
  
  public static void InsertSpiel(int spielerId,  Figur figur)
  {
      String sql = "INSERT INTO Spiel(SpielerID,SpielID,X,Y,FigurID) VALUES (?,null,?,?,?)";
      Connection conn = connect();
      try
      {
          PreparedStatement pstmt = conn.prepareStatement(sql);
          pstmt.setInt(1,spielerId);
          //pstmt.setInt(2, spielId);
          pstmt.setInt(2, figur.getX());
          pstmt.setInt(3, figur.getY());
          pstmt.setInt(4, figur.getFigurId());
          
          pstmt.executeUpdate();
      }
      catch(Exception e)
      {
          System.out.println(e.getMessage());
      }
      finally 
      {
         try 
         {
            if (conn != null) 
            {
               conn.close();
            }
         } 
         catch (SQLException e) 
         {
            System.err.println(e);
         }
        }
  }
  
  public static void UpdateStatistik(int spielerIdGewinner, int spielerIdVerlierer)
  {
      String sql1 = "UPDATE Statistik SET Siege = Siege + 1 WHERE SpielerID = ?";
      String sql2 = "UPDATE Statistik SET Niederlagen = Niederlagen + 1 WHERE SpielerID = ?";
      Connection conn = connect();
      try
      {
        PreparedStatement pstmt1 = conn.prepareStatement(sql1);
        pstmt1.setInt(1,spielerIdGewinner);
        PreparedStatement pstmt2 = conn.prepareStatement(sql2);
        pstmt2.setInt(1,spielerIdVerlierer);
        pstmt1.executeUpdate();
        pstmt2.executeUpdate();
      }
      catch(Exception e)
      {
          e.printStackTrace();
      }
      finally 
      {
         try 
         {
            if (conn != null) 
            {
               conn.close();
            }
         } 
         catch (SQLException e) 
         {
            System.err.println(e);
         }
        }
  }
  
 
}