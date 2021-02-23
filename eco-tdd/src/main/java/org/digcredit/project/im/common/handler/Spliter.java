package org.digcredit.project.im.common.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import lombok.extern.slf4j.Slf4j;
import org.digcredit.project.im.protocol.PacketCode;

/**
 * @author niushaohan
 * @date 2021/2/8 15
 */
@Slf4j
public class Spliter extends LengthFieldBasedFrameDecoder {
    private static final int LENGTH_FIELD_OFFSET = 7;
    private static final int LENGTH_FIELD_LENGTH = 4;

    public Spliter() {
        super(Integer.MAX_VALUE, LENGTH_FIELD_OFFSET, LENGTH_FIELD_LENGTH);
    }

    @Override
    public Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
        // Todo 这里使用 nc 连接后，发送消息时，会打印三次： 收到非法消息，直接拒绝断连，为什么？
        //  telnet 连接上之后发送数据也是会打印三次
        if (in.getInt(in.readerIndex()) != PacketCode.MAGIC_NUMBER) {
            log.info("收到非法消息，直接拒绝断连, {} -> {}", ctx.channel(), in);
            ctx.channel().close();
            return null;
        }

        return super.decode(ctx, in);
    }
}
