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
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

import java.net.InetSocketAddress;

/**
 * @author niushaohan
 * @date 2021/2/3 17
 */
public class NettyEchoServer {

    static class NettyEchoHandler extends ChannelInboundHandlerAdapter {
        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            ByteBuf buf = (ByteBuf) msg;
            System.out.println("msg type " + (buf.hasArray() ? "堆内存" : "直接内存"));

            int readableBytes = buf.readableBytes();
            byte[] bytes = new byte[readableBytes];
            buf.getBytes(0, bytes);
            System.out.println("server received: " + new String(bytes));

            System.out.println("写回前的 msg.refCnt: " + ((ByteBuf) msg).refCnt());
            ChannelFuture channelFuture = ctx.writeAndFlush(msg);
            channelFuture.addListener(new GenericFutureListener<Future<? super Void>>() {
                @Override
                public void operationComplete(Future<? super Void> future) throws Exception {
                    System.out.println("写回后 msg.refCnt: " + ((ByteBuf) msg).refCnt());
                }
            });
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
                        ch.pipeline().addLast(new NettyEchoHandler());
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
