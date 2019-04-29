package org.digcredit.netty.im.protocol.request;

import lombok.Data;
import org.digcredit.netty.im.protocol.Command;
import org.digcredit.netty.im.protocol.Packet;

@Data
public class MessageRequestPacket extends Packet {
    private String message;

    @Override
    public Byte getCommand() {
        return Command.MESSAGE_REQUEST;
    }
}
