package io.github.shniu.dubbo.rpc.framework.protocol.netty;

import io.github.shniu.dubbo.rpc.framework.protocol.Invocation;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;

/**
 * @author niushaohan
 * @date 2021/5/13 10
 */
public class NettyClientHandler extends ChannelInboundHandlerAdapter implements Callable<Object> {

    private ChannelHandlerContext context;
    private Invocation invocation;
    private Object result;

    CountDownLatch latch = new CountDownLatch(1);

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        context = ctx;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        result = msg;
        latch.countDown();
    }

    @Override
    public Object call() throws Exception {
        context.writeAndFlush(this.invocation);
        latch.await();
        return result;
    }

    public void setInvocation(Invocation invocation) {
        this.invocation = invocation;
    }
}
