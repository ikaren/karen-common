package org.karen.common.exception;

/**
 * 服务异常
 */
public class CommonException extends RuntimeException {

    public CommonException() {
        super();
    }

    public CommonException(String message, Throwable cause) {
        super(message, cause);
    }

    public CommonException(String message) {
        super(message);
    }

    public CommonException(Throwable cause) {
        super(cause);
    }
}
