package io.github.shniu.dubbo.demo.api;

/**
 * @author niushaohan
 * @date 2021/5/12 15
 */
public interface UserService {
    String getUsername(String userId);

    void registerUser(String userId, String username);
}
