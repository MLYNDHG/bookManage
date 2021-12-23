package com.Httb.bookManage.dao;

import com.Httb.bookManage.mbg.dao.FirstitemsystemDao;
import com.Httb.bookManage.mbg.entity.Firstitemsystem;

import java.util.List;

public interface FirstitemsystemExtDao extends FirstitemsystemDao {

    /**
     * 系统分组列表
     */
    List< Firstitemsystem> selectSystemList(Firstitemsystem firstitemsystem);
}
