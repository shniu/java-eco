package io.github.shniu.springbootgracefulshutdown.resource;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class LongResource {

    @GetMapping(value = "/test/shutdown")
    public String longProcess() {
        log.info("sleep 10s");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            // ...
        }
        return "success";
    }
}
