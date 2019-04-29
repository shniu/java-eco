package org.digcredit.netty.im.util;

import io.netty.channel.Channel;
import io.netty.util.Attribute;
import org.digcredit.netty.im.pipeline.Attributes;

public class LoginUtil {


    public static void markAsLogin(Channel channel) {
        channel.attr(Attributes.LOGIN).set(true);
    }

    public static boolean hasLogin(Channel channel) {
        Attribute<Boolean> loginAttr = channel.attr(Attributes.LOGIN);
        return loginAttr.get() != null;
    }
}
