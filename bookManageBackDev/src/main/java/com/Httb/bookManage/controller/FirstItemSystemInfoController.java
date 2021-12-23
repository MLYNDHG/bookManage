package com.Httb.bookManage.controller;

import com.Httb.bookManage.mbg.entity.Firstitemsystem;
import com.Httb.bookManage.mbg.entity.Firstitemsysteminfo;
import com.Httb.bookManage.service.FirstItemSystemInfoService;
import com.Httb.bookManage.service.FirstItemSystemService;
import com.Httb.bookManage.util.RequestPageData;
import com.Httb.bookManage.util.ResponsePageData;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
public class FirstItemSystemInfoController {
    @Resource
    private FirstItemSystemInfoService firstItemSystemInfoService;

    @PostMapping("/selectSystemInfoList")
    public ResponsePageData<Firstitemsysteminfo> selectSystemInfoList(@RequestBody RequestPageData<Firstitemsysteminfo> requestPageData) {
        return firstItemSystemInfoService.selectSystemInfoList(requestPageData);
    }
    @PostMapping("/insertSystemInfo")
    public int insertSystemInfo(@RequestBody Firstitemsysteminfo firstitemsysteminfo) {
        return firstItemSystemInfoService.insert(firstitemsysteminfo);
    }
    @GetMapping("/deleteByPrimaryKeySystemInfo")
    public int deleteByPrimaryKeySystemInfo(@RequestParam Integer wid) {
        return firstItemSystemInfoService.deleteByPrimaryKey(wid);
    }
}
