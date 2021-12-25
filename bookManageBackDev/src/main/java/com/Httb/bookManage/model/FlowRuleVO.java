package com.Httb.bookManage.model;

import com.Httb.bookManage.mbg.entity.Flowrule;
import com.Httb.bookManage.mbg.entity.Sourcelist;
import lombok.Data;

import java.util.List;

@Data
public class FlowRuleVO extends Flowrule {

    /**
     * 流控源id列表
     */
    private List<Integer> sids;

    /**
     * 流控源列表
     */
    private List<Sourcelist> flowSources;

    /**
     * 流控不包含源列表
     */
    private List<Sourcelist> flowNotSources;
}
