package org.digcredit.project.im.protocol.response;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.digcredit.project.im.protocol.Command;
import org.digcredit.project.im.protocol.Packet;

@EqualsAndHashCode(callSuper = true)
@Data
public class LoginResponsePacket extends Packet {

    private int code;
    private String data;

    @Override
    public Byte getCommand() {
        return Command.LOGIN_RESPONSE;
    }
}
