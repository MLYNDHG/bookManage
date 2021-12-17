package com.wtth.bookManage.util;

import com.github.pagehelper.Page;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QueryResult<T> {
    @ApiModelProperty(value = "页数")
    private int totalPages;
    @ApiModelProperty(value = "总数")
    private long totalRows;
    private List<T> resultList;

    public QueryResult(Page page) {
        this.totalPages = page.getPages();
        this.totalRows = page.getTotal();
        this.resultList = page.getResult();
    }
}
