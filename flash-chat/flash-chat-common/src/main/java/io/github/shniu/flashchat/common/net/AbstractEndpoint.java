package io.github.shniu.flashchat.common.net;

import io.github.shniu.flashchat.common.exception.ConnectionException;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.AbstractSelectableChannel;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;

/**
 * @author niushaohan
 * @date 2020/12/22 22
 */
public abstract class AbstractEndpoint implements Runnable {
    protected String endpointId;
    protected InetSocketAddress address;

    protected Selector selector;
    protected AbstractSelectableChannel channel;
    protected ByteBuffer buffer;

    private volatile boolean running;
    private volatile boolean initialized = false;

    public AbstractEndpoint(String endpointId, int port) throws IOException {
        this(endpointId, new InetSocketAddress(port));
    }

    public AbstractEndpoint(String endpointId, InetSocketAddress address) throws IOException {
        this.endpointId = endpointId;
        this.address = address;

        this.buffer = ByteBuffer.allocateDirect(1024);
        this.channel = channel(address);
        this.selector = selector();
    }

    protected abstract Selector selector() throws IOException;
    protected abstract AbstractSelectableChannel channel(InetSocketAddress address) throws IOException;
    protected abstract void handleInboundData(SelectionKey key, byte[] bytes) throws IOException;
    protected abstract void handleWrite(SelectionKey key) throws IOException;

    public synchronized void start() {
        Thread execThread = new Thread(this, endpointId);
        running = true;

        execThread.start();
        while (!initialized) {
            // System.out.println("Waiting for endpoint initialized.");
        }
    }

    public void stop() throws IOException {
        running = false;
    }

    public boolean isRunning() {
        return running;
    }

    @Override
    public void run() {
        initialized = true;

        while (running) {
            try {
                eventLoop();
            } catch (ConnectionException e) {
                try {
                    stop();
                } catch (IOException ex) {
                    break;
                }
            }
        }

        cleanUp();
    }

    void eventLoop() {
        try {
            selector.select(100);

            Iterator<SelectionKey> selectionKeyIterator = selector.selectedKeys().iterator();
            while (selectionKeyIterator.hasNext()) {
                SelectionKey key = selectionKeyIterator.next();
                selectionKeyIterator.remove();

                if (!key.isValid()) {
                    continue;
                }

                if (key.isConnectable()) {
                    handleConnect(key);
                } else if (key.isAcceptable()) {
                    handleAccept(key);
                } else if (key.isReadable()) {
                    handleRead(key);
                } else if (key.isWritable()) {
                    handleWrite(key);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void handleRead(SelectionKey key) throws IOException {
        SocketChannel channel = (SocketChannel) key.channel();
        buffer.clear();

        StringBuilder sb = new StringBuilder();
        int read, total = 0;

        try {
            // Todo 定义消息协议，做序列化与反序列化，处理粘包、半包等，
            //  还原一个个真实的 Message
            while ((read = channel.read(buffer)) > 0) {
                buffer.flip();
                sb.append(StandardCharsets.UTF_8.newDecoder().decode(buffer));
                total += read;
                buffer.clear();
            }
        } catch (IOException e) {
            Closer.close(key);
            return;
        }

        if (read == -1 && total == 0) {
            Closer.close(key);
            return;
        }

        // System.out.printf("Received %d bytes, content is %s", total, sb.toString());

        if (total > 0) {
            handleInboundData(key, sb.toString().getBytes());
        }
    }

    protected void handleConnect(SelectionKey key) {
        SocketChannel channel = (SocketChannel) key.channel();

        try {
            channel.finishConnect();
            channel.configureBlocking(false);
            channel.register(selector, SelectionKey.OP_WRITE);
        }  catch (IOException e) {
            e.printStackTrace();

            Closer.close(key);

            throw new ConnectionException("CONN_ERR", e.getMessage());
        }

    }

    protected void handleAccept(SelectionKey key) throws IOException {
        ServerSocketChannel channel = (ServerSocketChannel) key.channel();
        SocketChannel client = channel.accept();
        client.configureBlocking(false);

        client.register(key.selector(), SelectionKey.OP_READ);
        System.out.println("Establishing a connection " + client.getRemoteAddress());
    }

    void cleanUp() {
        for (SelectionKey key : selector.selectedKeys()) {
            Closer.close(key.channel());
        }

        Closer.close(selector);
        System.out.println("Resource cleaner.");
    }
}
