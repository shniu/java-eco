package org.digcredit.project.echo;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

/**
 * @author niushaohan
 * @date 2021/2/2 21
 */
public class EchoHandler implements Runnable {
    final SocketChannel socketChannel;
    final SelectionKey sk;

    final ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
    static final int RECEIVING = 0, SENDING = 1;
    int state = RECEIVING;

    public EchoHandler(Selector selector, SocketChannel socketChannel) throws IOException {
        this.socketChannel = socketChannel;
        this.socketChannel.configureBlocking(false);
        this.sk = this.socketChannel.register(selector, 0);

        this.sk.attach(this);

        sk.interestOps(SelectionKey.OP_READ);
        selector.wakeup();
        System.out.println("Register socket channel");
    }

    @Override
    public void run() {
        try {

            if (state == SENDING) {
                System.out.println("writing...");
                // 写
                socketChannel.write(byteBuffer);
                byteBuffer.clear();
                sk.interestOps(SelectionKey.OP_READ);
                state = RECEIVING;
            } else if (state == RECEIVING) {
                System.out.println("receiving...");
                // 读
                int length = 0;
                while ((length = socketChannel.read(byteBuffer)) > 0) {
                    System.out.println(new String(byteBuffer.array(), 0, length));
                }

                byteBuffer.flip();
                sk.interestOps(SelectionKey.OP_WRITE);
                state = SENDING;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
