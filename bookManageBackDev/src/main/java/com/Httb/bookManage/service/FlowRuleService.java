package com.Httb.bookManage.service;

import com.Httb.bookManage.dao.FlowRuleExtDao;
import com.Httb.bookManage.dao.FlowSourceExtDao;
import com.Httb.bookManage.dao.SourceListExtDao;
import com.Httb.bookManage.mbg.entity.FlowSource;
import com.Httb.bookManage.mbg.entity.Flowrule;
import com.Httb.bookManage.mbg.entity.Sourcelist;
import com.Httb.bookManage.model.FlowRuleVO;
import com.Httb.bookManage.util.RequestPageData;
import com.Httb.bookManage.util.ResponsePageData;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class FlowRuleService {

    @Resource
    private FlowRuleExtDao flowRuleExtDao;

    @Resource
    private FlowSourceExtDao flowSourceExtDao;

    @Resource
    private SourceListExtDao sourceListExtDao;

    /**
     * 流控列表
     */
    public ResponsePageData<FlowRuleVO> selectFlowRuleList(RequestPageData<Flowrule> requestPageData) {
        // 分页查询
        Page<Object> page = PageHelper.startPage(requestPageData.getPageCondition().getPageNo(),requestPageData.getPageCondition().getPageSize());
        List<FlowRuleVO> flowRuleVOS = flowRuleExtDao.selectFlowRuleList(requestPageData.getCondition());

        for (FlowRuleVO flowRuleVO:
             flowRuleVOS) {
            // 查询源id
            List<FlowSource> flowSources = flowSourceExtDao.selectFlowSourceByFlowId(flowRuleVO.getId());
            // 查询源信息
            List<Sourcelist> sourcelists = new ArrayList<>();
            List<Integer> sids = new ArrayList<>();
            for (FlowSource flowSource:
                flowSources) {
                Sourcelist sourcelist = sourceListExtDao.selectSourceById(flowSource.getSid());
                sids.add(sourcelist.getId());
                sourcelists.add(sourcelist);
            }
            flowRuleVO.setSids(sids);
            flowRuleVO.setFlowSources(sourcelists);

            // 不包含的源列表
            List<Sourcelist> sourcelists1 = sourceListExtDao.selectSourceList();
            List<Sourcelist> sourcelists2 = new ArrayList<>();
            for (Sourcelist s:
                 sourcelists1) {
                boolean tag = false;
                for (Sourcelist si:
                     sourcelists) {
                    if (s.getId().equals(si.getId())) {
                        tag = true;
                        break;
                    }
                }
                if (!tag) {
                    sourcelists2.add(s);
                }
            }
            flowRuleVO.setFlowNotSources(sourcelists2);
        }

        return new ResponsePageData<>(page);
    }

    /**
     * save 流控
     */
    public Integer saveFlowRule(FlowRuleVO flowRuleVO) {
        if (flowRuleVO.getId() != null) {
            // 修改 流控信息
            // 修改 源信息
            List<Integer> sids = flowRuleVO.getSids();
            List<FlowSource> flowSources = flowSourceExtDao.selectFlowSourceByFlowId(flowRuleVO.getId());
            for (FlowSource flowSource: flowSources) {
                boolean tag = false;
                for (Integer s: sids) {
                    if (s.equals(flowSource.getSid())) {
                        tag = true;
                        break;
                    }
                }
                if (!tag) {
                    // 如果 新的sid 中没有，则删除配置
                    flowSource.setStatus(true);
                    flowSourceExtDao.updateFlowSource(flowSource);
                }
            }
            for (Integer s: sids) {
                boolean tag = false;
                for (FlowSource flowSource: flowSources) {
                    if (flowSource.getSid().equals(s)) {
                        tag = true;
                        break;
                    }
                }
                if (!tag) {
                    // 如果 旧的sid 中没有，则新增配置
                    FlowSource flowSource = new FlowSource();
                    flowSource.setFid(flowRuleVO.getId());
                    flowSource.setSid(s);
                    flowSource.setTime(new Date());
                    flowSourceExtDao.insertFlowSource(flowSource);
                }
            }
            // 修改 基本信息
            return flowRuleExtDao.updateFlowRule(flowRuleVO);

        } else {
            // 新增 流控
            // 新增 流控基本信息
            flowRuleVO.setTime(new Date());
            flowRuleExtDao.insertFloeRule(flowRuleVO);
            FlowRuleVO flowRuleVO1 = flowRuleExtDao.selectNewFlowRule();
            // 新增 源信息
            List<Integer> sids = flowRuleVO.getSids();
            for (Integer sid:
                 sids) {
                FlowSource flowSource = new FlowSource();
                flowSource.setFid(flowRuleVO1.getId());
                flowSource.setSid(sid);
                flowSource.setTime(new Date());
                flowSourceExtDao.insertFlowSource(flowSource);
            }
        }
        return 1;
    }


    /**
     * 删除 流控
     */
    public Integer deleteFlowRule(Integer id) {
        // 删除 流控 源信息
        FlowSource flowSource = new FlowSource();
        flowSource.setFid(id);
        flowSource.setStatus(true);
        flowSourceExtDao.updateFlowSource(flowSource);
        // 删除 流控 基本信息
        FlowRuleVO flowRuleVO = new FlowRuleVO();
        flowRuleVO.setId(id);
        flowRuleVO.setStatus(true);
        return flowRuleExtDao.updateFlowRule(flowRuleVO);
    }


}
