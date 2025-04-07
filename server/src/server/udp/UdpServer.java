package server.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketAddress;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;

public class UdpServer {
    private DatagramSocket socket;
    private final int port = 8080;
    private Set<SocketAddress> clients = ConcurrentHashMap.newKeySet();
    private BlockingQueue<Message> messageQueue = new LinkedBlockingQueue<>();

    public UdpServer() {
        try {
            socket = new DatagramSocket(port);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void listen() {
        byte[] buffer = new byte[100];
        while (true) {
            try {
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);

                SocketAddress sender = packet.getSocketAddress();
                clients.add(sender);

                String message = new String(packet.getData(), 0, packet.getLength());
                System.out.println("받은 메세지 : " + message);

                messageQueue.put(new Message(message, sender));

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void send() {
        while (true) {
            try {
                Message message = messageQueue.take();
                byte[] byteData = message.getMessage().getBytes();

                for (SocketAddress client : clients) {
                    if (!client.equals(message.getSender())) {
                        DatagramPacket packet = new DatagramPacket(byteData, byteData.length, client);
                        socket.send(packet);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
