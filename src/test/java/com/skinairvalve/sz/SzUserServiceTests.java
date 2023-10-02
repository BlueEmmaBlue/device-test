package com.skinairvalve.sz;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.skinairvalve.sz.dto.user.SzUserInfo;
import com.skinairvalve.sz.dto.user.SzUserSearchParam;
import com.skinairvalve.sz.entity.SzUser;
import com.skinairvalve.sz.service.ISzUserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;

/**
 * @create on 2023/10/2-2:20 PM
 */
@Slf4j
public class SzUserServiceTests extends DeviceTestApplicationTests{
    @Resource
    private ISzUserService szUserService;

    @Test
    public void testSearch(){
        SzUser currentUser = new SzUser();
        currentUser.setAccountRole("ADMIN");

        SzUserSearchParam param = new SzUserSearchParam();
        param.setUsername("admin");
        param.setAccountRole("ADMIN");

        Page<SzUserInfo> userInfoPage = szUserService
                .searchUser(currentUser,param);
        Assertions.assertNotNull(userInfoPage);
        Assertions.assertFalse(userInfoPage.getRecords().isEmpty());
    }
}
