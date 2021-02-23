package org.digcredit.project.im.client.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;
import org.digcredit.project.im.protocol.response.MessageResponsePacket;

import java.util.Date;

/**
 * @author niushaohan
 * @date 2021/2/8 14
 */
@Slf4j
public class MessageResponseHandler extends SimpleChannelInboundHandler<MessageResponsePacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageResponsePacket msg) throws Exception {
        log.info("[{}] 收到来自 {} 的消息: {}",
                new Date(), msg.getFromUserId(), msg.getMessage());
    }
}
