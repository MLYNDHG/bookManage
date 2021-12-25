package com.Httb.bookManage.controller;

import com.Httb.bookManage.mbg.entity.Firstitemsystem;
import com.Httb.bookManage.service.FirstItemSystemService;
import com.Httb.bookManage.util.RequestPageData;
import com.Httb.bookManage.util.ResponsePageData;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

@RestController
public class FirstItemSystemController {
    @Resource
    private FirstItemSystemService firstItemSystemService;

    @PostMapping("/selectSystemList")
    public ResponsePageData<Firstitemsystem> selectSystemList(@RequestBody RequestPageData<Firstitemsystem> requestPageData) {
        return firstItemSystemService.selectSystemList(requestPageData);
    }

    @PostMapping("/insertSystem")
    public int insertSystem(@RequestBody Firstitemsystem firstitemsystem) {
        return firstItemSystemService.insert(firstitemsystem);
    }

    @PostMapping("/insertSystemOfExcel")
    public String insertSystemOfExcel(@RequestBody MultipartFile file) throws Exception {
        return firstItemSystemService.insertSystemOfExcel(file);
    }
}
