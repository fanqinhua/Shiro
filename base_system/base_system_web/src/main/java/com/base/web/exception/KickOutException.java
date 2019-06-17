package com.base.web.exception;


public class KickOutException extends RuntimeException{
    public KickOutException() {
        super("账号已在别处登录");
    }

    public KickOutException(String message) {
        super(message);
    }

    public KickOutException(Throwable cause) {
        super(cause);
    }

    public KickOutException(String message, Throwable cause) {
        super(message, cause);
    }
}
