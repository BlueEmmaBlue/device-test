package com.skinairvalve.sz.controller;

import com.skinairvalve.sz.dto.ApiResult;
import com.skinairvalve.sz.service.ISzDeviceService;
import com.skinairvalve.sz.service.impl.SzDeviceServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
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

}
