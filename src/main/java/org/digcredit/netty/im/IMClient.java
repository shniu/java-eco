package org.digcredit.netty.im;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.digcredit.netty.im.pipeline.ClientHandler;
import org.digcredit.netty.im.pipeline.FirstClientHandler;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class IMClient {

    public static final int MAX_RETRY = 5;

    public static void main(String[] args) {
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(workerGroup)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        System.out.println("client handler");
                        // ch.pipeline().addLast(new FirstClientHandler());
                        ch.pipeline().addLast(new ClientHandler());
                    }
                });
        connect(bootstrap, "localhost", 8000, MAX_RETRY);
    }

    private static void connect(Bootstrap bootstrap, String host, int port, int retry) {
        bootstrap.connect(host, port).addListener(future -> {
            if (future.isSuccess()) {
                System.out.println("conn success");
            } else if (retry == 0) {
                System.out.println("retry 次数用完，放弃连接");
                throw new RuntimeException("无法连接远程主机");
            } else {
                int order = (MAX_RETRY - retry) + 1;
                // 每次重连的间隔
                int delay = 1 << order;
                System.out.println(new Date() + ": 连接失败，第" + order + "次重连...");
                bootstrap.config().group().schedule(
                        () -> connect(bootstrap, host, port, retry - 1),
                        delay,
                        TimeUnit.SECONDS
                );
            }
        });
    }
}
