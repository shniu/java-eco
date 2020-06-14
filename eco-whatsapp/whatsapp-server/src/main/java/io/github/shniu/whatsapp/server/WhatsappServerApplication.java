package io.github.shniu.whatsapp.server;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@Slf4j
public class WhatsappServerApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(WhatsappServerApplication.class, args);
        Object bean = context.getBean("byteToMessageDecoder");
        log.info("{}", bean);
    }

}
