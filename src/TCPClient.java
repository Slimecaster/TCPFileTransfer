import java.io.*;
import java.net.*;

public class TCPClient {
    public static void main(String[] args) {
        String hostname = "localhost";  // Server IP address
        int port = 5000;
        String saveFilePath = "C:/Users/nd070/IdeaProjects/TCP - filetransfer/src/Client/file.txt";  // Path to save the file

        try (Socket socket = new Socket(hostname, port);
             InputStream inputStream = socket.getInputStream();
             FileOutputStream fileOutputStream = new FileOutputStream(saveFilePath);
             BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream)) {

            byte[] buffer = new byte[1024];
            int bytesRead;

            // Receive the file from server
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                bufferedOutputStream.write(buffer, 0, bytesRead);
            }

            System.out.println("File received successfully!");

        } catch (IOException ex) {
            System.out.println("Client error: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
