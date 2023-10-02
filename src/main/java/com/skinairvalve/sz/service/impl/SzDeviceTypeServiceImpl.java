package com.skinairvalve.sz.service.impl;

import com.skinairvalve.sz.entity.SzDeviceType;
import com.skinairvalve.sz.mapper.SzDeviceTypeMapper;
import com.skinairvalve.sz.service.ISzDeviceTypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zw
 * @since 2023-10-02
 */
@Service
public class SzDeviceTypeServiceImpl extends ServiceImpl<SzDeviceTypeMapper, SzDeviceType> implements ISzDeviceTypeService {
    @Override
    public Map<String, String> deviceNameToType() {
        List<SzDeviceType> deviceTypeList = list();
        return deviceTypeList
                .stream()
                .collect(Collectors.toMap(SzDeviceType::getDeviceType,SzDeviceType::getDeviceTypeName,(a,b) -> a));
    }
}
