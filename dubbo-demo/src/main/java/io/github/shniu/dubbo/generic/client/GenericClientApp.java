package io.github.shniu.dubbo.generic.client;

import io.github.shniu.dubbo.demo.api.UserService;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.rpc.service.GenericService;

import java.util.HashMap;
import java.util.Map;

/**
 * @author niushaohan
 * @date 2021/5/13 16
 */
public class GenericClientApp {
    private static String zookeeperHost = System.getProperty("zookeeper.address", "127.0.0.1");
    private static String zookeeperPort = System.getProperty("zookeeper.port", "2181");

    public static void main(String[] args) {
        ReferenceConfig<GenericService> referenceConfig = new ReferenceConfig<>();
        referenceConfig.setApplication(new ApplicationConfig("generic-consumer"));
        referenceConfig.setRegistry(new RegistryConfig("zookeeper://" + zookeeperHost + ":" + zookeeperPort));
        referenceConfig.setInterface("io.github.shniu.dubbo.generic.api.HelloService");
        referenceConfig.setGeneric("true");

        GenericService genericService = referenceConfig.get();
        Map<String, Object> person = new HashMap<String, Object>();
        person.put("class", "io.github.shniu.dubbo.generic.api.Person");
        person.put("name", "zhangsan");
        person.put("password", "123456");
        Object resp = genericService.$invoke("sayHello",
                new String[]{"io.github.shniu.dubbo.generic.api.Person", "java.lang.String"},
                new Object[]{person, "I am generic."});
        System.out.println("=====" + resp);
    }
}
