import java.net.*;

public class DSender {
    public static void main(String[] args) {
        try {
            DatagramSocket socket = new DatagramSocket();

            String message = "Hello, Balaji!";
            InetAddress receiverAddress = InetAddress.getByName("localhost");
            int receiverPort = 6666;

            byte[] sendData = message.getBytes();
            DatagramPacket packet = new DatagramPacket(sendData, sendData.length, receiverAddress, receiverPort);

            socket.send(packet);
            System.out.println("Message sent to receiver.");

            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}