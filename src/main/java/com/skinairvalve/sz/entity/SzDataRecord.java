package com.skinairvalve.sz.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author zw
 * @since 2023-09-18
 */
@TableName("sz_data_record")
@ApiModel(value = "SzDataRecord对象", description = "")
public class SzDataRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer uploadUser;

    private Integer uploadDevice;

    private LocalDateTime uploadTime;

    private Integer createId;

    private LocalDateTime createTime;

    private Integer updateId;

    private LocalDateTime updateTime;

    private String filePath;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUploadUser() {
        return uploadUser;
    }

    public void setUploadUser(Integer uploadUser) {
        this.uploadUser = uploadUser;
    }

    public Integer getUploadDevice() {
        return uploadDevice;
    }

    public void setUploadDevice(Integer uploadDevice) {
        this.uploadDevice = uploadDevice;
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

    @Override
    public String toString() {
        return "SzDataRecord{" +
            "id = " + id +
            ", uploadUser = " + uploadUser +
            ", uploadDevice = " + uploadDevice +
            ", uploadTime = " + uploadTime +
            ", createId = " + createId +
            ", createTime = " + createTime +
            ", updateId = " + updateId +
            ", updateTime = " + updateTime +
            ", filePath = " + filePath +
        "}";
    }
}
