package com.skinairvalve.sz.dto.user;

import com.skinairvalve.sz.entity.SzUser;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotBlank;

/**
 * @create on 2023/9/18-5:08 PM
 */
@Data
@NoArgsConstructor
public class BaseUserInfo {
    private String name;

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    @NotBlank
    private String role;

    public BaseUserInfo(SzUser szUser){
        BeanUtils.copyProperties(szUser,this);
    }
}
