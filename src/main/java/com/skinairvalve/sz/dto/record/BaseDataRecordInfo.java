package com.skinairvalve.sz.dto.record;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.skinairvalve.sz.dto.device.BaseDeviceInfo;
import com.skinairvalve.sz.dto.user.BaseUserInfo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @create on 2023/10/2-4:15 PM
 */
@Data
@NoArgsConstructor
public class BaseDataRecordInfo {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("上传用户")
    private String uploadUser;

    @ApiModelProperty("上传用户详情")
    private BaseUserInfo uploadUserInfo;

    @ApiModelProperty("上传设备id")
    private String uploadDeviceId;

    @ApiModelProperty("上传设备信息")
    private BaseDeviceInfo uploadDeviceInfo;

    @ApiModelProperty("上传时间")
    private LocalDateTime uploadTime;

    private Integer createId;

    private LocalDateTime createTime;

    private Integer updateId;

    private LocalDateTime updateTime;

    private String filePath;

    @ApiModelProperty("同步状态,NOT_UPLOAD,UPLOAD")
    private String uploadServerStatus;

    @ApiModelProperty("上传设备类型")
    private String uploadDeviceType;

    @ApiModelProperty("部品序列号")
    private String uploadSerialId;
}
