package org.digcredit.project.im.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.digcredit.project.im.protocol.PacketCode;
import org.digcredit.project.im.protocol.request.MessageRequestPacket;

import java.util.Date;
import java.util.Scanner;

@Slf4j
public class Client {
    public static void main(String[] args) throws InterruptedException {

        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            Bootstrap b = new Bootstrap();
            b.group(workerGroup)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            log.info("{} client: init channel", new Date());
                            ch.pipeline().addLast(new ClientHandler());
                        }
                    });

            ChannelFuture future = b.connect("127.0.0.1", 8888).sync();
            future.addListener(future1 -> {
                if (future1.isSuccess()) {
                    log.info("Connect 服务端成功!");
                    // 开启一个线程收发数据
                    Channel channel = ((ChannelFuture) future1).channel();
                    startConsole(channel);
                }
            });
            future.channel().closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
        }

    }

    private static void startConsole(Channel channel) {
        log.info("Start chat console");
        new Thread(() -> {
            while (true) {
                log.info("请输入：");
                Scanner scanner = new Scanner(System.in);
                String message = scanner.nextLine();

                MessageRequestPacket messageRequest = new MessageRequestPacket();
                messageRequest.setMessage(message);
                ByteBuf buf = PacketCode.encode(channel.alloc(), messageRequest);
                channel.writeAndFlush(buf);
            }
        }).start();
    }
}
