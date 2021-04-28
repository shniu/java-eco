package io.github.shniu.mybatisplus.config;

import io.github.shniu.mybatisplus.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * @author niushaohan (shaohan.niu@dfgroup.pro)
 * @date 2020/4/19 18
 */
@Configuration
@EnableTransactionManagement(order = 3)
public class MybatisPlusConfig {
    @Bean
    PlatformTransactionManager txManager(@Autowired DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
}
