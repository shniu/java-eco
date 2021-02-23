package org.digcredit.project.im.protocol.request;

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
public class LogoutRequestPacket extends Packet {

    private String reason;

    @Override
    public Byte getCommand() {
        return Command.LOGOUT_REQUEST;
    }
}
