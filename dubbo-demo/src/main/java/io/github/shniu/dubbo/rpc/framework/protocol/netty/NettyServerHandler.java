package io.github.shniu.dubbo.rpc.framework.protocol.netty;

import io.github.shniu.dubbo.rpc.framework.protocol.Invocation;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author niushaohan
 * @date 2021/5/13 10
 */
@ChannelHandler.Sharable
public class NettyServerHandler extends ChannelInboundHandlerAdapter {

    private WorkerHandler workerHandler;

    public NettyServerHandler(WorkerHandler workerHandler) {
        this.workerHandler = workerHandler;
    }

    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        Invocation invocation = (Invocation) msg;
        System.out.println("Receive msg: " + invocation);
        workerHandler.handle(ctx, invocation);
    }
}
