package com.Httb.bookManage.controller;

import com.Httb.bookManage.mbg.entity.Resources;
import com.Httb.bookManage.model.PrimarytypeVO;
import com.Httb.bookManage.model.ResourcesVO;
import com.Httb.bookManage.service.ResourceService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class ResourcesController {

    @Resource
    private ResourceService resourceService;

    /**
     * 应用资源基本信息列表
     */
    @PostMapping("/selectResourceBasicList")
    public List<ResourcesVO> selectResourceBasicList(@RequestBody ResourcesVO resourcesVO) {

        return resourceService.selectResourceBasicList(resourcesVO);
    }

    /**
     * 类型列表
     */
    @GetMapping("/selectTypeList")
    public List<PrimarytypeVO> selectTypeList() {

        return resourceService.selectTypeList();
    }

    /**
     * save 资源基本信息
     */
    @PostMapping("/saveResourcesBasic")
    public Integer saveResourcesBasic(@RequestBody Resources resources) {

        return resourceService.saveResourceBasic(resources);
    }

    /**
     * 删除 资源的基本信息和配置信息
     */
    @GetMapping("/deleteResources")
    public Integer deleteResources(@RequestParam Integer id) {

        return resourceService.deleteResources(id);
    }
}
