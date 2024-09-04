import java.net.*;

public class DReceiver {
    public static void main(String[] args) {
        try {
            DatagramSocket socket = new DatagramSocket(6666);

            byte[] receiveData = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

            socket.receive(receivePacket);

            String receivedMessage = new String(receivePacket.getData(), 0, receivePacket.getLength());
            System.out.println("Received message from sender: " + receivedMessage);

            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}