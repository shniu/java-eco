package org.digcredit.netty.im;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.digcredit.netty.im.pipeline.ServerHandler;

public class IMServer {

    public static void main(String[] args) {
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        System.out.println("Server handler");
                        // ch.pipeline().addLast(new FirstServerHandler());
                        ch.pipeline().addLast(new ServerHandler());
                    }
                });
        ChannelFuture channelFuture = serverBootstrap.bind(8000);
        channelFuture.addListener(future -> {
            if (future.isSuccess()) {
                System.out.println("server start success, bind 8000");
            }
        });

    }
}
