package org.digcredit.netty.im.protocol.response;

import lombok.Data;
import org.digcredit.netty.im.protocol.Command;
import org.digcredit.netty.im.protocol.Packet;

@Data
public class LoginResponsePacket extends Packet {
    private boolean success;
    private String reason;

    @Override
    public Byte getCommand() {
        return Command.LOGIN_RESPONSE;
    }
}
