package com.Httb.bookManage.dao;

import com.Httb.bookManage.mbg.dao.PrimarytypeDao;
import com.Httb.bookManage.model.PrimarytypeVO;

import java.util.List;

public interface PrimarytypeExtDao extends PrimarytypeDao {

    /**
     * 一级类型列表
     */
    List<PrimarytypeVO> selectPrimarytypeList();
}
