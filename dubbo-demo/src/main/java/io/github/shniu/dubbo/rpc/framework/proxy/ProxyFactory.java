package io.github.shniu.dubbo.rpc.framework.proxy;

import io.github.shniu.dubbo.rpc.framework.URL;
import io.github.shniu.dubbo.rpc.framework.loadbalancer.LoadBalance;
import io.github.shniu.dubbo.rpc.framework.loadbalancer.RoundRobinLoadBalance;
import io.github.shniu.dubbo.rpc.framework.protocol.Invocation;
import io.github.shniu.dubbo.rpc.framework.protocol.netty.NettyClient;
import io.github.shniu.dubbo.rpc.framework.register.ServiceDiscovery;
import io.github.shniu.dubbo.rpc.framework.register.impl.ZookeeperServiceDiscovery;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.List;

/**
 * @author niushaohan
 * @date 2021/5/13 09
 */
public class ProxyFactory {
    static LoadBalance loadBalance = new RoundRobinLoadBalance();
    static ServiceDiscovery serviceDiscovery = new ZookeeperServiceDiscovery();

    @SuppressWarnings("unchecked")
    public static <T> T getInstance(Class<T> cls) {
        InvocationHandler invocationHandler = (proxy, method, args) -> {
            Invocation invocation
                    = new Invocation(cls.getName(), method.getName(), method.getParameterTypes(), args);

            NettyClient nettyClient = new NettyClient();

            final String serviceName = "user-service";
            List<String> urls = serviceDiscovery.get(serviceName);
            URL url = loadBalance.select(urls);

            long start = System.currentTimeMillis();
            Object resp = nettyClient.send(url, invocation);
            long end = System.currentTimeMillis();
            System.out.println("RPC 调用耗时 " + (end - start) + " ms");

            return resp;
        };

        return (T) Proxy.newProxyInstance(cls.getClassLoader(), new Class[]{cls}, invocationHandler);
    }
}
