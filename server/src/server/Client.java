package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client implements Runnable {
    private Socket socket;
    private PrintWriter writer;
    private BufferedReader reader;


    public Client(Socket socket) {
        this.socket = socket;
        try {
            writer = new PrintWriter(socket.getOutputStream(), true);
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(String message) {
        writer.println(message);
    }

    @Override
    public void run() {
        String message = null;

        while ((message = getMessage()) != null) {
            System.out.println("받은 메세지 : " + message);
            Server.broadCast(message, this);
        }

        System.out.println("사용자 한명이 나갔습니다.");
        close();
    }

    private String getMessage() {
        String message = null;
        try {
            message = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return message;
    }

    private void close() {
        try {
            Server.removeClient(this);
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
