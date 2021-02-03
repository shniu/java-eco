package org.digcredit.project.echo;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author niushaohan
 * @date 2021/2/3 10
 */
public class MultiThreadEchoHandler implements Runnable {
    final SocketChannel socketChannel;
    final SelectionKey sk;

    final ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
    static final int RECEIVING = 0, SENDING = 1;
    int state = RECEIVING;

    // 线程池，对象共享
    static ExecutorService threadPool = Executors.newFixedThreadPool(4);

    public MultiThreadEchoHandler(Selector selector, SocketChannel socketChannel) throws IOException {
        this.socketChannel = socketChannel;
        this.socketChannel.configureBlocking(false);
        this.sk = this.socketChannel.register(selector, 0);

        this.sk.attach(this);
        this.sk.interestOps(SelectionKey.OP_READ);
        selector.wakeup();
    }

    @Override
    public void run() {
        threadPool.execute(new AsyncTask());
    }

    public synchronized void asyncRun() {
        try {

            // read
            if (state == RECEIVING) {
                int length = 0;
                while ((length = socketChannel.read(byteBuffer)) > 0) {
                    System.out.println("Received: " + new String(byteBuffer.array(), 0, length));
                }

                byteBuffer.flip();
                this.sk.interestOps(SelectionKey.OP_WRITE);
                state = SENDING;
            }
            // write
            else if (state == SENDING) {
                socketChannel.write(byteBuffer);
                byteBuffer.clear();
                this.sk.interestOps(SelectionKey.OP_READ);
                state = RECEIVING;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    class AsyncTask implements Runnable {

        @Override
        public void run() {
            MultiThreadEchoHandler.this.asyncRun();
        }
    }
}
