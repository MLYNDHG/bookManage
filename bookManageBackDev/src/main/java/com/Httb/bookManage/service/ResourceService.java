package com.Httb.bookManage.service;

import com.Httb.bookManage.dao.PrimarytypeExtDao;
import com.Httb.bookManage.dao.ResourcesExtDao;
import com.Httb.bookManage.dao.SecondarytypeExtDao;
import com.Httb.bookManage.mbg.entity.Resources;
import com.Httb.bookManage.model.PrimarytypeVO;
import com.Httb.bookManage.model.ResourcesVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ResourceService {

    @Resource
    private ResourcesExtDao resourceExtDao;

    @Resource
    private PrimarytypeExtDao primarytypeExtDao;

    @Resource
    private SecondarytypeExtDao secondarytypeExtDao;

    /**
     * 应用资源基本信息列表
     */
    public List<ResourcesVO> selectResourceBasicList(ResourcesVO resourcesVO) {

        return resourceExtDao.selectResourceBasicList(resourcesVO);
    }

    /**
     * 类型列表
     */
    public List<PrimarytypeVO> selectTypeList() {
        // 一级类型列表
        List<PrimarytypeVO> primarytypeVOS = primarytypeExtDao.selectPrimarytypeList();
        // 根据一级类型获得二级类型
        for (PrimarytypeVO p:
             primarytypeVOS) {
            p.setSList(secondarytypeExtDao.selectSecondarytypeList(p.getId()));
        }

        return primarytypeVOS;
    }

    /**
     * save 资源的基本信息
     */
    public Integer saveResourceBasic(Resources resources) {

        return 1;
    }
}
