package io.github.shniu.flashchat.client;

import java.io.IOException;
import java.net.InetSocketAddress;

/**
 * @author niushaohan
 * @date 2020/12/21 14
 */
public class FlashChatClientApplication {

    public static void main(String[] args) {
        try {
            String endpointId = "Flash Chat Nio Client - 1";
            InetSocketAddress serverAddress = new InetSocketAddress("localhost", 10005);

            FlashChatClient flashChatClient = new FlashChatClient(endpointId, serverAddress, message -> {
                System.out.println("Received " + message);
            });
            flashChatClient.start();

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // flashChatClient.send("123");

            for (int i = 0; i < 100; i++) {
                flashChatClient.send("hello-" + i);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
