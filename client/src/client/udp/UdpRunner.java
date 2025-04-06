package client.udp;

public class UdpRunner implements Runnable {

    UdpClient client;

    public UdpRunner(UdpClient client) {
        this.client = client;
    }

    @Override
    public void run() {
        while (true) {
            client.receive();
        }
    }
}
