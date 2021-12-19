package com.Httb.bookManage.util;

import lombok.Data;

@Data
public class RequestPageData<T> {

    private PageCondition pageCondition;

    private T condition;
}
