package client.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UdpClient {
    DatagramSocket socket;
    byte[] inBuffer = new byte[100];
    byte[] outBuffer = new byte[100];

    DatagramPacket inPacket;
    DatagramPacket outPacket;

    InetAddress targetAddress;
    int port;

    public UdpClient(String hostName, int port) {
        try {
            this.socket = new DatagramSocket();
            this.port = port;
            this.targetAddress = InetAddress.getByName(hostName);

            // Packet Set.
            setOutPacket(this.outBuffer);
            setInPacket(this.inBuffer);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void receive() {
        try {
            socket.receive(inPacket);
            String message = new String(inPacket.getData(), 0, inPacket.getLength());
            System.out.println("받은 메세지 : " + message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void send(String message) {
        try {
            byte[] buffer = message.getBytes();
            setOutPacket(buffer);
            socket.send(outPacket);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void setOutPacket(byte[] buffer) {
        this.outPacket = new DatagramPacket(buffer, buffer.length, targetAddress, port);
    }

    private void setInPacket(byte[] buffer) {
        this.inPacket = new DatagramPacket(buffer, buffer.length);
    }
}
