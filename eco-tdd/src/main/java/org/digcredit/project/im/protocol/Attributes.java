package org.digcredit.project.im.protocol;

import io.netty.util.AttributeKey;
import org.digcredit.project.im.server.Session;

/**
 * @author niushaohan
 * @date 2021/2/8 14
 */
public interface Attributes {
    AttributeKey<Boolean> LOGIN = AttributeKey.newInstance("login");
    AttributeKey<Session> SESSION = AttributeKey.newInstance("session");
}
