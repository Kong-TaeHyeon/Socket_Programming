package client;

import java.io.BufferedReader;
import java.io.IOException;

public class ReaderThread implements Runnable {
    BufferedReader br;

    public ReaderThread(BufferedReader br) {
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
