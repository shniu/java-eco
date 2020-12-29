package io.github.shniu.flashchat.server;

import io.github.shniu.flashchat.common.net.AbstractEndpoint;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.AbstractSelectableChannel;
import java.nio.channels.spi.AbstractSelector;
import java.nio.channels.spi.SelectorProvider;

/**
 * @author niushaohan
 * @date 2020/12/22 09
 */
public class FlashChatServer extends AbstractEndpoint {

    public FlashChatServer(String endpointId, int port) throws IOException {
        super(endpointId, port);
    }

    @Override
    protected Selector selector() throws IOException {
        AbstractSelector selector = SelectorProvider.provider().openSelector();
        channel.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println("Channel register on the selector.");
        return selector;
    }

    @Override
    protected AbstractSelectableChannel channel(InetSocketAddress address) throws IOException {
        ServerSocketChannel channel = ServerSocketChannel.open();
        channel.configureBlocking(false);
        channel.bind(address);

        System.out.println("Flash chat server listen on " + address.getPort());
        return channel;
    }

    @Override
    protected void handleInboundData(SelectionKey sender, byte[] bytes) throws IOException {
        System.out.println("Inbound data " + new String(bytes));

        for (SelectionKey registeredKey : sender.selector().keys()) {
            if (registeredKey.channel() instanceof ServerSocketChannel) {
                continue;
            }

            if (registeredKey.equals(sender)) {
                continue;
            }

            SocketChannel socketChannel = (SocketChannel) registeredKey.channel();

            // Send to all other clients that have registered.
            registeredKey.attach(ByteBuffer.wrap(bytes));
            handleWrite(registeredKey);
            System.out.println(socketChannel.socket().getRemoteSocketAddress() + " write finished.");
        }
    }

    @Override
    protected void handleWrite(SelectionKey key) throws IOException {
        ByteBuffer buffer = (ByteBuffer) key.attachment();
        SocketChannel channel = (SocketChannel) key.channel();
        channel.write(buffer);
        key.interestOps(SelectionKey.OP_READ);
    }
}
