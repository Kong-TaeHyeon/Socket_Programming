package client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class TcpClient {
    private Socket socket;

    public TcpClient(String hostName, int port) {
        try {
            Socket socket = new Socket(hostName, port);
            this.socket = socket;
        } catch (Exception e) {
            e.printStackTrace(); // 소켓 생성에 실패.
        }
    }

    public BufferedReader getBufferedReader() {
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (Exception e) {
            e.printStackTrace(); // Reader 생성 실패.
        }
        return bufferedReader;
    }

    public PrintWriter getPrintWriter() {
        PrintWriter printWriter = null;
        try {
            printWriter = new PrintWriter(socket.getOutputStream(), true);
        } catch (Exception e) {
            e.printStackTrace(); // Writer 생성 실패.
        }
        return printWriter;
    }

}
