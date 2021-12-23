package com.Httb.bookManage.dao;

import com.Httb.bookManage.mbg.dao.FirstitemsystemDao;
import com.Httb.bookManage.mbg.dao.FirstitemsysteminfoDao;
import com.Httb.bookManage.mbg.entity.Firstitemsysteminfo;

import java.util.List;

public interface FirstitemsysteminfoExtDao extends FirstitemsysteminfoDao {

    /**
     * 系统信息分组列表
     */
    List<Firstitemsysteminfo> selectSystemInfoList(Firstitemsysteminfo firstitemsysteminfo);
    /**
     * 新增分组信息
     */
    List<Firstitemsysteminfo> SystemInfoInsert(Firstitemsysteminfo firstitemsysteminfo);
}
