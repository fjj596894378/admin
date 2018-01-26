package com.gnnt.admin.exceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.validation.ConstraintViolationException;

/**
 * 描述：全局异常处理
 * add by 符晶晶
 * 2018-01-26 14:19
 **/
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    @ExceptionHandler(value = { ConstraintViolationException.class })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrorResponse constraintViolationException(ConstraintViolationException ex) {
        return new ApiErrorResponse(500, 5001, ex.getMessage());
    }

    @ExceptionHandler(value = { IllegalArgumentException.class })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrorResponse IllegalArgumentException(IllegalArgumentException ex) {
        return new ApiErrorResponse(501, 5002, ex.getMessage());
    }

    @ExceptionHandler(value = { NoHandlerFoundException.class })
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiErrorResponse noHandlerFoundException(Exception ex) {
        return new ApiErrorResponse(404, 4041, ex.getMessage());
    }


    @ExceptionHandler(value = { Exception.class })
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiErrorResponse unknownException(Exception ex) {
        return new ApiErrorResponse(500, 5002, ex.toString());
    }
}
