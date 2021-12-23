package com.Httb.bookManage.service;

import com.Httb.bookManage.dao.FirstitemsysteminfoExtDao;
import com.Httb.bookManage.mbg.entity.Firstitemsysteminfo;
import com.Httb.bookManage.util.RequestPageData;
import com.Httb.bookManage.util.ResponsePageData;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class FirstItemSystemInfoService {
    @Resource
    private FirstitemsysteminfoExtDao firstitemsysteminfoExtDao;

    public ResponsePageData<Firstitemsysteminfo> selectSystemInfoList(RequestPageData<Firstitemsysteminfo> requestPageData) {
        // 分页查询
        Page<Object> page = PageHelper.startPage(requestPageData.getPageCondition().getPageNo(), requestPageData.getPageCondition().getPageSize());
        firstitemsysteminfoExtDao.selectSystemInfoList(requestPageData.getCondition());
        return new ResponsePageData<>(page);
    }

    public int insert(Firstitemsysteminfo record) {
        return firstitemsysteminfoExtDao.insertSelective(record);
    }

    public int deleteByPrimaryKey(Integer wid) {
        return firstitemsysteminfoExtDao.deleteByPrimaryKey(wid);
    }
    //导入多个
//    public int insertByGroup(Integer wid) {
//        return firstitemsysteminfoExtDao.deleteByPrimaryKey(wid);
//    }
    //删除多个
//    public int deleteByGroup(Integer wid) {
//        return firstitemsysteminfoExtDao.deleteByPrimaryKey(wid);
//    }
}
