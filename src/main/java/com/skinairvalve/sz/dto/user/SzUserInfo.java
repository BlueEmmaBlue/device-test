package com.skinairvalve.sz.dto.user;

import com.skinairvalve.sz.entity.SzUser;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

/**
 * @author zhangwei zhangwei06@kuaishou.com
 * @create on 2023/9/25-12:15 AM
 */
@Data
@NoArgsConstructor
public class SzUserInfo {
    private String name;

    private String username;

    private String accountRole;

    private String password;

    public SzUserInfo(SzUser szUser){
        BeanUtils.copyProperties(szUser,this);
    }
}
