package io.github.shniu.gateway.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.cloud.gateway.filter.factory.AbstractNameValueGatewayFilterFactory;
import org.springframework.stereotype.Component;

/**
 * @author shniu
 * @date 2019/09/09 19:55:05
 */
@Component
@Slf4j
public class JWTFilter extends AbstractNameValueGatewayFilterFactory {

    public JWTFilter() {
        log.info("init JWTFilter.");
    }

    @Override
    public GatewayFilter apply(NameValueConfig config) {
        log.info("Load jwt filter.");
        return null;
    }

}
