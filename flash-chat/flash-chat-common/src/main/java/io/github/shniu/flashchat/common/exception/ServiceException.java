package io.github.shniu.flashchat.common.exception;

/**
 * @author niushaohan
 * @date 2020/12/23 10
 */
public class ServiceException extends RuntimeException {
    private String code;

    public ServiceException(String code) {
        super();
        this.code = code;
    }

    public ServiceException(String code, String message) {
        super(message);
        this.code = code;
    }

    public ServiceException(String code, String message, Throwable e) {
        super(message, e);
        this.code = code;
    }
}
