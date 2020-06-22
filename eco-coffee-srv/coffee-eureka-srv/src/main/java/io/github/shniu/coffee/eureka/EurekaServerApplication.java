package io.github.shniu.coffee.eureka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author niushaohan
 * @date 2020/6/19 23
 */
@SpringBootApplication
@EnableEurekaServer
@Slf4j
public class EurekaServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(EurekaServerApplication.class, args);
        log.info("Eureka server started.");
    }
}
