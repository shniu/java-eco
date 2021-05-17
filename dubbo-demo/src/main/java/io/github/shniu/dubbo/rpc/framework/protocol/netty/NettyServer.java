package io.github.shniu.dubbo.rpc.framework.protocol.netty;

import io.github.shniu.dubbo.rpc.framework.protocol.Protocol;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import io.netty.util.concurrent.FutureListener;

import java.net.URL;

/**
 * @author niushaohan
 * @date 2021/5/13 09
 */
public final class NettyServer implements Protocol {

    WorkerHandler workerHandler = new DispatchHandler();

    @Override
    public void start(String hostname, int port) {
        NettyServerHandler nettyServerHandler = new NettyServerHandler(workerHandler);

        ServerBootstrap bootstrap = new ServerBootstrap();

        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();
        bootstrap.group(bossGroup, workerGroup);
        bootstrap.channel(NioServerSocketChannel.class);
        bootstrap.childOption(ChannelOption.TCP_NODELAY, true);
        bootstrap.childOption(ChannelOption.SO_REUSEADDR, true);
        bootstrap.childOption(ChannelOption.SO_KEEPALIVE, false);
        bootstrap.childOption(ChannelOption.SO_RCVBUF, 2048);
        bootstrap.childOption(ChannelOption.SO_SNDBUF, 2048);
        bootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel socketChannel) throws Exception {
                ChannelPipeline pipeline = socketChannel.pipeline();
                pipeline.addLast(new ObjectDecoder(
                        ClassResolvers.weakCachingConcurrentResolver(this.getClass().getClassLoader())));
                pipeline.addLast(new ObjectEncoder());

                pipeline.addLast(nettyServerHandler);
            }
        });

        ChannelFuture channelFuture = bootstrap.bind(hostname, port)
                .syncUninterruptibly()
                .addListener(future -> {
                    System.out.println("RPC 服务启动成功！");
                });

        channelFuture.channel().closeFuture()
                .addListener(future -> {
                    System.out.println("RPC 服务关闭！");

                    bossGroup.shutdownGracefully();
                    workerGroup.shutdownGracefully();
                });
    }
}
