package com.skinairvalve.sz.dto.record;

import com.skinairvalve.sz.dto.PageParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author zhangwei zhangwei06@kuaishou.com
 * @create on 2023/10/2-4:21 PM
 */
@Data
public class SzDataRecordSearchParam extends PageParam {
    @ApiModelProperty("开始时间")
    private LocalDateTime startTime;

    @ApiModelProperty("截止时间")
    private LocalDateTime endTime;

    @ApiModelProperty("设备类型")
    private String deviceType;

    @ApiModelProperty("部品序列号")
    private String serialId;
}
