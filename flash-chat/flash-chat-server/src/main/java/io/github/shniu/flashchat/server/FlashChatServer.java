package io.github.shniu.flashchat.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;

/**
 * @author niushaohan
 * @date 2020/12/22 09
 */
public class FlashChatServer {
    private Selector selector;
    private ServerSocketChannel serverSocketChannel;

    public FlashChatServer(int port) throws IOException {
        assert port > 0;

        selector = Selector.open();

        serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.bind(new InetSocketAddress(port));

        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        System.out.println("Flash chat server listen on " + port);
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
