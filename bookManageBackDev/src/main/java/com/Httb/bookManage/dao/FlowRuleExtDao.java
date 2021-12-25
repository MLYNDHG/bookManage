package com.Httb.bookManage.dao;

import com.Httb.bookManage.mbg.dao.FlowruleDao;
import com.Httb.bookManage.mbg.entity.Flowrule;
import com.Httb.bookManage.model.FlowRuleVO;

import java.util.List;

public interface FlowRuleExtDao extends FlowruleDao {

    /**
     * 流控列表
     */
    List<FlowRuleVO> selectFlowRuleList(Flowrule flowrule);

    /**
     * 修改流控信息
     */
    Integer updateFlowRule(FlowRuleVO flowRuleVO);

    /**
     * 新增流控信息
     */
    Integer insertFloeRule(FlowRuleVO flowRuleVO);

    /**
     * 查询最新的流控
     */
    FlowRuleVO selectNewFlowRule();

}
