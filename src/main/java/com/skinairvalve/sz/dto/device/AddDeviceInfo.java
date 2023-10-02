package com.skinairvalve.sz.dto.device;

import com.skinairvalve.sz.entity.SzDevice;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotBlank;

/**
 * @create on 2023/10/2-1:40 PM
 */
@Data
@NoArgsConstructor
public class AddDeviceInfo {

    @NotBlank
    @ApiModelProperty("设备id")
    private String deviceId;

    @NotBlank
    @ApiModelProperty("设备类型")
    private String deviceType;

    @ApiModelProperty("设备名")
    private String deviceName;

    @ApiModelProperty("设备描述")
    private String deviceDesc;

    @NotBlank
    @ApiModelProperty("阈值上限")
    private String upperThreshold;

    @NotBlank
    @ApiModelProperty("阈值下限")
    private String lowerThreshold;

    public AddDeviceInfo(SzDevice szDevice){
        BeanUtils.copyProperties(szDevice,this);
    }
}
