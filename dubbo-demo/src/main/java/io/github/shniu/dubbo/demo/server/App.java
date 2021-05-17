package io.github.shniu.dubbo.demo.server;

import io.github.shniu.dubbo.demo.api.UserService;
import io.github.shniu.dubbo.demo.server.service.UserServiceImpl;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.ServiceConfig;

import java.util.concurrent.CountDownLatch;

/**
 * @author niushaohan
 * @date 2021/5/12 15
 */
public class App {

    private static String zookeeperHost = System.getProperty("zookeeper.address", "127.0.0.1");
    private static String zookeeperPort = System.getProperty("zookeeper.port", "2181");

    public static void main(String[] args) throws InterruptedException {
        ServiceConfig<UserService> serviceConfig = new ServiceConfig<>();
        serviceConfig.setApplication(new ApplicationConfig("first-dubbo-provider"));
        serviceConfig.setRegistry(new RegistryConfig("zookeeper://" + zookeeperHost + ":" + zookeeperPort));
        serviceConfig.setInterface(UserService.class);
        serviceConfig.setRef(new UserServiceImpl());
        serviceConfig.export();

        System.out.println("Dubbo service started.");
        new CountDownLatch(1).await();
    }
}
