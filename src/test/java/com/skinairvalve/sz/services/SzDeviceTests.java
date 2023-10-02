package com.skinairvalve.sz.services;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.skinairvalve.sz.DeviceTestApplicationTests;
import com.skinairvalve.sz.dto.device.AddDeviceInfo;
import com.skinairvalve.sz.dto.device.BaseDeviceInfo;
import com.skinairvalve.sz.dto.device.SzDeviceSearchParam;
import com.skinairvalve.sz.dto.device.UpdateDeviceInfo;
import com.skinairvalve.sz.service.ISzDeviceService;
import com.skinairvalve.sz.service.ISzDeviceTypeService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @create on 2023/10/2-3:22 PM
 */
@Slf4j
public class SzDeviceTests extends DeviceTestApplicationTests {
    @Resource
    private ISzDeviceService deviceService;

    @Resource
    private ISzDeviceTypeService deviceTypeService;

    @Test
    public void testDeviceType(){
        Map<String,String> deviceTypeName = deviceTypeService
                .deviceNameToType();
        Assertions.assertNotNull(deviceTypeName);
        Assertions.assertFalse(deviceTypeName.isEmpty());
    }

    @Test
    public void testDeviceApi(){
        AddDeviceInfo baseDeviceInfo = podamFactory
                .manufacturePojo(AddDeviceInfo.class);
        Assertions.assertNotNull(baseDeviceInfo.getDeviceId());
        baseDeviceInfo.setDeviceId("device_0");
        baseDeviceInfo.setDeviceType("device_0");
        Assertions.assertTrue(deviceService.addDevice(baseDeviceInfo));
        Assertions.assertFalse(deviceService.addDevice(baseDeviceInfo));

        UpdateDeviceInfo updateDeviceInfo = new UpdateDeviceInfo();
        updateDeviceInfo.setDeviceId(baseDeviceInfo.getDeviceId());
        updateDeviceInfo.setLowerThreshold("lower");
        Assertions.assertTrue(deviceService.updateDevice(updateDeviceInfo));

        baseDeviceInfo = podamFactory.manufacturePojo(AddDeviceInfo.class);
        Assertions.assertTrue(deviceService.addDevice(baseDeviceInfo));

        SzDeviceSearchParam szDeviceSearchParam = new SzDeviceSearchParam();
        szDeviceSearchParam.setDeviceType("device_0");

        Page<BaseDeviceInfo> deviceInfoPage = deviceService
                .searchDevice(szDeviceSearchParam);
        Assertions.assertNotNull(deviceInfoPage);
        Assertions.assertNotNull(deviceInfoPage.getRecords());
        Assertions.assertFalse(deviceInfoPage.getRecords().isEmpty());
        Assertions.assertFalse(deviceInfoPage.getRecords().get(0).getDeviceTypeName().isEmpty());
    }
}
