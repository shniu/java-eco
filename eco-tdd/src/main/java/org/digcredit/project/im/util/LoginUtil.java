package org.digcredit.project.im.util;


import io.netty.channel.Channel;
import io.netty.util.Attribute;
import org.digcredit.project.im.protocol.Attributes;

/**
 * @author niushaohan
 * @date 2021/2/8 14
 */
public final class LoginUtil {
    public static void markAsLogin(Channel channel) {
        channel.attr(Attributes.LOGIN).set(true);
    }

    public static boolean hasLogin(Channel channel) {
        Attribute<Boolean> loginAttr = channel.attr(Attributes.LOGIN);

        return loginAttr.get() != null && loginAttr.get();
    }
}
