package io.github.shniu.springbootgracefulshutdown.config;

import com.github.timpeeters.boot.shutdown.autoconfigure.GracefulShutdownAutoConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.connector.Connector;
import org.springframework.boot.web.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextClosedEvent;

// @Configuration
@Slf4j
public class GracefulShutdownConfig {

    @Bean
    public WebServerFactoryCustomizer webServerFactoryCustomizer() {
        log.info("#################");
        return factory -> {
            if (factory instanceof TomcatServletWebServerFactory) {
                ((TomcatServletWebServerFactory) factory)
                        .addConnectorCustomizers(gracefulShutdown());
            }
        };
    }

    @Bean
    public GracefulShutdown gracefulShutdown() {
        return new GracefulShutdown();
    }

    private static class GracefulShutdown implements TomcatConnectorCustomizer,
            ApplicationListener<ContextClosedEvent> {

        @Override
        public void customize(Connector connector) {
            log.info("=========== customize");
        }

        @Override
        public void onApplicationEvent(ContextClosedEvent contextClosedEvent) {
            log.info("=========== onApplicationEvent");
        }
    }
}
