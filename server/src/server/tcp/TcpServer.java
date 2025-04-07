package server.tcp;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class TcpServer {
    static Set<Client> clients = ConcurrentHashMap.newKeySet();
    private ServerSocket serverSocket;
    private final int port = 8080;

    public TcpServer() {
        try {
            serverSocket = new ServerSocket(port);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Client accept() {
        while (true) {
            Socket socket = acceptClient();
            System.out.println("새로운 사용자가 입장하였습니다.");

            Client client = new Client(socket);
            clients.add(client);
            new Thread(client).start();
        }
    }

    public static void broadCast(String message, Client sender) {
        for (Client client : clients) {
            if (client != sender)
                client.sendMessage(message);
        }
    }

    public static void removeClient(Client client) {
        clients.remove(client);
    }

    private Socket acceptClient() {
        Socket socket = null;
        try {
            socket = serverSocket.accept();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return socket;
    }
}
