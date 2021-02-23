package org.digcredit.project.im.protocol.request;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.digcredit.project.im.protocol.Command;
import org.digcredit.project.im.protocol.Packet;

@EqualsAndHashCode(callSuper = true)
@Data
public class LoginRequestPacket extends Packet {

    // private String userId;
    private String username;
    private String password;

    @Override
    public Byte getCommand() {
        return Command.LOGIN_REQUEST;
    }
}
