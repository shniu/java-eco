package io.github.shniu.dubbo.rpc.sample.order;

import io.github.shniu.dubbo.rpc.framework.proxy.CglibProxyFactory;
import io.github.shniu.dubbo.rpc.framework.proxy.ProxyFactory;
import io.github.shniu.dubbo.rpc.sample.api.User;
import io.github.shniu.dubbo.rpc.sample.api.UserService;

/**
 * @author niushaohan
 * @date 2021/5/13 09
 */
public class OrderApplication {

    // 作为客户端，去调用其他服务
    public static void main(String[] args) {
        // UserService userService = ProxyFactory.getInstance(UserService.class);

        CglibProxyFactory proxyFactory = new CglibProxyFactory();
        UserService userService = proxyFactory.getInstance(UserService.class);

        for (int i = 0; i < 20; i++) {
            User user = userService.getById("u123456");
            System.out.println(user);
        }
    }
}
