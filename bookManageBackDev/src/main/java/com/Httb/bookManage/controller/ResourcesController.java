package com.Httb.bookManage.controller;

import com.Httb.bookManage.model.PrimarytypeVO;
import com.Httb.bookManage.model.ResourcesVO;
import com.Httb.bookManage.service.ResourceService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
}
