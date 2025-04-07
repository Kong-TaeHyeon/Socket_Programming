package server.udp;

import java.net.SocketAddress;

public class Message {
    private String message;
    private SocketAddress sender;

    public Message(String message, SocketAddress sender) {
        this.message = message;
        this.sender = sender;
    }

    public String getMessage() {
        return message;
    }

    public SocketAddress getSender() {
        return sender;
    }
}
