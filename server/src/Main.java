import server.tcp.TcpServer;
import server.udp.UdpRunner;
import server.udp.UdpServer;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("1 : TCP, 2 : UDP 알맞은 숫자를 입력해주세요.");
        String command = reader.readLine();

        if (command.equals("1")) {
            TcpServer tcpServer = new TcpServer();
            tcpServer.accept();
        } else {
            UdpServer udpServer = new UdpServer();
            new Thread(new UdpRunner(udpServer)).start();
            udpServer.listen();
        }
    }
}