package com.base.web.exception;


public class ValidateCodeOverdueException extends RuntimeException {
    public ValidateCodeOverdueException() {
        super("验证码已过期");
    }

    public ValidateCodeOverdueException(String message) {
        super(message);
    }

    public ValidateCodeOverdueException(Throwable cause) {
        super(cause);
    }

    public ValidateCodeOverdueException(String message, Throwable cause) {
        super(message, cause);
    }
}
