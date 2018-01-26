package com.gnnt.admin.exception;

/**
 * 描述：
 * add by 符晶晶
 * 2018-01-26 14:39
 **/
public class MyRuntimeException extends RuntimeException{
    public MyRuntimeException() {
        super();
    }

    public MyRuntimeException(String message) {
        super(message);
    }

    public MyRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public MyRuntimeException(Throwable cause) {
        super(cause);
    }

    protected MyRuntimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
