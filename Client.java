import java.net.*;
import java.io.*;
public class Client {
public static void main(String[] args) throws IOException
 {
     String serverHostname = "10.3.0.121"; //server IP address
     int portNumber = 10500; //port number    
     System.out.println("Connecting to server " + serverHostname + " on port " + portNumber);
     Socket clientSocket = null;
     PrintWriter out = null;
     BufferedReader in = null;
     try {
          clientSocket = new Socket(serverHostname, portNumber);
          out = new PrintWriter(clientSocket.getOutputStream(), true);
          in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        } 
       catch (UnknownHostException e)
       {
           System.err.println("Unknown host: " + serverHostname);
           System.exit(1);
        } 
        catch (IOException e)
        {
            System.out.println(e.getMessage());
            System.err.println("Couldn't get I/O for the connection to: " + serverHostname);
            System.exit(1);
        }
        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
        String userInput;
        while ((userInput = stdIn.readLine()) != null) {
            out.println(userInput);
            System.out.println("Server: " + in.readLine());
            if (userInput.equals("Bye.")) {
                break;
            }
        }
        out.close();
        in.close();
        stdIn.close();
        clientSocket.close();
}
}