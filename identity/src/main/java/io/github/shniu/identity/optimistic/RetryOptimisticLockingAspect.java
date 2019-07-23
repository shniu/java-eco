package io.github.shniu.identity.optimistic;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.hibernate.StaleObjectStateException;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class RetryOptimisticLockingAspect {
    private final int MAX_RETRY_TIMES = 5;

    @Pointcut("@annotation(io.github.shniu.identity.optimistic.RetryOnOptimisticLockingFailure)")
    public void retryOnFailure() {
    }

    @Around("retryOnFailure()")
    public Object doConcurrentOperation(ProceedingJoinPoint pjp) throws Throwable {
        int attempts = 0;

        do {
            attempts++;

            try {
                return pjp.proceed();
            } catch (Exception e) {
                if (e instanceof ObjectOptimisticLockingFailureException ||
                        e instanceof StaleObjectStateException) {
                    log.info("retrying....times:{}", attempts);
                    if (attempts > MAX_RETRY_TIMES) {
                        log.info("retry excceed the max times..");
                        throw e;
                    }
                }

            }
        } while (attempts < MAX_RETRY_TIMES);

        return null;
    }

}
