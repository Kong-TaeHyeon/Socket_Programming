import client.TcpClient;
import client.ReaderThread;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Main {
    public static void main(String[] args) {
        String hostname = "localhost";
        int port = 8080;

        TcpClient tcpClient = new TcpClient(hostname, port);
        PrintWriter writer = tcpClient.getPrintWriter();

        ReaderThread readerThread = new ReaderThread(tcpClient.getBufferedReader());
        readerThread.run();

        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));

        try {
            String userInput;
            while ((userInput = consoleReader.readLine()) != null) {
                writer.println(userInput);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}