package io.github.shniu.dubbo.rpc.framework.protocol.netty;

import io.github.shniu.dubbo.rpc.framework.protocol.Invocation;
import io.netty.channel.ChannelHandlerContext;

/**
 * @author niushaohan
 * @date 2021/5/13 10
 */
public interface WorkerHandler {
    void handle(ChannelHandlerContext ctx, Invocation invocation);
}
