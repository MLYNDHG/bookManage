package com.Httb.bookManage.service;

import com.Httb.bookManage.dao.RedisExtDao;
import com.Httb.bookManage.mbg.entity.Redis;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class RedisService {

    @Resource
    private RedisExtDao redisExtDao;

    /**
     * save redis配置
     */
    public Integer saveRedis(Redis redis) {
        if (redis.getId() != null) {
            // 修改redis配置
            return redisExtDao.updateRedis(redis);
        } else {
            // 新增redis配置
            redis.setTime(new Date());
            return redisExtDao.insertRedis(redis);
        }
    }

    /**
     * 根据 资源id 查redis配置
     */
    public Redis selectRedisByResourcesId(Integer rid) {

        return redisExtDao.selectRedisByResourcesId(rid);
    }
}
