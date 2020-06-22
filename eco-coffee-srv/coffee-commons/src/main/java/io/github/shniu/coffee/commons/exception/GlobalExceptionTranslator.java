package io.github.shniu.coffee.commons.exception;

import io.github.shniu.coffee.commons.api.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * https://www.baeldung.com/exception-handling-for-rest-with-spring
 *
 * @author niushaohan
 * @date 2020/6/20 14
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionTranslator {
    @ExceptionHandler({ServiceException.class})
    public BaseResponse handleException(final ServiceException e) {
        log.error("Service exception", e);
        return BaseResponse.builder()
                .code(e.getResultCode())
                .message(e.getMessage())
                .build();
    }

    @ExceptionHandler({MyServiceException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public BaseResponse handleException(final MyServiceException e) {
        log.error("My service exception", e);
        return BaseResponse.builder()
                .code(e.getResultCode())
                .message(e.getMessage())
                .build();
    }
}
