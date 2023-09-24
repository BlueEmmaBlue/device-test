package com.skinairvalve.sz.dto;

import com.skinairvalve.sz.enums.ResultEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @create on 2023/9/7-4:51 PM
 */
@Data
@NoArgsConstructor
public class ApiResult<T> {
    private int code;

    private String message;

    private T data;

    public ApiResult(ResultEnum resultEnum){
        this.code = resultEnum.getCode();
        this.message = resultEnum.getMessage();
    }

    public ApiResult(int code,String message){
        this.code = code;
        this.message = message;
    }

    public static <T> ApiResult<T> ok(T data){
        ApiResult<T> result = new ApiResult<>(ResultEnum.OK);
        result.data = data;
        return result;
    }

    public static <T> ApiResult<T> fail(String message){
        ApiResult<T> result = new ApiResult<>(ResultEnum.FAIL);
        result.message = message;
        return result;
    }
}
