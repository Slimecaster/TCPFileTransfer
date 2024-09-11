import java.io.*;
import java.net.*;

public class TCPServer {
    public static void main(String[] args) {
        String filePath = "C:/Users/nd070/IdeaProjects/TCP - filetransfer/src/Server/file.txt"; // Full path to the file

        try (ServerSocket serverSocket = new ServerSocket(5000)) {
            System.out.println("Server is listening on port 5000");

            // Accept client connection
            Socket socket = serverSocket.accept();
            System.out.println("New client connected");

            // Send the file to the client
            try (FileInputStream fileInputStream = new FileInputStream(filePath);
                 BufferedOutputStream outputStream = new BufferedOutputStream(socket.getOutputStream())) {

                byte[] buffer = new byte[1024];
                int bytesRead;

                // Read file and send to client
                while ((bytesRead = fileInputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }

                System.out.println("File sent successfully!");
            }

            // Close the connection
            socket.close();
        } catch (IOException ex) {
            System.out.println("Server exception: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
