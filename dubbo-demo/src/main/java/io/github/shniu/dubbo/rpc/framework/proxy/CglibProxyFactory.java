package io.github.shniu.dubbo.rpc.framework.proxy;

import io.github.shniu.dubbo.rpc.framework.URL;
import io.github.shniu.dubbo.rpc.framework.loadbalancer.LoadBalance;
import io.github.shniu.dubbo.rpc.framework.loadbalancer.RoundRobinLoadBalance;
import io.github.shniu.dubbo.rpc.framework.protocol.Invocation;
import io.github.shniu.dubbo.rpc.framework.protocol.netty.NettyClient;
import io.github.shniu.dubbo.rpc.framework.register.ServiceDiscovery;
import io.github.shniu.dubbo.rpc.framework.register.impl.ZookeeperServiceDiscovery;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.List;

/**
 * @author niushaohan
 * @date 2021/5/13 14
 */
public class CglibProxyFactory implements MethodInterceptor {

    static LoadBalance loadBalance = new RoundRobinLoadBalance();
    static ServiceDiscovery serviceDiscovery = new ZookeeperServiceDiscovery();

    @SuppressWarnings("unchecked")
    public <T> T getInstance(Class<T> cls) {
        // 工具类
        Enhancer en = new Enhancer();
        // 设置父类
        en.setSuperclass(cls);
        // 设置回调函数
        en.setCallback(this);
        // 创建子类对象代理
        return (T) en.create();
    }

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        Class<?> aClass = obj.getClass().getInterfaces()[0];
        Invocation invocation
                = new Invocation(aClass.getName(), method.getName(), method.getParameterTypes(), args);

        NettyClient nettyClient = new NettyClient();

        final String serviceName = "user-service";
        List<String> urls = serviceDiscovery.get(serviceName);
        URL url = loadBalance.select(urls);

        long start = System.currentTimeMillis();
        Object resp = nettyClient.send(url, invocation);
        long end = System.currentTimeMillis();
        System.out.println("RPC 调用耗时 " + (end - start) + " ms");

        return resp;
    }

}
