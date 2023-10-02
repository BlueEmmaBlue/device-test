package com.skinairvalve.sz.services;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.databind.cfg.BaseSettings;
import com.skinairvalve.sz.DeviceTestApplicationTests;
import com.skinairvalve.sz.dto.record.AddDataRecord;
import com.skinairvalve.sz.dto.record.BaseDataRecordInfo;
import com.skinairvalve.sz.dto.record.SzDataRecordSearchParam;
import com.skinairvalve.sz.service.ISzDataRecordService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.security.test.context.support.WithMockUser;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * @author zhangwei zhangwei06@kuaishou.com
 * @create on 2023/10/2-4:42 PM
 */
@Slf4j
@WithMockUser(username = "admin",roles = {"ADMIN"})
public class SzDataRecordServiceTests extends DeviceTestApplicationTests {
    @Resource
    private ISzDataRecordService dataRecordService;

    @Test
    public void testDeviceRecord(){
        AddDataRecord addDataRecord =
                podamFactory.manufacturePojo(AddDataRecord.class);
        String deviceId = "device_0";
        addDataRecord.setUploadDeviceId(deviceId);
        addDataRecord.setUploadSerialId(deviceId);

        Assertions.assertTrue(dataRecordService.addDataRecord(addDataRecord));

        SzDataRecordSearchParam szDataRecordSearchParam = new SzDataRecordSearchParam();
        szDataRecordSearchParam.setDeviceType(deviceId);
        szDataRecordSearchParam.setStartTime(LocalDateTime.now().minusDays(1));
        szDataRecordSearchParam.setEndTime(LocalDateTime.now().plusDays(1));

        Page<BaseDataRecordInfo> searchResult = dataRecordService
                .searchDataRecord(szDataRecordSearchParam);
        Assertions.assertNotNull(searchResult);
        Assertions.assertNotNull(searchResult.getRecords());
        Assertions.assertFalse(searchResult.getRecords().isEmpty());
        BaseDataRecordInfo recordInfo = searchResult.getRecords().get(0);
        Assertions.assertFalse(recordInfo == null || recordInfo.getUploadDeviceInfo() == null || recordInfo.getUploadUserInfo() == null);
    }
}
