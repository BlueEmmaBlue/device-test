package com.skinairvalve.sz.dto.device;

import com.skinairvalve.sz.dto.PageParam;
import lombok.Data;

/**
 * @create on 2023/10/2-2:55 PM
 */
@Data
public class SzDeviceSearchParam extends PageParam {
    private String deviceType;

    private String deviceId;
}
