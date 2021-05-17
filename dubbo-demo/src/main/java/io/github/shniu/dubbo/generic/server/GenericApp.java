package io.github.shniu.dubbo.generic.server;

import io.github.shniu.dubbo.generic.api.HelloService;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.ServiceConfig;
import org.apache.dubbo.rpc.service.GenericService;

import java.util.concurrent.CountDownLatch;

/**
 * @author niushaohan
 * @date 2021/5/13 16
 */
public class GenericApp {
    private static String zookeeperHost = System.getProperty("zookeeper.address", "127.0.0.1");
    private static String zookeeperPort = System.getProperty("zookeeper.port", "2181");

    public static void main(String[] args) throws InterruptedException {
        ServiceConfig<GenericService> serviceConfig = new ServiceConfig<>();
        serviceConfig.setApplication(new ApplicationConfig("generic-service-provider"));
        serviceConfig.setInterface(HelloService.class.getName());
        serviceConfig.setRegistry(new RegistryConfig("zookeeper://" + zookeeperHost + ":" + zookeeperPort));

        serviceConfig.setRef(new HelloServiceImpl());

        serviceConfig.export();

        new CountDownLatch(1).await();
    }
}
