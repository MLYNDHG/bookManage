package com.Httb.bookManage.dao;

import com.Httb.bookManage.mbg.dao.SecondarytypeDao;
import com.Httb.bookManage.mbg.entity.Secondarytype;

import java.util.List;

public interface SecondarytypeExtDao extends SecondarytypeDao {

    /**
     * 二级类型列表
     */
    List<Secondarytype> selectSecondarytypeList(Integer pid);
}
