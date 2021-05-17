package io.github.shniu.dubbo.rpc.framework.register;

import java.util.HashMap;
import java.util.Map;

/**
 * @author niushaohan
 * @date 2021/5/13 09
 */
public class LocalRegister {
    private static Map<String, Object> instances = new HashMap<>();

    public static void register(String apiInterface, Object impl) {
        instances.put(apiInterface, impl);
    }

    public static Object get(String apiInterface) {
        return instances.get(apiInterface);
    }
}
