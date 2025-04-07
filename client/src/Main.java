import client.tcp.TcpClient;
import client.tcp.TcpRunner;
import client.udp.UdpClient;
import client.udp.UdpRunner;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Main {
    public static void main(String[] args) throws Exception {
        String hostname = "localhost";
        int port = 8080;

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("1 : TCP, 2 : UDP 알맞은 숫자를 입력해주세요.");

        String command = reader.readLine();

        if (command.equals("1")) {
            // TCP.
            TcpClient tcpClient = new TcpClient(hostname, port);
            PrintWriter writer = tcpClient.getPrintWriter();

            TcpRunner tcpRunner = new TcpRunner(tcpClient.getBufferedReader());
            new Thread(tcpRunner).start();

            BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));

            try {
                String userInput;
                while ((userInput = consoleReader.readLine()) != null) {
                    writer.println(userInput);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            UdpClient udpClient = new UdpClient(hostname, port);
            new Thread(new UdpRunner(udpClient)).start();
            while (true) {
                String sendMessage = reader.readLine();
                udpClient.send(sendMessage);
            }
        }


    }
}