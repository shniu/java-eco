package io.github.shniu.dubbo.rpc.sample.user;

import io.github.shniu.dubbo.rpc.framework.URL;
import io.github.shniu.dubbo.rpc.framework.protocol.Protocol;
import io.github.shniu.dubbo.rpc.framework.protocol.netty.NettyServer;
import io.github.shniu.dubbo.rpc.framework.register.LocalRegister;
import io.github.shniu.dubbo.rpc.framework.register.ServiceRegister;
import io.github.shniu.dubbo.rpc.framework.register.impl.ZookeeperServiceRegister;
import io.github.shniu.dubbo.rpc.sample.api.UserService;

/**
 * @author niushaohan
 * @date 2021/5/13 09
 */
public class UserApplication {

    // 作为服务端，被其他服务调用
    public static void main(String[] args) {
        final URL url = new URL("127.0.0.1", 20000);
        // 注册本地接口和对应的实现
        LocalRegister.register(UserService.class.getName(), new UserServiceImpl());

        // 注册到注册中心：serviceName -> ip:port
        ServiceRegister serviceRegister = new ZookeeperServiceRegister();
        serviceRegister.register("user-service", url.toString());

        // 启动服务
        Protocol protocol = new NettyServer();
        protocol.start("127.0.0.1", 20000);
    }
}
