package com.skinairvalve.sz.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @create on 2023/9/7-4:52 PM
 */
@AllArgsConstructor
@Getter
public enum ResultEnum {
    OK(0,""),
    FAIL(1,"");

    private int code;
    private String message;
}
