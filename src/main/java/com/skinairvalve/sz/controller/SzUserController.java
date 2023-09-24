package com.skinairvalve.sz.controller;

import com.skinairvalve.sz.dto.ApiResult;
import com.skinairvalve.sz.dto.user.SzUserInfo;
import com.skinairvalve.sz.entity.SzUser;
import com.skinairvalve.sz.service.ISzUserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
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
@Controller
@RequestMapping("/szUser")
public class SzUserController {
    @Resource
    private ISzUserService szUserService;

    @GetMapping("/list")
    public ApiResult<List<SzUserInfo>> userList() {
        return ApiResult.ok(szUserService
                .list()
                .stream()
                .map(SzUserInfo::new)
                .collect(Collectors.toList()));
    }
}
