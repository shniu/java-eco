package io.github.shniu.mybatisplus;

import io.github.shniu.mybatisplus.mapper.UserMapper;
import io.github.shniu.mybatisplus.model.User;
import io.github.shniu.mybatisplus.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @author niushaohan (shaohan.niu@dfgroup.pro)
 * @date 2020/4/19 16
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class SpringBootMybatisPlusApplicationTest {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserService userService;

    @Test
    public void testSelectUsers() {
        log.info("------ select users -------");
        List<User> users = userMapper.selectList(null);
        assertTrue(users.size() > 0);
        users.forEach(user -> log.info("{}", user));
    }

    @Test
    public void testUserService() {
        List<User> users = userService.list();
        for (User user : users) {
            user.incr();
        }

        // new user
        users.add(User.builder()
                .name("new1")
                .age(30)
                .email("new1@gmail.com")
                .build());

        boolean result = userService.saveOrUpdateBatch(users);
        log.info("批量插入或者更新的结果为：{}", result ? "成功" : "失败");
    }
}