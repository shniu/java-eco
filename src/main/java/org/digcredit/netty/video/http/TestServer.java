package org.digcredit.netty.video.http;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class TestServer {
    public static void main(String[] args) throws Exception {
        // acceptor
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        // worker thread pool
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        // start server
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        // config thread group
        serverBootstrap.group(bossGroup, workerGroup)
                // config IO Model
                .channel(NioServerSocketChannel.class)
                // config channel handler
                .childHandler(new TestServerInit());

        // bind port and listen
        ChannelFuture future = serverBootstrap.bind(7777).sync();
        future.sync();
    }
}
