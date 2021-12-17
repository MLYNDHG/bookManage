package com.wtth.bookManage.util;

import lombok.Data;

@Data
public class QueryRequest<T> {
    private PageCondition pageCondition;

    private T condition;

    public PageCondition getPageCondition() {
        return pageCondition;
    }

    public void setPageCondition(PageCondition pageCondition) {
        this.pageCondition = pageCondition;
    }

    public T getCondition() {
        return condition;
    }

    public void setCondition(T condition) {
        this.condition = condition;
    }
}
