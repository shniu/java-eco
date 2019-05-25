package org.digcredit.project.im.server.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

@Slf4j
public class OutboundServerHandler extends ChannelOutboundHandlerAdapter {

    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        log.info("[{}] Outbound handler...", new Date());
        super.write(ctx, msg, promise);
    }
}
