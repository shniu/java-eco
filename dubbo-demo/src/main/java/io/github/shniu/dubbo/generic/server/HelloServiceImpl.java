package io.github.shniu.dubbo.generic.server;

import io.github.shniu.dubbo.generic.api.HelloService;
import io.github.shniu.dubbo.generic.api.Person;
import org.apache.dubbo.common.utils.PojoUtils;
import org.apache.dubbo.rpc.service.GenericException;
import org.apache.dubbo.rpc.service.GenericService;

import java.util.Arrays;
import java.util.Map;

/**
 * @author niushaohan
 * @date 2021/5/13 16
 */
public class HelloServiceImpl implements HelloService, GenericService {

    @Override
    public String sayHello(Person person, String message) {
        String result = "hello[" + person + "], message=" + message;

        return result;
    }

    @Override
    public Object $invoke(String method, String[] parameterTypes, Object[] args) throws GenericException {
        System.out.println(method + Arrays.toString(parameterTypes) + Arrays.toString(args));

        if ("sayHello".equals(method)) {
            //noinspection unchecked
            Map<String, Object> map = (Map<String, Object>) args[0];
            // PojoUtils.generalize()
            Person p = new Person((String) map.get("name"), (String) map.get("password"));

            return sayHello(p, (String) args[1]);
        }
        return null;
    }
}
