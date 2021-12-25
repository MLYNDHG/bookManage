package com.Httb.bookManage.controller;

import com.Httb.bookManage.mbg.entity.Sourcelist;
import com.Httb.bookManage.service.SourceListService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class SourceListController {

    @Resource
    private SourceListService sourceListService;

    /**
     * 源列表
     */
    @GetMapping("/selectSourcelist")
    public List<Sourcelist> selectSourcelist() {

        return sourceListService.selectSourcelist();
    }
}
