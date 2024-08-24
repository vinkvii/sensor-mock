import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketTimeoutException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class TemperatureUdpClient {

    public static void main(String[] args) {
        DatagramSocket socket = null;
        try {
            socket = new DatagramSocket();
           // InetAddress address = InetAddress.getByName("172.27.160.1");
            InetAddress address = InetAddress.getByName("127.0.0.1");
            int port = 4002;

            // Read the message(s) from the file
            List<String> messages = Files.readAllLines(Paths.get("temperature_input.txt"));

            // Infinite loop to keep sending messages
            while (true) {
                // Iterate through each message in the file
                for (String message : messages) {
                    byte[] buf = message.getBytes();
                    DatagramPacket packet = new DatagramPacket(buf, buf.length, address, port);

                    // Send the packet
                    socket.send(packet);
                    System.out.println("Sent Message: " + message + " to: " + address.getHostAddress());

                    // Sleep to simulate delay between messages
                    Thread.sleep(10000);
                }
                // After finishing the file, start from the beginning again.
            }
        } catch (SocketTimeoutException ex) {
            System.out.println("Timeout error: " + ex.getMessage());
            ex.printStackTrace();
        } catch (IOException ex) {
            System.out.println("Client error: " + ex.getMessage());
            ex.printStackTrace();
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt(); // Reset interrupted flag
            ex.printStackTrace();
        } finally {
            if (socket != null && !socket.isClosed()) {
                socket.close();
            }
        }
    }
}
