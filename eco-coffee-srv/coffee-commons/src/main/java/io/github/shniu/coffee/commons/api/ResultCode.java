package io.github.shniu.coffee.commons.api;

import lombok.Getter;

import javax.servlet.http.HttpServletResponse;

/**
 * @author niushaohan
 * @date 2020/6/20 14
 */
public enum ResultCode {
    SUCCESS(HttpServletResponse.SC_OK, "Success"),
    FAILURE(HttpServletResponse.SC_BAD_REQUEST, "Failed")
    ;

    ResultCode(final int code, final String message) {
        this.code = code;
        this.message = message;
    }

    @Getter
    private final int code;
    @Getter
    private final String message;
}
