package org.digcredit.project.im.server.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;
import org.digcredit.project.im.protocol.PacketCode;
import org.digcredit.project.im.protocol.request.MessageRequestPacket;
import org.digcredit.project.im.protocol.response.MessageResponsePacket;
import org.digcredit.project.im.server.Session;
import org.digcredit.project.im.util.SessionUtil;

import java.util.Date;

/**
 * @author niushaohan
 * @date 2021/2/8 14
 */
@Slf4j
public class MessageRequestHandler extends SimpleChannelInboundHandler<MessageRequestPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageRequestPacket msg) throws Exception {
        log.info("{} 收到客户端信息：{}", new Date(), msg.toString());

        Session session = SessionUtil.getSession(msg.getFrom());


        String toUserId = msg.getTo();
        Channel toChannel = SessionUtil.getChannel(toUserId);
        if (toChannel != null && SessionUtil.hasLogin(toChannel)) {
            MessageResponsePacket responsePacket = new MessageResponsePacket();
            responsePacket.setFromUserId(msg.getFrom());
            responsePacket.setFromUsername(session.getUsername());
            responsePacket.setMessage(msg.getMessage());

            log.info("回复客户端：{}", responsePacket);

            ByteBuf buf = PacketCode.encode(ctx.alloc().ioBuffer(), responsePacket);
            // ctx.writeAndFlush(buf);
            // ctx.write(buf);
            toChannel.writeAndFlush(buf);
        } else {
            log.info("[{}] 不在线，发送失败！", toUserId);
        }
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        // ctx.fireChannelReadComplete();
        ctx.channel().flush();
    }

}
