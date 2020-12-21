package io.github.shniu.arts.core.io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Objects;

public class NonBlockIOServer {
    public static void main(String[] args) throws IOException, InterruptedException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);

        serverSocketChannel.socket()
                .bind(new InetSocketAddress(9922));

        System.out.println("Listen on :9922 ...");

        while (true) {
            SocketChannel socketChannel = serverSocketChannel.accept();

            if (Objects.isNull(socketChannel)) {
                System.out.println("No socket acquired.");
                Thread.sleep(1000);
                continue;
            }

            System.out.println("Accepted a socket: " + socketChannel.socket().getPort());

            ByteBuffer readBuffer = ByteBuffer.allocate(2048);
            int bytesRead = socketChannel.read(readBuffer);

            byte[] bytes = new byte[bytesRead];
            readBuffer.flip();
            readBuffer.get(bytes);

            System.out.println("Read bytes: " + bytes.length);

            if (bytesRead > 0) {
                System.out.println("Server received: " + new String(bytes));

                ByteBuffer writeBuffer = ByteBuffer.allocate(32);
                writeBuffer.putChar('O');
                writeBuffer.putChar('K');
                writeBuffer.flip();

                socketChannel.write(writeBuffer);
                System.out.println("Server sent: OK");
            } else {
                System.out.println("Read nothing.");
            }
        }
    }
}
