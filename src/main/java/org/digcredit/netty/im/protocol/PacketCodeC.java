package org.digcredit.netty.im.protocol;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import org.digcredit.netty.im.protocol.request.LoginRequestPacket;
import org.digcredit.netty.im.protocol.request.MessageRequestPacket;
import org.digcredit.netty.im.protocol.response.LoginResponsePacket;
import org.digcredit.netty.im.protocol.response.MessageResponsePacket;
import org.digcredit.netty.im.serialize.Serializer;
import org.digcredit.netty.im.serialize.SerializerAlogrithm;
import org.digcredit.netty.im.serialize.impl.JSONSerializer;

import java.util.HashMap;
import java.util.Map;

public class PacketCodeC {
    public static final PacketCodeC INSTANCE = new PacketCodeC();
    private static final int MAGIC_NUMBER = 0x12345678;

    private final Map<Byte, Class<? extends Packet>> packetTypeMap;
    private final Map<Byte, Serializer> serializerMap;

    private PacketCodeC() {
        packetTypeMap = new HashMap<>();
        packetTypeMap.put(Command.LOGIN_REQUEST, LoginRequestPacket.class);
        packetTypeMap.put(Command.LOGIN_RESPONSE, LoginResponsePacket.class);
        packetTypeMap.put(Command.MESSAGE_REQUEST, MessageRequestPacket.class);
        packetTypeMap.put(Command.MESSAGE_RESPONSE, MessageResponsePacket.class);

        serializerMap = new HashMap<>();
        Serializer serializer = new JSONSerializer();
        serializerMap.put(SerializerAlogrithm.JSON, serializer);
    }
    
    public ByteBuf encode(ByteBufAllocator alloc, Packet packet) {

        ByteBuf byteBuf = alloc.ioBuffer();

        byte[] bytes = Serializer.DEFAULT.serialize(packet);

        // Magic number
        byteBuf.writeInt(MAGIC_NUMBER);
        byteBuf.writeByte(packet.getVersion());
        byteBuf.writeByte(Serializer.DEFAULT.getSerializerAlogrithm());
        byteBuf.writeByte(packet.getCommand());
        byteBuf.writeInt(bytes.length);
        byteBuf.writeBytes(bytes);

        return byteBuf;
    }

    public Packet decode(ByteBuf byteBuf) {
        byteBuf.skipBytes(Integer.BYTES);
        byteBuf.skipBytes(Byte.BYTES);

        byte serializeAlgorithm = byteBuf.readByte();
        byte command = byteBuf.readByte();
        int dataLength = byteBuf.readInt();
        byte[] bytes = new byte[dataLength];
        byteBuf.readBytes(bytes);

        Class<? extends Packet> requestType = getRequestType(command);
        Serializer serializer = getSerializer(serializeAlgorithm);

        if (requestType != null && serializer != null) {
            return serializer.deserialize(requestType, bytes);
        }
        return null;
    }

    private Serializer getSerializer(byte serializeAlgorithm) {
        return serializerMap.get(serializeAlgorithm);
    }

    private Class<? extends Packet> getRequestType(byte command) {
        return packetTypeMap.get(command);
    }
}
