package org.digcredit.netty.im.protocol.request;

import lombok.Data;
import org.digcredit.netty.im.protocol.Command;
import org.digcredit.netty.im.protocol.Packet;

@Data
public class LoginRequestPacket extends Packet {

    private String userId;
    private String username;
    private String password;

    @Override
    public Byte getCommand() {
        return Command.LOGIN_REQUEST;
    }
}
