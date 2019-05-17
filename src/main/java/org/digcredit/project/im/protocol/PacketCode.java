package org.digcredit.project.im.protocol;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import org.digcredit.project.im.protocol.request.LoginRequestPacket;
import org.digcredit.project.im.protocol.request.MessageRequestPacket;
import org.digcredit.project.im.protocol.response.LoginResponsePacket;
import org.digcredit.project.im.protocol.response.MessageResponsePacket;
import org.digcredit.project.im.protocol.serializer.Serializer;

import java.util.HashMap;
import java.util.Map;

public class PacketCode {
    private static final int MAGIC_NUMBER = 0x12345678;

    private static Map<Byte, Class<? extends Packet>> packetMap = new HashMap<>();

    static {
        packetMap.put(Command.LOGIN_REQUEST, LoginRequestPacket.class);
        packetMap.put(Command.LOGIN_RESPONSE, LoginResponsePacket.class);
        packetMap.put(Command.MESSAGE_REQUEST, MessageRequestPacket.class);
        packetMap.put(Command.MESSAGE_RESPONSE, MessageResponsePacket.class);
    }

    // 编码
    public static ByteBuf encode(final ByteBufAllocator allocator, final Packet packet) {
        ByteBuf buf = allocator.ioBuffer();

        // 序列化数据
        byte[] packetBytes = Serializer.DEFAULT.serialize(packet);

        buf.writeInt(MAGIC_NUMBER);
        buf.writeByte(packet.getVersion());
        buf.writeByte(Serializer.DEFAULT.getSerializerAlgorithm());
        buf.writeByte(packet.getCommand());
        buf.writeInt(packetBytes.length);
        buf.writeBytes(packetBytes);

        return buf;
    }

    // 解码
    public static Packet decode(ByteBuf byteBuf) {
        int magicNumber = byteBuf.readInt();
        byte version = byteBuf.readByte();
        byte serializerAlgorithm = byteBuf.readByte();
        byte command = byteBuf.readByte();
        int length = byteBuf.readInt();

        byte[] bytesRead = new byte[length];
        byteBuf.readBytes(bytesRead);

        Class<? extends Packet> requestType = getRequestType(command);
        return Serializer.DEFAULT.deserialize(requestType, bytesRead);
    }

    private static Class<? extends Packet> getRequestType(byte command) {
        return packetMap.get(command);
    }
}
