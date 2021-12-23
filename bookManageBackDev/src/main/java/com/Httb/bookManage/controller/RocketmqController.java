package com.Httb.bookManage.controller;

import com.Httb.bookManage.mbg.entity.Rocketmq;
import com.Httb.bookManage.service.RocketmqService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
public class RocketmqController {

    @Resource
    private RocketmqService rocketmqService;

    /**
     * save rocketmq 配置
     */
    @PostMapping("/saveRocketmq")
    public Integer saveRocketmq(@RequestBody Rocketmq rocketmq) {

        return rocketmqService.saveRocketmq(rocketmq);
    }

    /**
     * 根据 资源id 查询 rocketmq配置
     */
    @GetMapping("/selectRocketmqByResourcesId")
    public Rocketmq selectRocketmqByResourcesId(@RequestParam Integer rid) {

        return rocketmqService.selectRocketmqByResourcesId(rid);
    }
}
