package org.digcredit.netty.im.protocol.response;

import lombok.Data;
import org.digcredit.netty.im.protocol.Command;
import org.digcredit.netty.im.protocol.Packet;

@Data
public class MessageResponsePacket extends Packet {
    private String message;

    @Override
    public Byte getCommand() {
        return Command.MESSAGE_RESPONSE;
    }
}
