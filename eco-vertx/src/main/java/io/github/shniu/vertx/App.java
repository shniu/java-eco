package io.github.shniu.vertx;

import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.http.HttpHeaders;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.core.http.HttpServerResponse;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;

/**
 * @author niushaohan
 * @date 2020/12/17 16
 */
@Slf4j
public class App {
    public static void main(String[] args) {
        CountDownLatch latch = new CountDownLatch(1);

        Vertx vertx = Vertx.vertx(
                new VertxOptions()
                        .setEventLoopPoolSize(3)
                        .setWorkerPoolSize(10)
        );

        HttpServerOptions serverOptions = new HttpServerOptions().setPort(9009);
        HttpServer server = vertx.createHttpServer(serverOptions);

        server.requestHandler(request -> {
            String uri = request.uri();
            log.info("{} {}{}", request.method().name(), request.host(), uri);

            HttpServerResponse response = request.response();
            response.putHeader(HttpHeaders.CONTENT_TYPE, "application/json");
            response.end("{\"status\": \"200 OK\"}");
        });

        server.listen();

        log.info("Vertx started.");

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
