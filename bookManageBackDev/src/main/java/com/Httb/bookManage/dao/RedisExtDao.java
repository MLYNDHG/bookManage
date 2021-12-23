package com.Httb.bookManage.dao;

import com.Httb.bookManage.mbg.dao.RedisDao;
import com.Httb.bookManage.mbg.entity.Redis;

public interface RedisExtDao extends RedisDao {

    /**
     * 新增redis配置
     */
    Integer insertRedis(Redis redis);

    /**
     * 修改redis配置
     */
    Integer updateRedis(Redis redis);

    /**
     * 根据 资源id 删除redis配置
     */
    Integer deleteRedis(Integer rid);

    /**
     * 根据 资源id 查询redis配置
     */
    Redis selectRedisByResourcesId(Integer rid);
}
