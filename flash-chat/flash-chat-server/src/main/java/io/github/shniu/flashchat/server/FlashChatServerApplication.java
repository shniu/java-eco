package io.github.shniu.flashchat.server;

import java.io.IOException;

/**
 * @author niushaohan
 * @date 2020/12/21 14
 */
public class FlashChatServerApplication {

    public static void main(String[] args) {
        int port = 10005;

        try {
            FlashChatServer chatServer = new FlashChatServer("FlashChat Nio Server", port);
            chatServer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
