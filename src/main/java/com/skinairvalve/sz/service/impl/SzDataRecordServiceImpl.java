package com.skinairvalve.sz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.base.Preconditions;
import com.skinairvalve.sz.dto.device.BaseDeviceInfo;
import com.skinairvalve.sz.dto.record.AddDataRecord;
import com.skinairvalve.sz.dto.record.BaseDataRecordInfo;
import com.skinairvalve.sz.dto.record.SzDataRecordSearchParam;
import com.skinairvalve.sz.dto.user.BaseUserInfo;
import com.skinairvalve.sz.entity.SzDataRecord;
import com.skinairvalve.sz.entity.SzDevice;
import com.skinairvalve.sz.entity.SzUser;
import com.skinairvalve.sz.enums.UploadToServerStatus;
import com.skinairvalve.sz.mapper.SzDataRecordMapper;
import com.skinairvalve.sz.service.ISzDataRecordService;
import com.skinairvalve.sz.service.ISzDeviceService;
import com.skinairvalve.sz.service.ISzUserService;
import com.skinairvalve.sz.service.UserDetailService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zw
 * @since 2023-09-18
 */
@Slf4j
@Service
public class SzDataRecordServiceImpl extends ServiceImpl<SzDataRecordMapper, SzDataRecord> implements ISzDataRecordService {
    @Lazy
    @Resource
    private ISzUserService userService;

    @Lazy
    @Resource
    private UserDetailService userDetailService;

    @Lazy
    @Resource
    private ISzDeviceService deviceService;

    @Override
    public boolean addDataRecord(AddDataRecord addDataRecord) {
        SzUser uploadUser = userDetailService.getCurrentUser();
        SzDevice device = deviceService.selectByDeviceId(addDataRecord.getUploadDeviceId());
        Preconditions.checkState(device != null,"invalid device id");

        SzDataRecord dataRecord = new SzDataRecord();
        dataRecord.setUploadUser(uploadUser.getUsername());
        dataRecord.setUploadTime(LocalDateTime.now());
        dataRecord.setCreateId(uploadUser.getId());
        dataRecord.setCreateTime(LocalDateTime.now());
        dataRecord.setUpdateId(dataRecord.getCreateId());
        dataRecord.setUpdateTime(dataRecord.getCreateTime());
        dataRecord.setUploadDeviceId(device.getDeviceId());
        dataRecord.setUploadDeviceType(device.getDeviceType());
        dataRecord.setUploadSerialId(addDataRecord.getUploadSerialId());
        dataRecord.setUploadServerStatus(UploadToServerStatus.NOT_UPLOAD.name());
        return save(dataRecord);
    }

    @Override
    public Page<BaseDataRecordInfo> searchDataRecord(SzDataRecordSearchParam szDataRecordSearchParam) {
        LambdaQueryWrapper<SzDataRecord> queryWrapper =
                new LambdaQueryWrapper<>();
        if(StringUtils.isNotBlank(szDataRecordSearchParam.getSerialId())){
            queryWrapper.eq(SzDataRecord::getUploadSerialId,szDataRecordSearchParam.getSerialId());
        }

        if (StringUtils.isNotBlank(szDataRecordSearchParam.getDeviceType())) {
            queryWrapper.eq(SzDataRecord::getUploadDeviceType,szDataRecordSearchParam.getDeviceType());
        }

        if(szDataRecordSearchParam.getStartTime() != null){
            queryWrapper.ge(SzDataRecord::getUploadTime,szDataRecordSearchParam.getStartTime());
        }

        if(szDataRecordSearchParam.getEndTime() != null){
            queryWrapper.le(SzDataRecord::getUploadTime,szDataRecordSearchParam.getEndTime());
        }

        Page<SzDataRecord> page = new Page<>(szDataRecordSearchParam.getPageNo(), szDataRecordSearchParam.getPageSize());
        page = page(page,queryWrapper);
        return convertPage(page);
    }

    private Page<BaseDataRecordInfo> convertPage(Page<SzDataRecord> page) {
        Page<BaseDataRecordInfo> resultPage = new Page<>();
        BeanUtils.copyProperties(page,resultPage);

        List<BaseDataRecordInfo> resultInfoList = convertRecords(page.getRecords());
        resultPage.setRecords(resultInfoList);
        return resultPage;
    }

    private List<BaseDataRecordInfo> convertRecords(List<SzDataRecord> records) {
        if(records == null || records.isEmpty()){
            return Collections.emptyList();
        }else{
            List<BaseDataRecordInfo> resultList = new ArrayList<>();

            List<String> usernameList = records
                    .stream()
                    .map(SzDataRecord::getUploadUser)
                    .distinct()
                    .collect(Collectors.toList());
            Map<String,SzUser> userInfoByUsername = userService
                    .selectByUsernameList(usernameList)
                    .stream()
                    .collect(Collectors.toMap(SzUser::getUsername, Function.identity(),(a, b) -> a));

            List<String> deviceIdList = records
                    .stream()
                    .map(SzDataRecord::getUploadDeviceId)
                    .distinct()
                    .collect(Collectors.toList());
            Map<String,SzDevice> deviceByDeviceId = deviceService
                    .selectByDeviceIdList(deviceIdList)
                    .stream()
                    .collect(Collectors.toMap(SzDevice::getDeviceId,Function.identity(),(a,b) -> a));

            for(SzDataRecord dataRecord : records){
                BaseDataRecordInfo baseDataRecordInfo = convertRecord(dataRecord,userInfoByUsername,deviceByDeviceId);
                if(baseDataRecordInfo != null) {
                    resultList.add(baseDataRecordInfo);
                }
            }
            log.info("data record list => {},result list => {}",records.size(),
                    resultList.size());
            return resultList;
        }
    }

    private BaseDataRecordInfo convertRecord(SzDataRecord dataRecord, Map<String, SzUser> userInfoByUsername, Map<String, SzDevice> deviceByDeviceId) {
        if(dataRecord == null){
            return null;
        }else{
            BaseDataRecordInfo baseDataRecordInfo = new BaseDataRecordInfo();
            BeanUtils.copyProperties(dataRecord,baseDataRecordInfo);
            baseDataRecordInfo.setUploadUserInfo(new BaseUserInfo(userInfoByUsername.get(baseDataRecordInfo.getUploadUser())));
            baseDataRecordInfo.setUploadDeviceInfo(new BaseDeviceInfo(deviceByDeviceId.get(baseDataRecordInfo.getUploadDeviceId())));
            return baseDataRecordInfo;
        }
    }
}
