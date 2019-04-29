package org.digcredit.netty.im.pipeline;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.digcredit.netty.im.protocol.Packet;
import org.digcredit.netty.im.protocol.PacketCodeC;
import org.digcredit.netty.im.protocol.request.LoginRequestPacket;
import org.digcredit.netty.im.protocol.request.MessageRequestPacket;
import org.digcredit.netty.im.protocol.response.LoginResponsePacket;
import org.digcredit.netty.im.protocol.response.MessageResponsePacket;

import java.util.Date;

public class ServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        ByteBuf byteBuf = (ByteBuf) msg;

        Packet packet = PacketCodeC.INSTANCE.decode(byteBuf);

        if (packet instanceof LoginRequestPacket) {
            LoginRequestPacket loginRequestPacket = (LoginRequestPacket) packet;

            LoginResponsePacket loginResponsePacket = new LoginResponsePacket();
            loginRequestPacket.setVersion(packet.getVersion());
            if (valid(loginRequestPacket)) {
                System.out.println("用户认证成功");
                // 登陆成功，返回响应
                loginResponsePacket.setSuccess(true);
                loginResponsePacket.setReason("");
            } else {
                System.out.println("用户认证失败");
                loginResponsePacket.setSuccess(false);
                loginResponsePacket.setReason("用户名密码错误");
            }

            ByteBuf responseByteBuf = PacketCodeC.INSTANCE.encode(ctx.alloc(), loginResponsePacket);
            ctx.channel().writeAndFlush(responseByteBuf);
        } else if (packet instanceof MessageRequestPacket) {
            MessageRequestPacket requestPacket = (MessageRequestPacket) packet;
            System.out.println(new Date() + ": 收到来自客户端的请求：" + requestPacket.getMessage());

            MessageResponsePacket messageResponsePacket = new MessageResponsePacket();
            messageResponsePacket.setMessage("服务端回复[" + requestPacket.getMessage() + "]");
            ByteBuf byteBuf1 = PacketCodeC.INSTANCE.encode(ctx.alloc(), messageResponsePacket);
            ctx.channel().writeAndFlush(byteBuf1);
        }
    }

    private boolean valid(LoginRequestPacket loginRequestPacket) {
        return true;
    }
}
