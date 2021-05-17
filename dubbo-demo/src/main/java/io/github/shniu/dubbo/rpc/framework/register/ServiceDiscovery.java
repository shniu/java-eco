package io.github.shniu.dubbo.rpc.framework.register;

import java.util.List;

/**
 * @author niushaohan
 * @date 2021/5/13 13
 */
public interface ServiceDiscovery {
    List<String> get(String serviceName);
}
