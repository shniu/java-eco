package io.github.shniu.flashchat.client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

/**
 * @author niushaohan
 * @date 2020/12/22 10
 */
public class FlashChatClient {
    private Selector selector;
    private SocketChannel socketChannel;

    public FlashChatClient(String host, int port) throws IOException {
        selector = Selector.open();
        socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);
        socketChannel.connect(new InetSocketAddress(host, port));

        socketChannel.register(selector, SelectionKey.OP_READ);
        System.out.println("Connect to " + socketChannel.getRemoteAddress() + " successfully");
    }

    public void start() {
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
