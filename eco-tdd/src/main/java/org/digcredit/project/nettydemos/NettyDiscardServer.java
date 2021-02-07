package org.digcredit.project.nettydemos;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;

/**
 * @author niushaohan
 * @date 2021/2/3 16
 */
public class NettyDiscardServer {

    static class NettyDiscardHandler extends ChannelInboundHandlerAdapter {
        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            ByteBuf buf = (ByteBuf) msg;

            System.out.println("收到消息并丢弃: ");
            while (buf.isReadable()) {
                System.out.println((char) buf.readByte());
            }
            System.out.println();

            // buf.clear();
            super.channelRead(ctx, msg);
            ctx.channel().writeAndFlush(buf);
        }
    }

    public static void main(String[] args) {
        NioEventLoopGroup bossGroup = new NioEventLoopGroup(1);
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG, 10240)
                // .option(ChannelOption.SO_KEEPALIVE, true)
                // .option(ChannelOption.SO_TIMEOUT, 10000)
                .childOption(ChannelOption.SO_TIMEOUT, 10000)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new NettyDiscardHandler());
                    }
                });

        try {
            ChannelFuture channelFuture = serverBootstrap.bind(new InetSocketAddress(4455));
            channelFuture.sync();

            channelFuture.channel().closeFuture().sync();
        } catch (Exception e) {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }

    }
}
