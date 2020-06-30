package io.github.shniu.gateway.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.Charsets;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.AsyncContext;
import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
@Slf4j
public class ServletAsyncController {
    private static final String controllerName = ServletAsyncController.class.getName();

    private ExecutorService pool = Executors.newFixedThreadPool(2);

    @GetMapping(value = "/async/pet")
    public void asyncPet(HttpServletRequest request, HttpServletResponse response) {
        AsyncContext asyncContext = request.startAsync();
        asyncContext.addListener(new AsyncListener() {
            @Override
            public void onComplete(AsyncEvent event) throws IOException {
                log.info("{} on complete", controllerName);
            }

            @Override
            public void onTimeout(AsyncEvent event) throws IOException {
                log.info("{} on timeout", controllerName);
            }

            @Override
            public void onError(AsyncEvent event) throws IOException {
                log.info("{} on error", controllerName);
            }

            @Override
            public void onStartAsync(AsyncEvent event) throws IOException {
                log.info("{} on start async", controllerName);
            }
        });
        asyncContext.setTimeout(10000);
//        asyncContext.start(() -> {
//            try {
//                Thread.sleep(1000);
//                log.info("异步线程：{}", Thread.currentThread().getName());
//                asyncContext.getResponse().setCharacterEncoding(Charsets.UTF_8.name());
//                asyncContext.getResponse().getWriter().write("pet async finished.");
//                asyncContext.complete();
//            } catch (InterruptedException | IOException e) {
//                e.printStackTrace();
//            }
//        });
        pool.submit(new Handler(asyncContext));

        log.info("请求线程：{}", Thread.currentThread().getName());
    }
}

@Slf4j
class Handler implements Runnable {
    private AsyncContext asyncContext;

    public Handler(AsyncContext asyncContext) {
        this.asyncContext = asyncContext;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
            log.info("异步线程：{}", Thread.currentThread().getName());
            asyncContext.getResponse().setCharacterEncoding(Charsets.UTF_8.name());
            asyncContext.getResponse().getWriter().write("pet async finished.");
            asyncContext.complete();
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
    }
}
