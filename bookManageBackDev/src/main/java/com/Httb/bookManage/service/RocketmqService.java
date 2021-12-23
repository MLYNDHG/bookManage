package com.Httb.bookManage.service;

import com.Httb.bookManage.dao.RocketmqExtDao;
import com.Httb.bookManage.mbg.entity.Rocketmq;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class RocketmqService {

    @Resource
    private RocketmqExtDao rocketmqExtDao;

    /**
     * save rocketmq配置
     */
    public Integer saveRocketmq(Rocketmq rocketmq) {
        if (rocketmq.getId() != null) {
            // 修改 rocketmq 配置
            return rocketmqExtDao.updateRocketmq(rocketmq);
        } else {
            // 新增 rocketmq 配置
            rocketmq.setTime(new Date());
            return rocketmqExtDao.insertRocketmq(rocketmq);
        }
    }

    /**
     * 根据 资源id 查询 rocketmq配置
     */
    public Rocketmq selectRocketmqByResourcesId(Integer rid) {

        return rocketmqExtDao.selectRocketmqByResourcesId(rid);
    }

}
