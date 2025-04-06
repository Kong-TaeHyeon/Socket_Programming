package client.tcp;

import java.io.BufferedReader;
import java.io.IOException;

public class TcpRunner implements Runnable {
    BufferedReader br;

    public TcpRunner(BufferedReader br) {
        this.br = br;
    }

    @Override
    public void run() {
        String readMessage = null;
        try {
            while ((readMessage = br.readLine()) != null) {
                System.out.println("받은 메세지 : " + readMessage);
            }
        } catch (IOException e) {
            e.printStackTrace(); // 읽기 실패.
        }
    }
}
