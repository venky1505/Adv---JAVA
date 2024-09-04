import java.io.*;
import java.net.*;

public class serverword {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(6666);
            System.out.println("Server listening on port 6666...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket);

                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

                String name = in.readLine();
                System.out.println("Received from client: " + name);

                String upperCaseName = name.toUpperCase();
                out.println(upperCaseName);
                System.out.println("Sent to client: " + upperCaseName);

                clientSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}