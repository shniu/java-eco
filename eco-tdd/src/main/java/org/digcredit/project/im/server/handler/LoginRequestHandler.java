package org.digcredit.project.im.server.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;
import org.digcredit.project.im.protocol.Packet;
import org.digcredit.project.im.protocol.PacketCode;
import org.digcredit.project.im.protocol.request.LoginRequestPacket;
import org.digcredit.project.im.protocol.response.LoginResponsePacket;
import org.digcredit.project.im.server.Session;
import org.digcredit.project.im.util.LoginUtil;
import org.digcredit.project.im.util.SessionUtil;

import java.util.Date;
import java.util.UUID;

/**
 * @author niushaohan
 * @date 2021/2/8 14
 */
@Slf4j
public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequestPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginRequestPacket msg) throws Exception {
        // 登录响应
        LoginResponsePacket loginResponsePacket = new LoginResponsePacket();
        loginResponsePacket.setCode(-1);
        loginResponsePacket.setData("Login Failure");

        if (loginValid(msg)) {
            Session session = Session.builder()
                    .userId(UUID.randomUUID().toString())
                    .username(msg.getUsername())
                    .build();

            // 登录成功
            log.info("[{}] 登录成功: {}", new Date(), msg.toString());
            loginResponsePacket.setCode(0);
            loginResponsePacket.setUserId(session.getUserId());
            loginResponsePacket.setData("Login Success");

            // 登录成功的标记
            LoginUtil.markAsLogin(ctx.channel());

            SessionUtil.bindSession(session, ctx.channel());
        }

        log.info("[{}] 回复客户端 -> {}", new Date(), loginResponsePacket);
        ByteBuf buf = PacketCode.encode(ctx.alloc().ioBuffer(), loginResponsePacket);
        ctx.writeAndFlush(buf);
    }

    private boolean loginValid(final Packet packet) {
        return true;
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        SessionUtil.unbindSession(ctx.channel());
        log.info("channel inactive, unbind session.");
    }
}
