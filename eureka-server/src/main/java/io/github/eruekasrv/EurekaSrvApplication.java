package io.github.eruekasrv;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author niushaohan
 * @date 2020/5/25 10
 */
@SpringBootApplication
@EnableEurekaServer
@Slf4j
public class EurekaSrvApplication {
    public static void main(String[] args) {
        SpringApplication.run(EurekaSrvApplication.class, args);
        log.info("Eureka srv started.");
    }
}
