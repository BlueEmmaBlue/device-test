package com.skinairvalve.sz.exception;

import lombok.Data;

/**
 * @create on 2023/9/7-7:15 PM
 */
@Data
public class SystemException extends RuntimeException {
    private int code;
    private String message;
}