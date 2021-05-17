package io.github.shniu.dubbo.rpc.sample.user;

import io.github.shniu.dubbo.rpc.sample.api.User;
import io.github.shniu.dubbo.rpc.sample.api.UserService;

/**
 * @author niushaohan
 * @date 2021/5/13 09
 */
public class UserServiceImpl implements UserService {
    @Override
    public User getById(String uid) {
        long ts = System.currentTimeMillis();
        return new User(uid, "name-" + (ts % 100), ts);
    }
}
