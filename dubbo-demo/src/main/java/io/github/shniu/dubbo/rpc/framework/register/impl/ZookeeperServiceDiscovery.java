package io.github.shniu.dubbo.rpc.framework.register.impl;

import com.google.common.collect.Lists;
import io.github.shniu.dubbo.rpc.framework.register.ServiceDiscovery;

import java.util.List;

/**
 * @author niushaohan
 * @date 2021/5/13 13
 */
public class ZookeeperServiceDiscovery implements ServiceDiscovery {
    @Override
    public List<String> get(String serviceName) {
        return Lists.newArrayList("127.0.0.1:20000");
    }
}
