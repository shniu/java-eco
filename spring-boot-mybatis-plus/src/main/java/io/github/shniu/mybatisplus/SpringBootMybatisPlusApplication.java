package io.github.shniu.mybatisplus;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
@MapperScan("io.github.shniu.mybatisplus")
public class SpringBootMybatisPlusApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootMybatisPlusApplication.class, args);
        log.info("SpringBootMybatisPlus startup!");
    }
}
