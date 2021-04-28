package io.github.shniu.corejava.io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @author niushaohan
 * @date 2020/12/18 15
 */
public class NonBlockIOClient {

    public static void main(String[] args) throws IOException {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress("127.0.0.1", 9922));

        ByteBuffer writeBuffer = ByteBuffer.allocate(16);
        writeBuffer.putChar('h');
        writeBuffer.putChar('e');
        writeBuffer.putChar('l');
        writeBuffer.putChar('l');
        writeBuffer.putChar('o');
        writeBuffer.flip();
        socketChannel.write(writeBuffer);
        System.out.println("Client sent: hello");

        ByteBuffer readBuffer = ByteBuffer.allocate(32);
        while (true) {
            int read = socketChannel.read(readBuffer);
            readBuffer.flip();
            byte[] bytes = new byte[read];
            readBuffer.get(bytes);
            System.out.println("Client received: " + new String(bytes));

            break;
        }
    }
}
