package org.digcredit.project.echo;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Date;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

/**
 * @author niushaohan
 * @date 2021/2/2 21
 */
public class EchoClient {

    public void start() throws IOException {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);
        socketChannel.connect(new InetSocketAddress("127.0.0.1", 4455));
        while (!socketChannel.finishConnect()) {
            // connect
        }

        System.out.println("Connect succeed.");

        Processor processor = new Processor(socketChannel);
        new Thread(processor).start();
    }

    static class Processor implements Runnable {
        final Selector selector;
        private SocketChannel socketChannel;

        public Processor(SocketChannel socketChannel) throws IOException {
            selector = Selector.open();
            this.socketChannel = socketChannel;

            this.socketChannel.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE);
        }

        @Override
        public void run() {
            try {
                while (true) {
                    selector.select();
                    Set<SelectionKey> selectionKeys = selector.selectedKeys();
                    Iterator<SelectionKey> iterator = selectionKeys.iterator();
                    while (iterator.hasNext()) {
                        SelectionKey sk = iterator.next();
                        if (sk.isReadable()) {
                            System.out.println("readable...");
                            // read
                            SocketChannel socketChannel = (SocketChannel) sk.channel();

                            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                            int length = 0;
                            while ((length = socketChannel.read(byteBuffer)) > 0) {
                                byteBuffer.flip();
                                System.out.println("server echo: " + new String(byteBuffer.array(), 0, length));
                                byteBuffer.clear();
                            }
                        }

                        if (sk.isWritable()) {
                            // write
                            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

                            Scanner scanner = new Scanner(System.in);
                            System.out.println("输入: ");
                            if (scanner.hasNext()) {
                                SocketChannel socketChannel = (SocketChannel) sk.channel();
                                String line = scanner.next();
                                String content = new Date() + " >> " + line;
                                // System.out.println(content);
                                byteBuffer.put(content.getBytes());
                                byteBuffer.flip();
                                socketChannel.write(byteBuffer);
                                byteBuffer.clear();
                            }

                            try {
                                Thread.sleep(10);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    selectionKeys.clear();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new EchoClient().start();
    }
}
