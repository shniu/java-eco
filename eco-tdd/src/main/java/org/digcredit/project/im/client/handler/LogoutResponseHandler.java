package org.digcredit.project.im.client.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;
import org.digcredit.project.im.protocol.response.LogoutResponsePacket;

/**
 * @author niushaohan
 * @date 2021/2/8 16
 */
@Slf4j
public class LogoutResponseHandler extends SimpleChannelInboundHandler<LogoutResponsePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LogoutResponsePacket msg) throws Exception {
        log.info("Receive logout response.");
        if (msg.isSuccess()) {
            ctx.close();
        }
    }
}
