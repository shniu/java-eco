package org.digcredit.project.im.protocol.response;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.digcredit.project.im.protocol.Command;
import org.digcredit.project.im.protocol.Packet;

/**
 * @author niushaohan
 * @date 2021/2/8 16
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class LogoutResponsePacket extends Packet {

    private int code;

    @Override
    public Byte getCommand() {
        return Command.LOGOUT_RESPONSE;
    }

    public boolean isSuccess() {
        return code == 0;
    }
}
