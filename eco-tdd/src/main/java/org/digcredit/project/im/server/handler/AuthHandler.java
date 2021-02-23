package org.digcredit.project.im.server.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;
import org.digcredit.project.im.util.LoginUtil;

/**
 * @author niushaohan
 * @date 2021/2/8 15
 */
@Slf4j
public class AuthHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (!LoginUtil.hasLogin(ctx.channel())) {
            // 当然也可以做其他处理，根据实际业务场景来处理
            ctx.channel().close();
        } else {
            // 校验通过后，移除 AuthHandler，这样就可以不需要做重复用户校验
            ctx.pipeline().remove(this);
            super.channelRead(ctx, msg);
        }
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        if (LoginUtil.hasLogin(ctx.channel())) {
            log.info("当前连接登录验证完毕，无需再次验证, AuthHandler 被移除");
        } else {
            log.info("无登录验证，强制关闭连接!");
        }
    }
}
