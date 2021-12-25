package com.Httb.bookManage.dao;

import com.Httb.bookManage.mbg.dao.FlowruleDao;
import com.Httb.bookManage.mbg.entity.FlowSource;

import java.util.List;

public interface FlowSourceExtDao extends FlowruleDao {

    /**
     * 根据流控id 查源列表
     */
    List<FlowSource> selectFlowSourceByFlowId(Integer fid);

    /**
     * 修改 流控 源信息
     */
    Integer updateFlowSource(FlowSource flowSource);

    /**
     * 新增 流控 源信息
     */
    Integer insertFlowSource(FlowSource flowSource);
}
