package org.digcredit.project.im.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import lombok.extern.slf4j.Slf4j;
import org.digcredit.project.im.common.handler.PacketDecoder;
import org.digcredit.project.im.common.handler.PacketEncoder;
import org.digcredit.project.im.common.handler.Spliter;
import org.digcredit.project.im.server.handler.AuthHandler;
import org.digcredit.project.im.server.handler.LoginRequestHandler;
import org.digcredit.project.im.server.handler.LogoutRequestHandler;
import org.digcredit.project.im.server.handler.MessageRequestHandler;

@Slf4j
public class Server {

    public static void main(String[] args) throws InterruptedException {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new ChannelInitializer<NioSocketChannel>() {
                        @Override
                        protected void initChannel(NioSocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new Spliter());
                            ch.pipeline().addLast(new PacketDecoder());

                            // ch.pipeline().addLast(new ServerHandler());

                            // ch.pipeline().addLast(new OutboundServerHandler());

                            ch.pipeline().addLast(new LoginRequestHandler());
                            ch.pipeline().addLast(new AuthHandler());
                            ch.pipeline().addLast(new MessageRequestHandler());
                            ch.pipeline().addLast(new LogoutRequestHandler());

                            ch.pipeline().addLast(new PacketEncoder());
                        }
                    });
            ChannelFuture future = b.bind(8889).sync();
            future.addListener(future1 -> {
                if (future1.isSuccess()) {
                    log.info("IM Server start success on port 8889");
                }
            });
            future.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
