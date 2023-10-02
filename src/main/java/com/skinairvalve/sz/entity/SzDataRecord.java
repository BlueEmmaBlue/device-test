package com.skinairvalve.sz.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author zw
 * @since 2023-10-02
 */
@TableName("sz_data_record")
@ApiModel(value = "SzDataRecord对象", description = "")
public class SzDataRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("上传用户")
    private String uploadUser;

    @ApiModelProperty("上传设备id")
    private String uploadDeviceId;

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUploadUser() {
        return uploadUser;
    }

    public void setUploadUser(String uploadUser) {
        this.uploadUser = uploadUser;
    }

    public String getUploadDeviceId() {
        return uploadDeviceId;
    }

    public void setUploadDeviceId(String uploadDeviceId) {
        this.uploadDeviceId = uploadDeviceId;
    }

    public LocalDateTime getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(LocalDateTime uploadTime) {
        this.uploadTime = uploadTime;
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

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getUploadServerStatus() {
        return uploadServerStatus;
    }

    public void setUploadServerStatus(String uploadServerStatus) {
        this.uploadServerStatus = uploadServerStatus;
    }

    public String getUploadDeviceType() {
        return uploadDeviceType;
    }

    public void setUploadDeviceType(String uploadDeviceType) {
        this.uploadDeviceType = uploadDeviceType;
    }

    public String getUploadSerialId() {
        return uploadSerialId;
    }

    public void setUploadSerialId(String uploadSerialId) {
        this.uploadSerialId = uploadSerialId;
    }

    @Override
    public String toString() {
        return "SzDataRecord{" +
            "id = " + id +
            ", uploadUser = " + uploadUser +
            ", uploadDeviceId = " + uploadDeviceId +
            ", uploadTime = " + uploadTime +
            ", createId = " + createId +
            ", createTime = " + createTime +
            ", updateId = " + updateId +
            ", updateTime = " + updateTime +
            ", filePath = " + filePath +
            ", uploadServerStatus = " + uploadServerStatus +
            ", uploadDeviceType = " + uploadDeviceType +
            ", uploadSerialId = " + uploadSerialId +
        "}";
    }
}
