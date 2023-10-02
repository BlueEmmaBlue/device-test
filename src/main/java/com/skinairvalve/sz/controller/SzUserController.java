package com.skinairvalve.sz.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.skinairvalve.sz.dto.ApiResult;
import com.skinairvalve.sz.dto.user.SzUserInfo;
import com.skinairvalve.sz.dto.user.SzUserInfoDto;
import com.skinairvalve.sz.dto.user.SzUserSearchParam;
import com.skinairvalve.sz.entity.SzUser;
import com.skinairvalve.sz.service.ISzUserService;
import com.skinairvalve.sz.service.UserDetailService;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author zw
 * @since 2023-09-18
 */
@RestController
@RequestMapping("/szUser")
public class SzUserController {
    @Resource
    private ISzUserService szUserService;

    @Resource
    private UserDetailService userDetailService;

    @GetMapping("/list")
    public ApiResult<List<SzUserInfo>> userList() {
        return ApiResult.ok(szUserService
                .allUser(userDetailService.getCurrentUser())
                .stream()
                .map(SzUserInfo::new)
                .collect(Collectors.toList()));
    }

    @PostMapping("/search")
    public ApiResult<Page<SzUserInfo>> searchUserList(@RequestBody SzUserSearchParam szUserSearchParam) {
        return ApiResult.ok(szUserService
                .searchUser(userDetailService.getCurrentUser(),szUserSearchParam));
    }

    @PostMapping("/add")
    public ApiResult<String> registerUser(@Valid @RequestBody SzUserInfoDto signUpDto){

        // add check for username exists in a DB
        if(! szUserService.addUser(userDetailService.getCurrentUser(),signUpDto)){
            return ApiResult.fail("Username is already taken!");
        }
        return ApiResult.ok("add user successfully");

    }

    @PostMapping("/update")
    public ApiResult<SzUserInfo> updateUser(@RequestBody SzUserInfoDto szUserInfo){
        if(szUserService.updateUser(userDetailService.getCurrentUser(),
                szUserInfo)){
            return ApiResult.ok(new SzUserInfo(szUserService.selectEnableUserByUsername(szUserInfo.getUsername())));
        }else{
            return ApiResult.fail("error update user");
        }
    }

    @PostMapping("/delete")
    public ApiResult<Boolean> removeUser(@RequestParam("username")String username){
        return ApiResult.ok(szUserService.deleteUser(
                userDetailService.getCurrentUser(),
                username));
    }
}
