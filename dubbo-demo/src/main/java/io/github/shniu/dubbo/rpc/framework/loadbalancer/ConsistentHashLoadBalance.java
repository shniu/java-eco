package io.github.shniu.dubbo.rpc.framework.loadbalancer;

import io.github.shniu.dubbo.rpc.framework.URL;

import java.util.List;

/**
 * @author niushaohan
 * @date 2021/5/13 13
 */
public class ConsistentHashLoadBalance implements LoadBalance {
    @Override
    public URL select(List<String> urls) {
        return null;
    }
}
