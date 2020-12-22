package io.github.shniu.flashchat.client;

import com.google.common.collect.Lists;
import io.github.shniu.flashchat.common.net.AbstractEndpoint;
import io.github.shniu.flashchat.common.net.ResponseHandler;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.AbstractSelectableChannel;
import java.util.LinkedList;

import static java.nio.channels.SelectionKey.OP_WRITE;

/**
 * @author niushaohan
 * @date 2020/12/22 10
 */
public class FlashChatClient extends AbstractEndpoint {
    private final ResponseHandler responseHandler;
    private final LinkedList<String> messages;

    public FlashChatClient(String endpointId, InetSocketAddress address, ResponseHandler responseHandler) throws IOException {
        super(endpointId, address);
        this.responseHandler = responseHandler;
        messages = Lists.newLinkedList();
    }

    @Override
    protected Selector selector() throws IOException {
        Selector selector = Selector.open();
        channel.register(selector, SelectionKey.OP_CONNECT);
        return selector;
    }

    @Override
    protected AbstractSelectableChannel channel(InetSocketAddress address) throws IOException {
        SocketChannel channel = SocketChannel.open();
        channel.configureBlocking(false);
        channel.connect(address);

        System.out.println("Connect " + address);
        return channel;
    }

    @Override
    protected void handleInboundData(SelectionKey key, byte[] bytes) throws IOException {
        responseHandler.onMessage(new String(bytes));
    }

    @Override
    protected void handleWrite(SelectionKey key) throws IOException {
        SocketChannel channel = (SocketChannel) key.channel();

        while (!messages.isEmpty()) {
            String message = messages.poll();
            channel.write(ByteBuffer.wrap(message.getBytes()));
        }
        key.interestOps(SelectionKey.OP_READ);
    }

    public void send(String message) {
        messages.add(message);
        SelectionKey key = channel.keyFor(selector);
        key.interestOps(OP_WRITE);
        System.out.println(this.endpointId + " send: " + message);
    }
}
