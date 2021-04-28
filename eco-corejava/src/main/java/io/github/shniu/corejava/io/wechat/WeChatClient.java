package io.github.shniu.corejava.io.wechat;

import com.google.common.collect.Lists;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.List;

/**
 * @author niushaohan
 * @date 2020/12/18 17
 */
public class WeChatClient {
    private User mine;
    private List<User> friends;

    private SocketChannel socketChannel;

    public WeChatClient() {
        friends = Lists.newArrayList();
    }

    public void startup() {

        try {
            socketChannel = SocketChannel.open();
            socketChannel.connect(new InetSocketAddress("127.0.0.1", 10000));
            // socketChannel.configureBlocking(false);

            // 把自己注册到服务端
            mine = new User();
            mine.setUid(null);
            mine.setNickname("Mike");
//        byte[] bytes = ChatUtil.getObjectMapper().writeValueAsBytes(mine);
//        ByteBuffer writeBuffer = ByteBuffer.allocate(1024);
//        writeBuffer.put(bytes);
//        writeBuffer.flip();
//
//        socketChannel.write(writeBuffer);
//        writeBuffer.clear();

            sendMsg(ChatUtil.getObjectMapper().writeValueAsString(mine));
            Thread.sleep(1000);

            socketChannel.close();

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

//        while (true) {
//            // 等待服务端的回复
//            ByteBuffer readBuffer = ByteBuffer.allocate(1024);
//            int read = 0;
////            while ((read = socketChannel.read(readBuffer)) != -1) {
////
////            }
//            read = socketChannel.read(readBuffer);
//            readBuffer.flip();
//
//            byte[] bytesRead = new byte[read];
//            readBuffer.get(bytesRead);
//
//            try {
//                User user = ChatUtil.getObjectMapper().readValue(bytesRead, User.class);
//                mine.setUid(user.getUid());
//                System.out.println("My info: " + mine);
//                break;
//            } catch (Exception e) {
//                System.exit(1);
//            }
//
//            try {
//                Thread.sleep(100);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }

//        try {
//            new CountDownLatch(1).await();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }

    private void sendMsg(String msg) {
        try {
            socketChannel.write(ByteBuffer.wrap(msg.getBytes()));
        } catch (IOException e) {
            System.out.println("Send msg error.");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        WeChatClient weChatClient = new WeChatClient();
        weChatClient.startup();
    }
}
