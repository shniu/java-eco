package org.digcredit.project.nettydemos;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

import java.net.InetSocketAddress;
import java.util.Date;
import java.util.Scanner;

/**
 * @author niushaohan
 * @date 2021/2/3 18
 */
public class NettyEchoClient {

    static class NettyEchoClientHandler extends ChannelInboundHandlerAdapter {
        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            ByteBuf buf = (ByteBuf) msg;

            int readableBytes = buf.readableBytes();
            byte[] bytes = new byte[readableBytes];
            buf.getBytes(0, bytes);

            System.out.println("Client received: " + new String(bytes));

            super.channelRead(ctx, msg);
        }
    }

    public static void main(String[] args) {
        NioEventLoopGroup group = new NioEventLoopGroup();

        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(group)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new NettyEchoClientHandler());
                    }
                });

        ChannelFuture channelFuture = bootstrap.connect(new InetSocketAddress(4455));
        channelFuture.addListener(new GenericFutureListener<Future<? super Void>>() {
            @Override
            public void operationComplete(Future<? super Void> future) throws Exception {
                if (future.isSuccess()) {
                    System.out.println("连接成功");
                } else {
                    System.out.println("连接失败");
                }
            }
        });

        try {
            channelFuture.sync();

            Channel channel = channelFuture.channel();

            Scanner scanner = new Scanner(System.in);
            System.out.println("请输入：");
            while (scanner.hasNext()) {
                String line = scanner.next();
                String content = new Date() + " >> " + line;

                ByteBuf byteBuf = channel.alloc().buffer();
                byteBuf.writeBytes(content.getBytes());
                channel.writeAndFlush(byteBuf);

                System.out.println("请输入：");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            group.shutdownGracefully();
        }
    }
}
