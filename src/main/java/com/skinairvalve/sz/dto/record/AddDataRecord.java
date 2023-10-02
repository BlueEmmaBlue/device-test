package com.skinairvalve.sz.dto.record;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * @author zhangwei zhangwei06@kuaishou.com
 * @create on 2023/10/2-4:17 PM
 */
@Data
//TODO data
public class AddDataRecord {

//    @NotNull
    @ApiModelProperty("上传设备id")
    private String uploadDeviceId;

//    @NotBlank
    @ApiModelProperty("部品序列号")
    private String uploadSerialId;
}
