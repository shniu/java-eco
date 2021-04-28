package io.github.shniu.mybatisplus.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.shniu.mybatisplus.aspect.CollectEvent;
import io.github.shniu.mybatisplus.mapper.UserMapper;
import io.github.shniu.mybatisplus.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author niushaohan (shaohan.niu@dfgroup.pro)
 * @date 2020/4/19 18
 */
@Slf4j
@Service
public class UserService extends ServiceImpl<UserMapper, User> {

    private UserMapper userMapper;

    public UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @CollectEvent(type = "UserCreated")
    @Transactional
    public User saveUser() {
        User user = User.builder()
                .name("he")
                .age(20)
                .build();
        userMapper.insert(user);
//        if (user != null) {
//            throw new RuntimeException("test");
//        }
        return user;
    }
}
