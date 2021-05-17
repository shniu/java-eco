package io.github.shniu.dubbo.rpc.framework.loadbalancer;

import io.github.shniu.dubbo.rpc.framework.URL;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author niushaohan
 * @date 2021/5/13 13
 */
public class RoundRobinLoadBalance implements LoadBalance {
    private AtomicLong next = new AtomicLong(1);

    @Override
    public URL select(List<String> urls) {
        long currIndex = next.getAndIncrement();
        int index = (int) (currIndex % urls.size());
        String urlStr = urls.get(index);
        String[] urlSegments = urlStr.split(":");
        return new URL(urlSegments[0], Integer.parseInt(urlSegments[1]));
    }
}
