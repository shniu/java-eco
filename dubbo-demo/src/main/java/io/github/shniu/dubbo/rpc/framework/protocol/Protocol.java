package io.github.shniu.dubbo.rpc.framework.protocol;

import java.net.URL;

/**
 * @author niushaohan
 * @date 2021/5/13 09
 */
public interface Protocol {
     void start(String hostname, int port);
}
