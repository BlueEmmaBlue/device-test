package com.skinairvalve.sz.dto.device;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @create on 2023/10/2-1:40 PM
 */
@Data
@NoArgsConstructor
public class BaseDeviceInfo {
    private Integer id;

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
