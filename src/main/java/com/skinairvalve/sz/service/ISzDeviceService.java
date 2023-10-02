package com.skinairvalve.sz.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.skinairvalve.sz.dto.device.AddDeviceInfo;
import com.skinairvalve.sz.dto.device.BaseDeviceInfo;
import com.skinairvalve.sz.dto.device.SzDeviceSearchParam;
import com.skinairvalve.sz.dto.device.UpdateDeviceInfo;
import com.skinairvalve.sz.entity.SzDevice;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zw
 * @since 2023-09-18
 */
public interface ISzDeviceService extends IService<SzDevice> {
    boolean addDevice(AddDeviceInfo deviceInfo);

    boolean updateDevice(UpdateDeviceInfo baseDeviceInfo);

    Page<BaseDeviceInfo> searchDevice(SzDeviceSearchParam szDeviceSearchParam);

    SzDevice selectByDeviceId(String deviceId);
}
