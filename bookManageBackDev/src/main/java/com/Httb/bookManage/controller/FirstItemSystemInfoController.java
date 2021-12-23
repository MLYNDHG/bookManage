package com.Httb.bookManage.controller;

import com.Httb.bookManage.mbg.entity.Firstitemsysteminfo;
import com.Httb.bookManage.service.FirstItemSystemInfoService;
import com.Httb.bookManage.util.RequestPageData;
import com.Httb.bookManage.util.ResponsePageData;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;

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
    @PostMapping("/insertSystemInfoOfExcel")
    public String insertSystemInfoOfExcel(@RequestParam int mid,@RequestBody MultipartFile file) throws Exception {
        return firstItemSystemInfoService.insertSystemInfoOfExcel(mid,file);
    }

    @GetMapping("/deleteByPrimaryKeySystemInfo")
    public int deleteByPrimaryKeySystemInfo(@RequestParam Integer wid) {
        return firstItemSystemInfoService.deleteByPrimaryKey(wid);
    }

    @PostMapping("/deleteSystemInfoOfList")
    public int deleteSystemInfoOfList(@RequestBody List<Integer> widList) {
        return firstItemSystemInfoService.deleteSystemInfoOfList(widList);
    }
}
