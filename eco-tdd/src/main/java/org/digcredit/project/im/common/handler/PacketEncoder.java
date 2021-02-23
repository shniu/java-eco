package org.digcredit.project.im.common.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import org.digcredit.project.im.protocol.Packet;
import org.digcredit.project.im.protocol.PacketCode;

/**
 * @author niushaohan
 * @date 2021/2/8 14
 */
public class PacketEncoder extends MessageToByteEncoder<Packet> {

    @Override
    protected void encode(ChannelHandlerContext ctx, Packet msg, ByteBuf out) throws Exception {
        PacketCode.encode(out, msg);
    }
}
