package io.github.shniu.flashchat.client;

import java.io.IOException;

/**
 * @author niushaohan
 * @date 2020/12/21 14
 */
public class FlashChatClientApplication {

    public static void main(String[] args) {
        try {
            FlashChatClient flashChatClient = new FlashChatClient("localhost", 10005);
            flashChatClient.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
