package com.skinairvalve.sz.service;

import com.skinairvalve.sz.dto.user.SignUpDto;
import com.skinairvalve.sz.entity.SzUser;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zw
 * @since 2023-09-18
 */
public interface ISzUserService extends IService<SzUser> {

    SzUser selectEnableUserByUsername(String username);

    SzUser selectByUsername(String username);

    boolean addUser(SzUser currentUser, SignUpDto signUpDto);
}
