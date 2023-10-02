package com.skinairvalve.sz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.skinairvalve.sz.dto.device.AddDeviceInfo;
import com.skinairvalve.sz.dto.device.BaseDeviceInfo;
import com.skinairvalve.sz.dto.device.SzDeviceSearchParam;
import com.skinairvalve.sz.dto.device.UpdateDeviceInfo;
import com.skinairvalve.sz.entity.SzDevice;
import com.skinairvalve.sz.mapper.SzDeviceMapper;
import com.skinairvalve.sz.service.ISzDeviceService;
import com.skinairvalve.sz.service.ISzDeviceTypeService;
import com.skinairvalve.sz.utils.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.Map;

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
public class SzDeviceServiceImpl extends ServiceImpl<SzDeviceMapper, SzDevice> implements ISzDeviceService {

    @Lazy
    @Resource
    private ISzDeviceTypeService deviceTypeService;


    @Override
    public boolean addDevice(AddDeviceInfo  deviceInfo) {
        SzDevice existDevice = selectByDeviceId(deviceInfo.getDeviceId());
        if(existDevice != null){
            return false;
        }

        SzDevice szDevice = new SzDevice();
        BeanUtils.copyProperties(deviceInfo,szDevice);
        return save(szDevice);
    }

    @Override
    public boolean updateDevice(UpdateDeviceInfo baseDeviceInfo) {
        SzDevice device = selectByDeviceId(baseDeviceInfo.getDeviceId());
        if(device == null){
            return false;
        }else {
            if(StringUtils.isNotBlank(baseDeviceInfo.getUpperThreshold())){
                device.setUpperThreshold(baseDeviceInfo.getUpperThreshold());
            }

            if(StringUtils.isNotBlank(baseDeviceInfo.getLowerThreshold())){
                device.setLowerThreshold(baseDeviceInfo.getLowerThreshold());
            }
            return updateById(device);
        }
    }

    @Override
    public Page<BaseDeviceInfo> searchDevice(SzDeviceSearchParam szDeviceSearchParam) {
        LambdaQueryWrapper<SzDevice> queryWrapper = new LambdaQueryWrapper<>();
        if(StringUtils.isNotBlank(szDeviceSearchParam.getDeviceType())){
            queryWrapper.eq(SzDevice::getDeviceType,szDeviceSearchParam.getDeviceType());
        }

        if(StringUtils.isNotBlank(szDeviceSearchParam.getDeviceId())){
            queryWrapper.eq(SzDevice::getDeviceId,szDeviceSearchParam.getDeviceId());
        }

        Page<SzDevice> page = new Page<>(szDeviceSearchParam.getPageNo(),szDeviceSearchParam.getPageSize());
        page = page(page,queryWrapper);

        Page<BaseDeviceInfo> resultPage =
                PageHelper.convertPage(page,BaseDeviceInfo::new);
        if(resultPage != null && (!CollectionUtils.isEmpty(resultPage.getRecords()))){
            Map<String,String> deviceTypeInfo = deviceTypeService
                    .deviceNameToType();
            for(BaseDeviceInfo deviceInfo : resultPage.getRecords()){
                deviceInfo.setDeviceTypeName(deviceTypeInfo.getOrDefault(deviceInfo.getDeviceType(),""));
            }
        }
        return resultPage;
    }

    @Override
    public SzDevice selectByDeviceId(String deviceId){
        LambdaQueryWrapper<SzDevice> queryWrapper =
                new LambdaQueryWrapper<>();
        queryWrapper.eq(SzDevice::getDeviceId,deviceId);

        List<SzDevice> list =
                list(queryWrapper);
        return (list != null && (! list.isEmpty())) ? list.get(0) : null;
    }

    @Override
    public List<SzDevice> selectByDeviceIdList(List<String> deviceIdList) {
        if(deviceIdList == null || deviceIdList.isEmpty()){
            return Collections.emptyList();
        }
        LambdaQueryWrapper<SzDevice> queryWrapper =
                new LambdaQueryWrapper<>();
        queryWrapper.in(SzDevice::getDeviceId,deviceIdList);
        return list(queryWrapper);
    }
}