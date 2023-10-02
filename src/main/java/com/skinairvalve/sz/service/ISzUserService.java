package com.skinairvalve.sz.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.skinairvalve.sz.dto.user.SzUserInfo;
import com.skinairvalve.sz.dto.user.SzUserInfoDto;
import com.skinairvalve.sz.dto.user.SzUserSearchParam;
import com.skinairvalve.sz.entity.SzUser;

import java.util.List;

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

    boolean addUser(SzUser currentUser, SzUserInfoDto signUpDto);

    boolean updateUser(SzUser currentUser, SzUserInfoDto userInfo);

    boolean deleteUser(SzUser currentUser,String username);

    List<SzUser> allUser(SzUser szUser);

    Page<SzUserInfo> searchUser(SzUser currentUser, SzUserSearchParam szUserSearchParam);

    void updateLoginTime(String username);

    List<SzUser> selectByUsernameList(List<String> usernameList);
}
