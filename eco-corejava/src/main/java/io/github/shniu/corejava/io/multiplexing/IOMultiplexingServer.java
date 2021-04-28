package io.github.shniu.corejava.io.multiplexing;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Set;

/**
 * @author niushaohan
 * @date 2020/12/18 16
 */
public class IOMultiplexingServer {

    public static void main(String[] args) throws IOException {
        Selector selector = Selector.open();

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.socket().bind(new InetSocketAddress(9933));

        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        while (true) {
            int selected = selector.select(100);

            final Set<SelectionKey> selectionKeys = selector.selectedKeys();
            for (SelectionKey selectedKey : selectionKeys) {
                if (selectedKey.isAcceptable()) {
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector, SelectionKey.OP_READ);
                }

                if (selectedKey.isConnectable()) {

                }

                if (selectedKey.isReadable()) {
                    SocketChannel socketChannel = (SocketChannel) selectedKey.channel();

                    ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                    int len = 0;
                    while ((len = socketChannel.read(byteBuffer)) != -1) {
                        byteBuffer.flip();
                        System.out.println(new String(byteBuffer.array(), 0, len));
                        byteBuffer.clear();
                    }
                    socketChannel.close();
                }

                if (selectedKey.isWritable()) {

                }
            }

            selectionKeys.clear();
        }
    }
}
