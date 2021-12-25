package com.Httb.bookManage.dao;

import com.Httb.bookManage.mbg.dao.SourcelistDao;
import com.Httb.bookManage.mbg.entity.Sourcelist;

import java.util.List;

public interface SourceListExtDao extends SourcelistDao {

    /**
     * 删除源列表
     */
    Integer deleteSourceList();

    /**
     * 根据关联表sid查询源
     */
    Sourcelist selectSourceById(Integer sid);

    /**
     * 源列表
     */
    List<Sourcelist> selectSourceList();

    /**
     * 插入源列表
     */
    Integer insertSourcelist(Sourcelist sourcelists);
}
