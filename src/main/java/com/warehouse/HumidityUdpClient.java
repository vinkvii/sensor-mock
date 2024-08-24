package com.warehouse;

import java.io.IOException;
import java.net.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class HumidityUdpClient {

    public static void main(String[] args) {
        DatagramSocket socket = null;
        try {
            socket = new DatagramSocket();
            //InetAddress address = InetAddress.getByName("172.27.160.1");
            InetAddress address = InetAddress.getByName("127.0.0.1");
            int port = 4003;

            // Load the resource file from src/main/resources
            Path filePath = getResourcePath("humidity_input.txt");

            List<String> messages = Files.readAllLines(filePath);
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

    /**
     * Retrieves the resource path from the classpath.
     *
     * @param resourceName the name of the resource file
     * @return the Path to the resource
     * @throws IOException if the resource is not found
     */
    private static Path getResourcePath(String resourceName) throws IOException {
        try {
            // Load the file from the classpath using ClassLoader and convert it to URI
            ClassLoader classLoader = TemperatureUdpClient.class.getClassLoader();
            return Paths.get(classLoader.getResource(resourceName).toURI());
        } catch (URISyntaxException e) {
            throw new IOException("Error loading resource: " + resourceName, e);
        }
    }
}
