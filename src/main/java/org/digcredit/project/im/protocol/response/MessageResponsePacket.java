package org.digcredit.project.im.protocol.response;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.digcredit.project.im.protocol.Command;
import org.digcredit.project.im.protocol.Packet;

@EqualsAndHashCode(callSuper = true)
@Data
public class MessageResponsePacket extends Packet {
    private String message;
    @Override
    public Byte getCommand() {
        return Command.MESSAGE_RESPONSE;
    }
}
