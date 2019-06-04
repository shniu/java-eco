package io.github.shniu.whatsapp.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@Slf4j
public class WhatsappClientApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(WhatsappClientApplication.class, args);

        log.info("----------");
        context.getBeanFactory().getBeanNamesIterator().forEachRemaining(log::info);

        Object bean = context.getBean("springBootBanner");

        log.info("{}", bean);
    }

}
