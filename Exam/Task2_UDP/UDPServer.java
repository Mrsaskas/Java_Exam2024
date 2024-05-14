import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPServer {
    private static final int PORT = 8888;
    private static final int BUFFER_SIZE = 1024;

    public static void main(String[] args) {
        try {
            DatagramSocket serverSocket = new DatagramSocket(PORT);
            System.out.println("UDP сервер запущено на порту " + PORT);

            while (true) {
                receiveAndProcess(serverSocket);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void receiveAndProcess(DatagramSocket serverSocket) throws IOException {
        byte[] receiveBuffer = new byte[BUFFER_SIZE];
        DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
        serverSocket.receive(receivePacket);
        String message = new String(receivePacket.getData(), 0, receivePacket.getLength());
        System.out.println("Отримано повідомлення від клієнта: " + message);
        sendResponse(serverSocket, receivePacket.getAddress(), receivePacket.getPort(), message.length());
    }

    private static void sendResponse(DatagramSocket serverSocket, InetAddress clientAddress, int clientPort, int messageLength) throws IOException {
        byte[] sendBuffer = String.valueOf(messageLength).getBytes();
        DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, clientAddress, clientPort);
        serverSocket.send(sendPacket);
    }
}