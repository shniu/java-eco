package io.github.shniu.dubbo.rpc.framework.protocol.netty;

import io.github.shniu.dubbo.rpc.framework.URL;
import io.github.shniu.dubbo.rpc.framework.protocol.Invocation;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author niushaohan
 * @date 2021/5/13 09
 */
public class NettyClient {

    NettyClientHandler clientHandler = null;
    private static ExecutorService executorService = Executors.newCachedThreadPool();

    public void start(String hostname, int port) throws InterruptedException {
        clientHandler = new NettyClientHandler();

        Bootstrap bootstrap = new Bootstrap();

        bootstrap.group(new NioEventLoopGroup())
                .channel(NioSocketChannel.class)
                .option(ChannelOption.TCP_NODELAY, true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        ChannelPipeline pipeline = socketChannel.pipeline();
                        pipeline.addLast(new ObjectDecoder(
                                ClassResolvers.weakCachingConcurrentResolver(this.getClass().getClassLoader())));
                        pipeline.addLast(new ObjectEncoder());

                        pipeline.addLast(clientHandler);
                    }
                });

        bootstrap.connect(hostname, port)
                .sync()
                .addListener(f -> {
                    System.out.println("RPC Client 连接成功！");
                });
    }

    public Object send(URL url, Invocation invocation) throws InterruptedException {
        if (clientHandler == null) {
            start(url.getIp(), url.getPort());
        }

        clientHandler.setInvocation(invocation);

        try {
            return executorService.submit(clientHandler).get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        return null;
    }
}
