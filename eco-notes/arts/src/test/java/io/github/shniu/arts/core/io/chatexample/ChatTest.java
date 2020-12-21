package io.github.shniu.arts.core.io.chatexample;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;

import static org.junit.Assert.*;

/**
 * @author niushaohan
 * @date 2020/12/20 21
 */
public class ChatTest {

    @Test
    public void deliversAllMessagesToConnectedClients() throws Exception {
        ChatServer server = new ChatServer("Nio Chat Server", 10001);
        server.start();

        int port = server.getPort();
        assertNotEquals("", port, 0);

        CountDownLatch latch = new CountDownLatch(3);

        ChatClient client1 = new ChatClient("Nio Chat Client - 1", "localhost", port, new ChatResponseHandler() {
            @Override
            public void onMessage(String message) {
                System.out.println("Client - 1: onMessage " + message);
                latch.countDown();
            }
        });
        client1.start();

        ChatClient client2 = new ChatClient("Nio Chat Client - 2", "localhost", port, new ChatResponseHandler() {
            @Override
            public void onMessage(String message) {
                System.out.println("Client - 2: onMessage " + message);
                latch.countDown();
            }
        });
        client2.start();

        ChatClient client3 = new ChatClient("Nio Chat Client - 3", "localhost", port, new ChatResponseHandler() {
            @Override
            public void onMessage(String message) {
                System.out.println("Client - 3: onMessage " + message);
                latch.countDown();
            }
        });
        client3.start();

        Thread.sleep(500);

        client1.sendMessage("I'm client 1.");
        client2.sendMessage("I'm client 2.");
        client3.sendMessage("I'm client 3.");

        latch.await();
    }

    @Test
    public void handlesContinuousStreamOfMessages() throws Exception {
        ChatServer server = new ChatServer("Nio Chat Server2", 10002);
        server.start();
        int port = server.getPort();
        // System.out.println("Start on " + port);

        int messageCount = 100;
        CountDownLatch latch = new CountDownLatch(messageCount);

        ChatClient client1 = new ChatClient("client 1", "localhost", port, new ChatResponseHandler() {
            @Override
            public void onMessage(String message) {
                System.out.println("Client 1: " + message);
            }
        });
        client1.start();

        ChatClient client2 = new ChatClient("client 2", "localhost", port, new ChatResponseHandler() {
            @Override
            public void onMessage(String message) {
                System.out.println("Client 2 received: " + message);
                latch.countDown();
            }
        });
        client2.start();

        Thread.sleep(500);

        for (int i = 0; i < messageCount; i++) {
            client1.sendMessage("msg-" + i);
        }

        latch.await();
    }
}