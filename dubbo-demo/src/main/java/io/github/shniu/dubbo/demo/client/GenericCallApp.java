package io.github.shniu.dubbo.demo.client;

import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.rpc.service.GenericService;

/**
 * @author niushaohan
 * @date 2021/5/12 17
 */
public class GenericCallApp {
    private static String zookeeperHost = System.getProperty("zookeeper.address", "127.0.0.1");
    private static String zookeeperPort = System.getProperty("zookeeper.port", "2181");

    public static void main(String[] args) {
        ApplicationConfig applicationConfig = new ApplicationConfig();
        applicationConfig.setName("generic-consumer-1");

        ReferenceConfig<GenericService> reference = new ReferenceConfig<>();
        reference.setApplication(applicationConfig);
        reference.setRegistry(new RegistryConfig("zookeeper://" + zookeeperHost + ":" + zookeeperPort));
        reference.setInterface("io.github.shniu.dubbo.demo.api.UserService");

        reference.setGeneric("true");
    }
}
