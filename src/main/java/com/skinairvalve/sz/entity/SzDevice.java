package com.skinairvalve.sz.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 
 * </p>
 *
 * @author zw
 * @since 2023-09-18
 */
@TableName("sz_device")
@ApiModel(value = "SzDevice对象", description = "")
public class SzDevice implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("设备id")
    private String deviceId;

    @ApiModelProperty("设备类型")
    private String deviceType;

    @ApiModelProperty("设备名")
    private String deviceName;

    @ApiModelProperty("设备描述")
    private String deviceDesc;

    @ApiModelProperty("创建人")
    private Integer createId;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("更新人")
    private Integer updateId;

    @ApiModelProperty("更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty("阈值上限")
    private String upperThreshold;

    @ApiModelProperty("阈值下限")
    private String lowerThreshold;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getDeviceDesc() {
        return deviceDesc;
    }

    public void setDeviceDesc(String deviceDesc) {
        this.deviceDesc = deviceDesc;
    }

    public Integer getCreateId() {
        return createId;
    }

    public void setCreateId(Integer createId) {
        this.createId = createId;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public Integer getUpdateId() {
        return updateId;
    }

    public void setUpdateId(Integer updateId) {
        this.updateId = updateId;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpperThreshold() {
        return upperThreshold;
    }

    public void setUpperThreshold(String upperThreshold) {
        this.upperThreshold = upperThreshold;
    }

    public String getLowerThreshold() {
        return lowerThreshold;
    }

    public void setLowerThreshold(String lowerThreshold) {
        this.lowerThreshold = lowerThreshold;
    }

    @Override
    public String toString() {
        return "SzDevice{" +
            "id = " + id +
            ", deviceId = " + deviceId +
            ", deviceType = " + deviceType +
            ", deviceName = " + deviceName +
            ", deviceDesc = " + deviceDesc +
            ", createId = " + createId +
            ", createTime = " + createTime +
            ", updateId = " + updateId +
            ", updateTime = " + updateTime +
            ", upperThreshold = " + upperThreshold +
            ", lowerThreshold = " + lowerThreshold +
        "}";
    }
}
