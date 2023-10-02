package com.skinairvalve.sz.dto.device;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author zhangwei zhangwei06@kuaishou.com
 * @create on 2023/10/2-3:01 PM
 */
@Data
public class UpdateDeviceInfo {
    @NotBlank
    @ApiModelProperty("设备id")
    private String deviceId;

    @ApiModelProperty("设备类型")
    private String deviceType;

    @ApiModelProperty("设备名")
    private String deviceName;

    @ApiModelProperty("设备描述")
    private String deviceDesc;

    @ApiModelProperty("阈值上限")
    private String upperThreshold;

    @ApiModelProperty("阈值下限")
    private String lowerThreshold;
}
