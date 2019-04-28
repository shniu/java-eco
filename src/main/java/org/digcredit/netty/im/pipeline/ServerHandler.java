package org.digcredit.netty.im.pipeline;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.digcredit.netty.im.protocol.Packet;
import org.digcredit.netty.im.protocol.PacketCodeC;
import org.digcredit.netty.im.protocol.request.LoginRequestPacket;
import org.digcredit.netty.im.protocol.response.LoginResponsePacket;

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
        }
    }

    private boolean valid(LoginRequestPacket loginRequestPacket) {
        return true;
    }
}
