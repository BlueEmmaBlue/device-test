package com.skinairvalve.sz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.skinairvalve.sz.entity.SzDeviceType;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zw
 * @since 2023-10-02
 */
public interface ISzDeviceTypeService extends IService<SzDeviceType> {
    Map<String,String> deviceNameToType();
}
