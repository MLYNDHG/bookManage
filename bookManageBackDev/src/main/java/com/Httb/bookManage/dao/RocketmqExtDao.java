package com.Httb.bookManage.dao;

import com.Httb.bookManage.mbg.dao.RocketmqDao;
import com.Httb.bookManage.mbg.entity.Rocketmq;

public interface RocketmqExtDao extends RocketmqDao {

    /**
     * 新增 rocketmq配置
     */
    Integer insertRocketmq(Rocketmq rocketmq);

    /**
     * 修改 rocketmq配置
     */
    Integer updateRocketmq(Rocketmq rocketmq);

    /**
     * 根据 资源id 删除 rocketmq配置
     */
    Integer deleteRocketmq(Integer rid);

    /**
     * 根据 资源id 查询 rocketmq配置
     */
    Rocketmq selectRocketmqByResourcesId(Integer rid);

}
