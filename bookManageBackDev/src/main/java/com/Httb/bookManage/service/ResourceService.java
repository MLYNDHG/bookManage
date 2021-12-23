package com.Httb.bookManage.service;

import com.Httb.bookManage.dao.PrimarytypeExtDao;
import com.Httb.bookManage.dao.ResourcesExtDao;
import com.Httb.bookManage.dao.SecondarytypeExtDao;
import com.Httb.bookManage.mbg.entity.Resources;
import com.Httb.bookManage.mbg.entity.Secondarytype;
import com.Httb.bookManage.model.PrimarytypeVO;
import com.Httb.bookManage.model.ResourcesVO;
import com.Httb.bookManage.model.SecondarytypeVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
        /*// 一级类型列表
        List<PrimarytypeVO> primarytypeVOS = primarytypeExtDao.selectPrimarytypeList();
        // 根据一级类型获得二级类型
        for (PrimarytypeVO p:
                primarytypeVOS) {
            p.setSList(secondarytypeExtDao.selectSecondarytypeList(p.getId()));
        }*/
        List<SecondarytypeVO> secondarytypeVOS = secondarytypeExtDao.selectAlltypeList();
        List<PrimarytypeVO> primarytypeVOS = new ArrayList<>();
        PrimarytypeVO pv = new PrimarytypeVO();
        List<Secondarytype> secondarytypes = new ArrayList<>();
        Secondarytype st = new Secondarytype();
        for (SecondarytypeVO s:
             secondarytypeVOS) {
            pv.setId(s.getPid());
            pv.setName(s.getPname());
            pv.setStatus(s.getPstatus());
            pv.setTime(s.getPtime());
            primarytypeVOS.add(pv);
        }
        for (SecondarytypeVO s:
             secondarytypeVOS) {
            int tag = s.getPid();
            for (PrimarytypeVO p:
                 primarytypeVOS) {
                if (p.getId().equals(tag)) {
                    st.setId(s.getId());
                    st.setName(s.getName());
                    st.setImage(s.getImage());
                    st.setStatus(s.getStatus());
                    st.setTime(s.getTime());
                    secondarytypes.add(st);
                }
            }
            for (PrimarytypeVO p:
                 primarytypeVOS) {
                if (p.getId().equals(tag)) {
                    p.setSList(secondarytypes);
                }
            }
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
