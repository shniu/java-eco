package io.github.shniu.coffee.waiter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author niushaohan
 * @date 2020/6/20 00
 */
@SpringBootApplication
@EnableEurekaClient
@Slf4j
public class WaiterServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(WaiterServiceApplication.class, args);
        log.info("Waiter service started.");
    }
}
