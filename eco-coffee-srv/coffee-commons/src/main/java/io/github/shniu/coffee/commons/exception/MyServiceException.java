package io.github.shniu.coffee.commons.exception;

import io.github.shniu.coffee.commons.api.ResultCode;

/**
 * @author niushaohan
 * @date 2020/6/20 14
 */
public class MyServiceException extends ServiceException {
    public MyServiceException(String message) {
        super(message);
    }

    public MyServiceException(ResultCode resultCode) {
        super(resultCode);
    }

    public MyServiceException(ResultCode resultCode, String msg) {
        super(resultCode, msg);
    }

    public MyServiceException(ResultCode resultCode, Throwable cause) {
        super(resultCode, cause);
    }

    public MyServiceException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
