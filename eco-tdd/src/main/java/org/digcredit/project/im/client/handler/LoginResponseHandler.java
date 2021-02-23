package org.digcredit.project.im.client.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;
import org.digcredit.project.im.protocol.response.LoginResponsePacket;
import org.digcredit.project.im.server.Session;
import org.digcredit.project.im.util.LoginUtil;
import org.digcredit.project.im.util.SessionUtil;

import java.util.Date;

/**
 * @author niushaohan
 * @date 2021/2/8 14
 */
@Slf4j
public class LoginResponseHandler extends SimpleChannelInboundHandler<LoginResponsePacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginResponsePacket msg) throws Exception {
        if (msg.isSuccess()) {
            // Todo 登录成功之后可能还需要在本地保存 uid, token, 登录过期时间，用户基本信息等数据
            LoginUtil.markAsLogin(ctx.channel());

            Session session = Session.builder().userId(msg.getUserId()).username("").build();
            SessionUtil.bindSession(session, ctx.channel());

            log.info("[{}] 登录成功，响应: {}", new Date(), msg.toString());
        } else {
            log.info("[{}] 登录失败，响应: {}", new Date(), msg.toString());
        }
    }
}
