package com.skinairvalve.sz.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.skinairvalve.sz.dto.record.AddDataRecord;
import com.skinairvalve.sz.dto.record.BaseDataRecordInfo;
import com.skinairvalve.sz.dto.record.SzDataRecordSearchParam;
import com.skinairvalve.sz.entity.SzDataRecord;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zw
 * @since 2023-09-18
 */
public interface ISzDataRecordService extends IService<SzDataRecord> {
    boolean addDataRecord(AddDataRecord addDataRecord);

    Page<BaseDataRecordInfo> searchDataRecord(SzDataRecordSearchParam szDataRecordSearchParam);
}
