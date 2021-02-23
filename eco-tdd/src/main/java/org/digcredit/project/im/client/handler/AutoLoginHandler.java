package org.digcredit.project.im.client.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;
import org.digcredit.project.im.protocol.PacketCode;
import org.digcredit.project.im.protocol.request.LoginRequestPacket;

import java.util.Date;

@Slf4j
public class AutoLoginHandler extends ChannelInboundHandlerAdapter {

    /**
     * 在客户端连接成功服务端时调用，可以在这个时候发送一些数据
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        // super.channelActive(ctx);
        log.info("[{}] channel active, 客户端开始登录", new Date());

        // 构造请求 Payload
        ByteBuf buf = getLoginBuf(ctx);

        // Send data
        ctx.writeAndFlush(buf);
    }

    private ByteBuf getLoginBuf(ChannelHandlerContext ctx) {
        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();
        loginRequestPacket.setUsername("Jim");
        loginRequestPacket.setPassword("111");

        return PacketCode.encode(ctx.alloc().ioBuffer(), loginRequestPacket);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
        log.info("[{}] channel inactive", new Date());
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        log.info("[{}] channel read", new Date());
        super.channelRead(ctx, msg);
        // super.channelRead(ctx, msg);
//        ByteBuf buf = (ByteBuf) msg;
//
//        Packet packet = PacketCode.decode(buf);
//        if (packet instanceof LoginResponsePacket) {
//            LoginResponsePacket loginResponsePacket = (LoginResponsePacket) packet;
//            if (loginResponsePacket.isSuccess()) {
//                LoginUtil.markAsLogin(ctx.channel());
//                log.info("[{}] 登录成功，响应: {}", new Date(), packet.toString());
//            } else {
//                log.info("[{}] 登录失败，响应: {}", new Date(), packet.toString());
//            }
//        } else if (packet instanceof MessageResponsePacket) {
//            log.info("[{}] 客户端收到响应: {}", new Date(), packet.toString());
//        } else {
//            log.info("[{}] channel read: {}", new Date(), buf.toString(CharsetUtil.UTF_8));
//        }
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        super.channelRegistered(ctx);
        log.info("[{}] channel registered", new Date());
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        super.channelUnregistered(ctx);
        log.info("[{}] channel unregistered", new Date());
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        super.channelReadComplete(ctx);
        log.info("[{}] channel read complete", new Date());
    }
}
