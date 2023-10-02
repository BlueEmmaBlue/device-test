package com.skinairvalve.sz.config;

import com.skinairvalve.sz.dto.ApiResult;
import com.skinairvalve.sz.exception.SystemException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@Slf4j
@RestControllerAdvice
@Order(0)
public class GlobalDefaultExceptionHandler {

    @ExceptionHandler({SystemException.class})
    public ApiResult<String> systemExceptionHandler(SystemException e) {
        log.warn("catch system error", e);
        return new ApiResult<>(e.getCode(), e.getMessage());
    }

    @ExceptionHandler({RuntimeException.class})
    public ApiResult<String> runtimeExceptionHandler(RuntimeException e) {
        log.warn("catch system error", e);
        return ApiResult.fail(e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ApiResult<String> exceptionHandler(Exception e) {
        log.warn("catch error", e);
        return ApiResult.fail("system error!");
    }
}
