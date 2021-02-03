package org.digcredit.project.echo;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author niushaohan
 * @date 2021/2/3 09
 */
public class MultiThreadEchoServerReactor {
    private Selector[] selectors;
    private ServerSocketChannel serverSocketChannel;
    private int acceptorSelectorIndex = 0;
    private AtomicLong next = new AtomicLong(0);

    public MultiThreadEchoServerReactor() throws IOException {
        selectors = new Selector[2];
        for (int i = 0; i < selectors.length; i++) {
            selectors[i] = Selector.open();
        }

        serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.bind(new InetSocketAddress(4455));

        SelectionKey sk = serverSocketChannel.register(selectors[0], SelectionKey.OP_ACCEPT);
        sk.attach(new AcceptorHandler());
    }

    public void start() {
        for (int i = 0; i < selectors.length; i++) {
            new Thread(new SubReactor(selectors[i])).start();
        }
    }

    class SubReactor implements Runnable {
        private final Selector selector;

        public SubReactor(Selector selector) {
            this.selector = selector;
        }

        @Override
        public void run() {
            //noinspection DuplicatedCode
            try {
                while (true) {
                    selector.select();
                    Set<SelectionKey> keys = selector.selectedKeys();
                    Iterator<SelectionKey> iter = keys.iterator();

                    while (iter.hasNext()) {
                        SelectionKey key = iter.next();
                        dispatch(key);
                    }

                    keys.clear();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        void dispatch(SelectionKey sk) {
            Runnable handler = (Runnable) sk.attachment();
            if (handler != null) {
                handler.run();
            }
        }
    }

    class AcceptorHandler implements Runnable {

        @Override
        public void run() {
            try {
                SocketChannel socketChannel = serverSocketChannel.accept();
                if (socketChannel != null) {
                    long seq = next.getAndIncrement();
                    int idx = (int) (seq % selectors.length);
                    System.out.println("Using selector " + idx);
                    new MultiThreadEchoHandler(selectors[idx], socketChannel);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        MultiThreadEchoServerReactor serverReactor = new MultiThreadEchoServerReactor();
        serverReactor.start();
        System.out.println("Multi thread reactor echo server started.");
    }
}
