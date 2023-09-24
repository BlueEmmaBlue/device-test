package com.skinairvalve.sz.dto.user;

import lombok.Data;

import javax.validation.constraints.NotBlank;


/**
 * @create on 2023/9/6-8:05 PM
 */
@Data
public class SignUpDto {
    private String name;

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    @NotBlank
    private String role;
}
