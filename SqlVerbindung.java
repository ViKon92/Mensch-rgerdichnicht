import java.sql.*;

public class SqlVerbindung {
   public static void main(String[] args) {
      Connection connection = null;
      
      try {
         // Laden des SQLite-Treibers
         Class.forName("org.sqlite.JDBC");
         
         // Verbindung zur Datenbank herstellen
         connection = DriverManager.getConnection("jdbc:sqlite:H:\\Dokumente\\STDM Brüske\\Sp1.db");
         
         System.out.println("Verbindung zur Datenbank hergestellt");
         
         // Ausführen einer Beispielabfrage
         Statement statement = connection.createStatement();
         ResultSet resultSet = statement.executeQuery("SELECT Farbe FROM Figur WHERE FigurenID = 1");
         
         while (resultSet.next()) {
            System.out.println("Farbe: " + resultSet.getString("Farbe"));
         }
      } catch (Exception e) {
         System.err.println(e.getMessage());
      } finally {
         try {
            if (connection != null) {
               connection.close();
            }
         } catch (SQLException e) {
            System.err.println(e);
         }
      }
   }
   
   public void speicherSpielstand(int figur, int x, int y, int id)
   {
       Connection connection = null;
       PreparedStatement pstmt;
       
       try{
       Class.forName("org.sqlite.JDBC");
         
         // Verbindung zur Datenbank herstellen
         connection = DriverManager.getConnection("jdbc:sqlite:H:\\Dokumente\\STDM Brüske\\Sp1.db");
         
         Statement statement = connection.createStatement();
         pstmt = connection.prepareStatement("UPDATE Spiel SET Figur =?, X = ?, Y = ? WHERE SpielID = ?");
         pstmt.setString(1, Integer.toString(figur));
         pstmt.setString(1, Integer.toString(x));
         pstmt.setString(1, Integer.toString(y));
         pstmt.setString(1, Integer.toString(id));
         pstmt.executeUpdate();
         //ResultSet resultSet = statement.executeQuery("UPDATE spiele SET Figur = figur, X = x and Y = y WHERE spielID = id");
        }
         catch (Exception e) {
         System.err.println(e.getMessage());
      } finally {
         try {
            if (connection != null) {
               connection.close();
            }
         } catch (SQLException e) {
            System.err.println(e);
         }
        }
   }
}