package io.github.shniu.mybatisplus.aspect;

import io.github.shniu.mybatisplus.mapper.EventMapper;
import io.github.shniu.mybatisplus.model.Event;
import io.github.shniu.mybatisplus.model.User;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author niushaohan
 * @date 2021/3/30 23
 */
@Aspect
@Component
@Order(Ordered.LOWEST_PRECEDENCE)
@Slf4j
public class CollectEventAspect {
    private CollectEventService collectEventService;

    public CollectEventAspect(CollectEventService collectEventService) {
        this.collectEventService = collectEventService;
    }

    @Around("@annotation(collectEvent)")
    public Object aroundCollectEvent(ProceedingJoinPoint joinPoint, CollectEvent collectEvent) throws Throwable {
        log.info("++++++++, event type: {}", collectEvent.type());
        Object result = joinPoint.proceed();
        log.info("------");

        Event event = Event.builder().type(collectEvent.type()).build();
        collectEventService.logEventInTransaction(event);

        User user = (User) result;
        log.info("User => {}", user);

        return result;
    }
}
