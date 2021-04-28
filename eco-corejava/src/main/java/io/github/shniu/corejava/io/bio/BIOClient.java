package io.github.shniu.corejava.io.bio;

import java.io.IOException;
import java.net.Socket;
import java.util.Date;

public class BIOClient {

    public static void main(String[] args) {
        new Thread(() -> {
            try {
                Socket socket = new Socket("127.0.0.1", 8000);
                while (true) {
                    socket.getOutputStream().write((new Date() + ":hello world").getBytes());
                    System.out.println("send");
                    Thread.sleep(2000);
                }
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
