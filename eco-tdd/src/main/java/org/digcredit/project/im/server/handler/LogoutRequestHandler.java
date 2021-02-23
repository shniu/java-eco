package org.digcredit.project.im.server.handler;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import lombok.extern.slf4j.Slf4j;
import org.digcredit.project.im.protocol.request.LogoutRequestPacket;
import org.digcredit.project.im.protocol.response.LogoutResponsePacket;
import org.digcredit.project.im.util.SessionUtil;

/**
 * @author niushaohan
 * @date 2021/2/8 16
 */
@Slf4j
public class LogoutRequestHandler extends SimpleChannelInboundHandler<LogoutRequestPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LogoutRequestPacket msg) throws Exception {
        log.info("Receive a logout message: {}", msg);

        // 发送 logout message
        LogoutResponsePacket logoutResponsePacket = new LogoutResponsePacket();
        logoutResponsePacket.setCode(0);
        ChannelFuture channelFuture = ctx.channel().writeAndFlush(logoutResponsePacket);

        channelFuture.addListener(new GenericFutureListener<Future<? super Void>>() {
            @Override
            public void operationComplete(Future<? super Void> future) throws Exception {
                if (future.isSuccess()) {
                    // 清除掉信息，然后把 channel 断掉
                    // SessionUtil.unbindSession(ctx.channel());

                    ctx.close();
                }
            }
        });
    }
}
