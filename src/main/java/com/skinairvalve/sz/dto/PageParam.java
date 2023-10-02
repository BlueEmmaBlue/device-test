package com.skinairvalve.sz.dto;

import lombok.Data;

/**
 * @create on 2023/10/2-2:05 PM
 */
@Data
public class PageParam {
    private Integer pageNo = 1;

    private Integer pageSize = 10;

    public int getPageNo(){
        if(pageNo == null || pageNo < 1){
            return 1;
        }else{
            return pageNo;
        }
    }

    public int getPageSize(){
        if(pageSize == null || pageSize < 1){
            return 10;
        }else{
            return pageSize;
        }
    }
}
