package io.github.shniu.mybatisplus.config;

import io.github.shniu.mybatisplus.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author niushaohan (shaohan.niu@dfgroup.pro)
 * @date 2020/4/19 18
 */
@Configuration
public class MybatisPlusConfig {
    @Bean
    public UserService userService() {
        return new UserService();
    }
}
