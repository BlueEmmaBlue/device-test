package com.skinairvalve.sz.dto.user;

import com.skinairvalve.sz.dto.PageParam;
import lombok.Data;

/**
 * @create on 2023/10/2-2:00 PM
 */
@Data
public class SzUserSearchParam extends PageParam {
    private String username;

    private String accountRole;
}
