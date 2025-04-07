package server.udp;

public class UdpRunner implements Runnable {
    private UdpServer udpServer;


    public UdpRunner(UdpServer udpServer) {
        this.udpServer = udpServer;
    }

    @Override
    public void run() {
        udpServer.send();
    }
}
