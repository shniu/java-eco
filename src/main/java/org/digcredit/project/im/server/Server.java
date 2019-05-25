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
import org.digcredit.project.im.server.handler.OutboundServerHandler;
import org.digcredit.project.im.server.handler.PacketDecoder;
import org.digcredit.project.im.server.handler.ServerHandler;

@Slf4j
public class Server {

    public static void main(String[] args) throws InterruptedException {

        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        final ServerHandler serverHandler = new ServerHandler();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new ChannelInitializer<NioSocketChannel>() {
                        @Override
                        protected void initChannel(NioSocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new PacketDecoder());
                            ch.pipeline().addLast(serverHandler);

                            ch.pipeline().addLast(new OutboundServerHandler());
                        }
                    });
            ChannelFuture future = b.bind(8888).sync();
            future.addListener(future1 -> {
                if (future1.isSuccess()) {
                    log.info("IM Server start success on port 8888");
                }
            });
            future.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
