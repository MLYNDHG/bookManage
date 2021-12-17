package com.wtth.bookManage.util;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class PageCondition {
    @ApiModelProperty(value = "页码")
    private int pageNo;
    @ApiModelProperty(value = "查询数量")
    private int pageSize;

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
