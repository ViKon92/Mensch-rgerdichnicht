import java.io.*;
import java.net.*;

public class ClientConnectionConsole {

    private static boolean isConnected = false;

    public static void main(String[] args) throws IOException {
        String serverAddress = "10.3.0.121"; //Server-IP-Adresse hier einfügen
        int portNumber = 10500; //Port-Nummer hier einfügen
        System.out.println("Trying to connect to " + "10.3.0.121" + " on port " + 10500);
        Socket clientSocket = null;
        PrintWriter out = null;
        BufferedReader in = null;
        try {
            clientSocket = new Socket(serverAddress, portNumber);
            isConnected = true;
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            System.out.println("Connected to server.");
        } catch (UnknownHostException e) {
            System.err.println("Unknown host: " + serverAddress);
            System.exit(1);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.err.println("Could not connect to: " + serverAddress);
            System.exit(1);
        }
        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
        String userInput;
        while ((userInput = stdIn.readLine()) != null) {
            if (isConnected) {
                out.println(userInput);
                System.out.println("Server: " + in.readLine());
                if (userInput.equals("Bye.")) {
                    break;
                }
            } else {
                System.out.println("Connection lost. Please wait while trying to reconnect...");
                while (!isConnected) {
                    try {
                        clientSocket = new Socket(serverAddress, portNumber);
                        isConnected = true;
                        out = new PrintWriter(clientSocket.getOutputStream(), true);
                        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                        System.out.println("Connected to server.");
                    } catch (UnknownHostException e) {
                        System.err.println("Unknown host: " + serverAddress);
                        System.exit(1);
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                        System.err.println("Could not connect to: " + serverAddress);
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException ex) {
                            Thread.currentThread().interrupt();
                        }
                    }
                }
            }
        }
        out.close();
        in.close();
        stdIn.close();
        clientSocket.close();
    }
}
