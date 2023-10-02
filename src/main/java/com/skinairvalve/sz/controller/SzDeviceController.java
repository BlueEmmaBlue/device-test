package com.skinairvalve.sz.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.skinairvalve.sz.dto.ApiResult;
import com.skinairvalve.sz.dto.device.AddDeviceInfo;
import com.skinairvalve.sz.dto.device.BaseDeviceInfo;
import com.skinairvalve.sz.dto.device.SzDeviceSearchParam;
import com.skinairvalve.sz.dto.device.UpdateDeviceInfo;
import com.skinairvalve.sz.entity.SzDeviceType;
import com.skinairvalve.sz.service.ISzDeviceService;
import com.skinairvalve.sz.service.ISzDeviceTypeService;
import com.skinairvalve.sz.service.UserDetailService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author zw
 * @since 2023-09-18
 */
@Controller
@RequestMapping("/szDevice")
public class SzDeviceController {

    @Resource
    private ISzDeviceService deviceService;

    @Resource
    private ISzDeviceTypeService szDeviceTypeService;

    @Resource
    private UserDetailService userDetailService;

    @GetMapping("/deviceType")
    public ApiResult<List<SzDeviceType>> allDeviceType() {
        userDetailService.checkAdmin();
        return ApiResult.ok(szDeviceTypeService.list());
    }

    @PostMapping("/add")
    public ApiResult<Boolean> addDevice(@Valid @RequestBody AddDeviceInfo deviceInfo) {
        userDetailService.checkAdmin();
        return ApiResult.ok(deviceService.addDevice(deviceInfo));
    }

    @PostMapping("/update")
    public ApiResult<Boolean> updateDevice(@Valid @RequestBody UpdateDeviceInfo updateDeviceInfo) {
        userDetailService.checkAdmin();
        return ApiResult.ok(deviceService.updateDevice(updateDeviceInfo));
    }

    @PostMapping("/search")
    public ApiResult<Page<BaseDeviceInfo>> searchDevice(@RequestBody SzDeviceSearchParam szDeviceSearchParam) {
        userDetailService.checkAdmin();
        return ApiResult.ok(deviceService.searchDevice(szDeviceSearchParam));
    }
}
