package io.github.shniu.dubbo.rpc.framework.register;

/**
 * @author niushaohan
 * @date 2021/5/13 13
 */
public interface ServiceRegister {
    void register(String serviceName, String address);
}
