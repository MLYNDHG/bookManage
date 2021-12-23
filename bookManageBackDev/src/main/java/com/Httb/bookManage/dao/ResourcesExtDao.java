package com.Httb.bookManage.dao;

import com.Httb.bookManage.mbg.dao.ResourcesDao;
import com.Httb.bookManage.model.ResourcesVO;

import java.util.List;

public interface ResourcesExtDao extends ResourcesDao {

    /**
     * 应用资源基本信息列表
     */
    List<ResourcesVO> selectResourceBasicList(ResourcesVO resourcesVO);


}
