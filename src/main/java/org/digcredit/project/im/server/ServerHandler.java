package org.digcredit.project.im.server;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;
import org.digcredit.project.im.protocol.Packet;
import org.digcredit.project.im.protocol.PacketCode;
import org.digcredit.project.im.protocol.request.LoginRequestPacket;
import org.digcredit.project.im.protocol.request.MessageRequestPacket;
import org.digcredit.project.im.protocol.response.LoginResponsePacket;
import org.digcredit.project.im.protocol.response.MessageResponsePacket;

import java.util.Date;

@Slf4j
public class ServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        log.info("[{}] active", new Date());
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        super.channelRegistered(ctx);
        log.info("[{}] registered", new Date());
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        // super.channelRead(ctx, msg);

        ByteBuf byteBuf = (ByteBuf) msg;
        Packet packet = PacketCode.decode(byteBuf);

        // 登录验证
        if (packet instanceof LoginRequestPacket) {
            // 登录响应
            LoginResponsePacket loginResponsePacket = new LoginResponsePacket();
            loginResponsePacket.setCode(-1);

            if (loginValid(packet)) {
                // 登录成功
                log.info("[{}] 登录成功: {}", new Date(), packet.toString());
                loginResponsePacket.setCode(0);
                loginResponsePacket.setData("oo-xx");
            }

            log.info("[{}] 回复客户端", new Date());
            ByteBuf buf = PacketCode.encode(ctx.alloc(), loginResponsePacket);
            ctx.writeAndFlush(buf);
        } else if (packet instanceof MessageRequestPacket) {
            log.info("{} 收到客户端信息：{}", new Date(), packet.toString());
            log.info("{} 回复客户端", new Date());

            MessageResponsePacket responsePacket = new MessageResponsePacket();
            responsePacket.setMessage("我收到消息了");
            ByteBuf buf = PacketCode.encode(ctx.alloc(), responsePacket);
            ctx.writeAndFlush(buf);
        }
    }

    private boolean loginValid(final Packet packet) {
        return true;
    }

    private ByteBuf getReply(ChannelHandlerContext ctx) {
        ByteBuf buffer = ctx.alloc().buffer();
        buffer.writeBytes("Thanks".getBytes(CharsetUtil.UTF_8));
        return buffer;
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        super.channelReadComplete(ctx);
        log.info("[{}] complete", new Date());
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
        log.info("[{}] inactive", new Date());
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        super.channelUnregistered(ctx);
        log.info("[{}] unregistered", new Date());
    }
}
