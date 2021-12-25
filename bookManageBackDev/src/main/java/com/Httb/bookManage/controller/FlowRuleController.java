package com.Httb.bookManage.controller;

import com.Httb.bookManage.mbg.entity.Flowrule;
import com.Httb.bookManage.model.FlowRuleVO;
import com.Httb.bookManage.service.FlowRuleService;
import com.Httb.bookManage.util.RequestPageData;
import com.Httb.bookManage.util.ResponsePageData;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class FlowRuleController {

    @Resource
    private FlowRuleService flowRuleService;

    /**
     * 流控列表
     */
    @PostMapping("/selectFlowRuleList")
    public ResponsePageData<FlowRuleVO> selectFlowRuleList(@RequestBody RequestPageData<Flowrule> requestPageData) {

        return flowRuleService.selectFlowRuleList(requestPageData);
    }

    /**
     * save 流控
     */
    @PostMapping("/saveFlowRule")
    public Integer saveFlowRule(@RequestBody FlowRuleVO flowRuleVO) {

        return flowRuleService.saveFlowRule(flowRuleVO);
    }

    /**
     * 删除 流控
     */
    @GetMapping("/deleteFlowRule")
    public Integer deleteFlowRule(Integer id) {

        return flowRuleService.deleteFlowRule(id);
    }
}
