package org.digcredit.netty.im.pipeline;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.digcredit.netty.im.protocol.Packet;
import org.digcredit.netty.im.protocol.PacketCodeC;
import org.digcredit.netty.im.protocol.request.LoginRequestPacket;
import org.digcredit.netty.im.protocol.response.LoginResponsePacket;

import java.util.Date;
import java.util.UUID;

public class ClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        System.out.println(new Date() + ": 客户端开始登录");

        LoginRequestPacket loginRequestPackage = new LoginRequestPacket();
        loginRequestPackage.setUserId(UUID.randomUUID().toString());
        loginRequestPackage.setUsername("netty");
        loginRequestPackage.setPassword("pwd");

        ByteBuf byteBuf = PacketCodeC.INSTANCE.encode(ctx.alloc(), loginRequestPackage);
        ctx.channel().writeAndFlush(byteBuf);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        System.out.println("Receive...");
        ByteBuf byteBuf = (ByteBuf) msg;

        Packet packet = PacketCodeC.INSTANCE.decode(byteBuf);
        if (packet instanceof LoginResponsePacket) {
            LoginResponsePacket loginResponsePacket = (LoginResponsePacket) packet;
            if (loginResponsePacket.isSuccess()) {
                System.out.println(new Date() + ": 登陆成功");
            } else {
                System.out.println(new Date() + ": 登陆失败，原因：" + loginResponsePacket.getReason());
            }
        }
    }
}
