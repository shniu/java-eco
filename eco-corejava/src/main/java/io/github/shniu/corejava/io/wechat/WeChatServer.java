package io.github.shniu.corejava.io.wechat;

import com.google.common.collect.Maps;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author niushaohan
 * @date 2020/12/18 17
 */
public class WeChatServer {
    private ConcurrentMap<String, User> registers;
    private Map<String, SocketChannel> onlineUsers;

    private ServerSocketChannel serverSocketChannel;
    private Selector selector;
    private int port;

    public WeChatServer(int port) throws IOException {
        registers = new ConcurrentHashMap<>();
        onlineUsers = Maps.newHashMap();

        this.port = port;

        selector = Selector.open();

        serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.bind(new InetSocketAddress(this.port));
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        System.out.println("WeChat Server listen on " + this.port + "...");
    }

    public void startup() {

        Iterator<SelectionKey> selectionKeyIterator;
        SelectionKey key;

        try {
            while (serverSocketChannel.isOpen()) {
                int select = selector.select(1000);
                // System.out.println("收到 ready 事件个数: " + select);

                selectionKeyIterator = selector.selectedKeys().iterator();
                while (selectionKeyIterator.hasNext()) {
                    key = selectionKeyIterator.next();
                    selectionKeyIterator.remove();

                    if (key.isAcceptable()) {
                        handleAccept(key);
                    } else if (key.isReadable()) {
                        handleRead(key);
                    } else if (key.isWritable()) {
                        System.out.println("Server is writable.");

                        key.interestOps(SelectionKey.OP_READ);
                    }
                }
            }

        } catch (IOException e) {
            //
            e.printStackTrace();
        }
    }

    private void handleAccept(SelectionKey key) throws IOException {
        SocketChannel socketChannel = ((ServerSocketChannel) key.channel()).accept();

        String address = socketChannel.socket().getInetAddress().toString() + ":" + socketChannel.socket().getPort();
        socketChannel.configureBlocking(false);

        System.out.println("建立连接：" + address);

        socketChannel.register(selector, SelectionKey.OP_READ, ByteBuffer.allocate(1024));

        // socketChannel.write(ByteBuffer.wrap(ChatUtil.welcomeBuf.getBytes()));
    }

    private void handleRead(SelectionKey selectionKey) throws IOException {
        SocketChannel socketChannel = (SocketChannel) selectionKey.channel();

        StringBuilder sb = new StringBuilder();
        ByteBuffer buffer = (ByteBuffer) selectionKey.attachment();
        buffer.clear();
        int read, total = 0;

        try {
            while ((read = socketChannel.read(buffer)) > 0) {
                buffer.flip();
                sb.append(StandardCharsets.UTF_8.newDecoder().decode(buffer));
                total += read;
                buffer.clear();
            }
        } catch (IOException e) {
            socketChannel.close();
            selectionKey.cancel();
            return;
        }

        System.out.println("Read len is " + total);

        if (read == -1) {
            socketChannel.close();
            selectionKey.cancel();
            return;
        }

        System.out.printf("收到 %s 发来的: %s\n", socketChannel.getRemoteAddress(), sb);
        buffer.clear();

        selectionKey.interestOps(selectionKey.interestOps() | SelectionKey.OP_WRITE);

//        byte[] bytes = new byte[read];
//        buffer.get(bytes);
//        User user = ChatUtil.getObjectMapper().readValue(bytes, User.class);
//        System.out.println("Received: " + user);
//        user.setUid("112233");
//
//        registers.put("112233", user);
//
//        selectionKey.interestOps(selectionKey.interestOps() | SelectionKey.OP_WRITE);
    }

    private void handleWrite(SelectionKey selectionKey) throws IOException {
        SocketChannel socketChannel = (SocketChannel) selectionKey.channel();

        ByteBuffer writeBuffer = ByteBuffer.allocate(1024);
        writeBuffer.put(ChatUtil.getObjectMapper().writeValueAsBytes(registers.get("112233")));
        writeBuffer.flip();
        socketChannel.write(writeBuffer);
        System.out.println("Sent: " + registers.get("112233"));
    }

    public static void main(String[] args) throws IOException {
        WeChatServer chatServer = new WeChatServer(10000);
        chatServer.startup();
    }
}
