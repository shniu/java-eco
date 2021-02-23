package org.digcredit.project.im.common.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import org.digcredit.project.im.protocol.PacketCode;

import java.util.List;

/**
 * @author niushaohan
 * @date 2021/2/8 14
 */
public class PacketDecoder extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        out.add(PacketCode.decode(in));
    }
}
