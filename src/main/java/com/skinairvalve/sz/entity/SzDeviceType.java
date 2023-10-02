package com.skinairvalve.sz.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 
 * </p>
 *
 * @author zw
 * @since 2023-10-02
 */
@TableName("sz_device_type")
@ApiModel(value = "SzDeviceType对象", description = "")
public class SzDeviceType implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("设备类型code")
    private String deviceType;

    @ApiModelProperty("设备类型name")
    private String deviceTypeName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getDeviceTypeName() {
        return deviceTypeName;
    }

    public void setDeviceTypeName(String deviceTypeName) {
        this.deviceTypeName = deviceTypeName;
    }

    @Override
    public String toString() {
        return "SzDeviceType{" +
            "id = " + id +
            ", deviceType = " + deviceType +
            ", deviceTypeName = " + deviceTypeName +
        "}";
    }
}
