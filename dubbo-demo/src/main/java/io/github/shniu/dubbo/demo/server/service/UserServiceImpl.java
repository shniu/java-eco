package io.github.shniu.dubbo.demo.server.service;

import io.github.shniu.dubbo.demo.api.UserService;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author niushaohan
 * @date 2021/5/12 15
 */
public class UserServiceImpl implements UserService {
    private ConcurrentMap<String, String> users = new ConcurrentHashMap<>();

    @Override
    public String getUsername(String userId) {
        return users.get(userId);
    }

    @Override
    public void registerUser(String userId, String username) {
        System.out.println("Register " + userId + "@" + username);
        users.put(userId, username);
    }
}
