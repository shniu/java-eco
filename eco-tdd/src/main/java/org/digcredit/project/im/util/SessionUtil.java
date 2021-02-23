package org.digcredit.project.im.util;

import com.google.common.collect.Maps;
import io.netty.channel.Channel;
import org.digcredit.project.im.protocol.Attributes;
import org.digcredit.project.im.server.Session;

import java.util.concurrent.ConcurrentMap;

/**
 * @author niushaohan
 * @date 2021/2/8 15
 */
public final class SessionUtil {
    private static final ConcurrentMap<String, Channel> onlineUserChannels = Maps.newConcurrentMap();

    public static void bindSession(Session session, Channel channel) {
        onlineUserChannels.put(session.getUserId(), channel);
        channel.attr(Attributes.SESSION).set(session);
    }

    public static void unbindSession(Channel channel) {
        if (hasLogin(channel)) {
            Session session = channel.attr(Attributes.SESSION).get();
            onlineUserChannels.remove(session.getUserId());
            channel.attr(Attributes.SESSION).set(null);
        }
    }

    public static boolean hasLogin(Channel channel) {
        return channel.hasAttr(Attributes.SESSION);
    }

    public static Session getSession(Channel channel) {
        return channel.attr(Attributes.SESSION).get();
    }

    public static Channel getChannel(String userId) {
        return onlineUserChannels.get(userId);
    }

    public static Session getSession(String userId) {
        return getSession(getChannel(userId));
    }
}
