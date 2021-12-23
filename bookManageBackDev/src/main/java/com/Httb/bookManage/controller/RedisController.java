package com.Httb.bookManage.controller;

import com.Httb.bookManage.mbg.entity.Redis;
import com.Httb.bookManage.service.RedisService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
public class RedisController {

    @Resource
    private RedisService redisService;

    /**
     * save redis配置
     */
    @PostMapping("/saveRedis")
    public Integer saveRedis(@RequestBody Redis redis) {

        return redisService.saveRedis(redis);
    }

    /**
     * 根据 资源id 查redis配置
     */
    @GetMapping("/selectRedisByResourcesId")
    public Redis selectRedisByResourcesId(@RequestParam Integer rid) {

        return redisService.selectRedisByResourcesId(rid);
    }
}
