package com.Httb.bookManage.util;

import com.github.pagehelper.Page;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponsePageData<T> {

    private Integer totalPages;

    private Long totalRows;

    private List<T> resultPages;

    public ResponsePageData(Page page) {
        this.totalPages = page.getPages();
        this.totalRows = page.getTotal();
        this.resultPages = page.getResult();
    }
}
