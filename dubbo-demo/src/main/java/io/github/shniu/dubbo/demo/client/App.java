package io.github.shniu.dubbo.demo.client;

import io.github.shniu.dubbo.demo.api.UserService;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;

/**
 * @author niushaohan
 * @date 2021/5/12 16
 */
public class App {
    private static String zookeeperHost = System.getProperty("zookeeper.address", "127.0.0.1");
    private static String zookeeperPort = System.getProperty("zookeeper.port", "2181");

    public static void main(String[] args) {
        ReferenceConfig<UserService> referenceConfig = new ReferenceConfig<>();
        referenceConfig.setApplication(new ApplicationConfig("first-dubbo-consumer"));
        referenceConfig.setRegistry(new RegistryConfig("zookeeper://" + zookeeperHost + ":" + zookeeperPort));
        referenceConfig.setInterface(UserService.class);

        UserService userService = referenceConfig.get();
        userService.registerUser("101010", "John");
        userService.registerUser("101011", "Sas");
        userService.registerUser("101012", "Ham");

        String username = userService.getUsername("101010");
        System.out.println("==> userId 101010 -> username is " + username);

        username = userService.getUsername("101014");
        System.out.println("==> userId 101010 -> username is " + username);
    }
}
