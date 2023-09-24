package com.skinairvalve.sz.dto.user;

import com.skinairvalve.sz.entity.SzUser;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotBlank;

/**
 * @author zhangwei zhangwei06@kuaishou.com
 * @create on 2023/9/25-12:15 AM
 */
@Data
@NoArgsConstructor
public class SzUserInfo {
    private String name;

    @NotBlank
    private String username;

    @NotBlank
    private String role;

    public SzUserInfo(SzUser szUser){
        BeanUtils.copyProperties(szUser,this);
    }
}
