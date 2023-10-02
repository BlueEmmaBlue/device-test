package com.skinairvalve.sz.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.skinairvalve.sz.dto.ApiResult;
import com.skinairvalve.sz.dto.record.AddDataRecord;
import com.skinairvalve.sz.dto.record.BaseDataRecordInfo;
import com.skinairvalve.sz.dto.record.SzDataRecordSearchParam;
import com.skinairvalve.sz.service.ISzDataRecordService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author zw
 * @since 2023-10-02
 */
@Controller
@RequestMapping("/szDataRecord")
public class SzDataRecordController {
    @Resource
    private ISzDataRecordService dataRecordService;

    @PostMapping("/add")
    public ApiResult<Boolean> addRecord(@Valid @RequestBody AddDataRecord dataRecord) {
        return ApiResult.ok(dataRecordService.addDataRecord(dataRecord));
    }

    @PostMapping("/search")
    public ApiResult<Page<BaseDataRecordInfo>> search(@RequestBody SzDataRecordSearchParam szDataRecordSearchParam) {
        return ApiResult.ok(dataRecordService.searchDataRecord(szDataRecordSearchParam));
    }

    @GetMapping("/print")
    //TODO
    public ApiResult<Boolean> print(@RequestParam("dataId")Integer dataId){
        return ApiResult.ok(true);
    }

    @GetMapping("/export")
    //TODO
    public ApiResult<Boolean> export(@RequestParam("dataId")Integer dataId){
        return ApiResult.ok(true);
    }

    @GetMapping("/upload")
    //TODO
    public ApiResult<Boolean> upload(@RequestParam("dataId")Integer dataId){
        return ApiResult.ok(true);
    }
}
