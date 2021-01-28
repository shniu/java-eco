package io.github.shniu.os.zerocopy;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * @author niushaohan
 * @date 2021/1/28 15
 */
public class ZeroCopyServer {

    public static void main(String[] args) {
        InetSocketAddress inetSocketAddress = new InetSocketAddress(3334);

        try {
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            ServerSocket serverSocket = serverSocketChannel.socket();
            serverSocket.setReuseAddress(true);
            serverSocket.bind(inetSocketAddress);
            System.out.println("Listening on " + inetSocketAddress.toString());

            ByteBuffer byteBuffer = ByteBuffer.allocateDirect(4096);

            while (true) {
                SocketChannel socketChannel = serverSocketChannel.accept();
                System.out.println("Accepted " + socketChannel);
                socketChannel.configureBlocking(true);

                int read = 0;
                while (read != -1) {
                    try {
                        read = socketChannel.read(byteBuffer);
                    } catch (IOException e) {
                        e.printStackTrace();
                        read = -1;
                    }

                    byteBuffer.rewind();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
