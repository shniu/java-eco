package io.github.shniu.flashchat.common.exception;

/**
 * @author niushaohan
 * @date 2020/12/23 10
 */
public class ConnectionException extends ServiceException {
    public ConnectionException(String code) {
        super(code);
    }

    public ConnectionException(String code, String message) {
        super(code, message);
    }

    public ConnectionException(String code, String message, Throwable e) {
        super(code, message, e);
    }
}
