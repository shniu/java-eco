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
import org.digcredit.project.im.client.handler.AutoLoginHandler;
import org.digcredit.project.im.client.handler.LogoutResponseHandler;
import org.digcredit.project.im.common.handler.PacketDecoder;
import org.digcredit.project.im.common.handler.PacketEncoder;
import org.digcredit.project.im.client.handler.LoginResponseHandler;
import org.digcredit.project.im.client.handler.MessageResponseHandler;
import org.digcredit.project.im.common.handler.Spliter;
import org.digcredit.project.im.protocol.PacketCode;
import org.digcredit.project.im.protocol.request.LogoutRequestPacket;
import org.digcredit.project.im.protocol.request.MessageRequestPacket;
import org.digcredit.project.im.util.LoginUtil;
import org.digcredit.project.im.util.SessionUtil;

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

                            // ch.pipeline().addLast(new LoggingHandler());
                            // ch.pipeline().addLast(new ClientHandler());

                            ch.pipeline().addLast(new Spliter());
                            ch.pipeline().addLast(new PacketDecoder());
                            ch.pipeline().addLast(new AutoLoginHandler());
                            ch.pipeline().addLast(new LoginResponseHandler());
                            ch.pipeline().addLast(new MessageResponseHandler());
                            ch.pipeline().addLast(new LogoutResponseHandler());
                            ch.pipeline().addLast(new PacketEncoder());
                        }
                    });

            ChannelFuture future = b.connect("127.0.0.1", 8889).sync();

            future.addListener(future1 -> {
                if (future1.isSuccess()) {
                    log.info("Connect 服务端成功!");
                    // 开启一个线程收发数据
                    Channel channel = ((ChannelFuture) future1).channel();
                    startConsole(channel);
                } else {
                    log.warn("Connect 服务端失败！");
                }
            });

            future.channel().closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
        }

    }

    private static void startConsole(Channel channel) {
        log.info("Start chat console...");
        new Thread(() -> {
            while (true) {
                // 登录成功后，才可以发送消息
                if (LoginUtil.hasLogin(channel)) {
                    log.info("请输入：");
                    Scanner scanner = new Scanner(System.in);
                    String userId = scanner.next();

                    if ("quit".equalsIgnoreCase(userId)) {
                        // 需要发送一个退出请求
                        LogoutRequestPacket logoutRequestPacket = new LogoutRequestPacket();
                        logoutRequestPacket.setReason("i'm quit");
                        ByteBuf buf = PacketCode.encode(channel.alloc().ioBuffer(), logoutRequestPacket);
                        channel.writeAndFlush(buf);

                        break;
                    }

                    String message = scanner.nextLine();

                    MessageRequestPacket messageRequest = new MessageRequestPacket();
                    messageRequest.setFrom(SessionUtil.getSession(channel).getUserId());
                    messageRequest.setTo(userId);
                    messageRequest.setMessage(message);
                    ByteBuf buf = PacketCode.encode(channel.alloc().ioBuffer(), messageRequest);
                    channel.writeAndFlush(buf);
                } else {
                    // 如果没有登录需要转移到登录的 UI
                }
            }
        }).start();
    }
}
