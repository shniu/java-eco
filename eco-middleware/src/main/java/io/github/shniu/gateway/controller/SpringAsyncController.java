package io.github.shniu.gateway.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
@Slf4j
public class SpringAsyncController {
    private ExecutorService pool = Executors.newFixedThreadPool(3);

    @GetMapping(value = "/spring/pet/{name}")
    public DeferredResult<String> deferredResult(@PathVariable String name) {
        log.info("请求线程：{}", Thread.currentThread().getName());
        DeferredResult<String> result = new DeferredResult<>(3 * 1000L);
        result.onTimeout(() -> {
            log.info("Async deferred result timed out");
            result.setErrorResult("errorCode: 100");
        });
        result.onCompletion(() -> log.info("Async deferred result completion"));
        result.onError(throwable -> log.error("Async deferred result error", throwable));

        pool.submit(new DeferredHandler(result));

        return result;
    }
}

@Slf4j
class DeferredHandler implements Runnable {
    private DeferredResult<String> result;

    public DeferredHandler(DeferredResult<String> result) {
        this.result = result;
    }

    @Override
    public void run() {
        try {
            log.info("业务线程: {}", Thread.currentThread().getName());
            Thread.sleep(1000);
            result.setResult("Ok!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
