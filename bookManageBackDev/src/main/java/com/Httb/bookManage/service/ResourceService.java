package com.Httb.bookManage.service;

import com.Httb.bookManage.dao.*;
import com.Httb.bookManage.mbg.entity.Redis;
import com.Httb.bookManage.mbg.entity.Resources;
import com.Httb.bookManage.mbg.entity.Secondarytype;
import com.Httb.bookManage.model.PrimarytypeVO;
import com.Httb.bookManage.model.ResourcesVO;
import com.Httb.bookManage.model.SecondarytypeVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class ResourceService {

    @Resource
    private ResourcesExtDao resourcesExtDao;

    @Resource
    private PrimarytypeExtDao primarytypeExtDao;

    @Resource
    private SecondarytypeExtDao secondarytypeExtDao;

    @Resource
    private RedisExtDao redisExtDao;

    @Resource
    private RocketmqExtDao rocketmqExtDao;

    /**
     * 应用资源基本信息列表
     */
    public List<ResourcesVO> selectResourceBasicList(ResourcesVO resourcesVO) {

        return resourcesExtDao.selectResourceBasicList(resourcesVO);
    }

    /**
     * 类型列表
     */
    public List<PrimarytypeVO> selectTypeList() {
        // 一级类型列表
        List<PrimarytypeVO> primarytypeVOS = primarytypeExtDao.selectPrimarytypeList();
        // 根据一级类型获得二级类型
//        for (PrimarytypeVO p:
//                primarytypeVOS) {
//            p.setSList(secondarytypeExtDao.selectSecondarytypeList(p.getId()));
//        }
        List<SecondarytypeVO> secondarytypeVOS = secondarytypeExtDao.selectAlltypeList();
        for (PrimarytypeVO p :
                primarytypeVOS) {
            List<Secondarytype> secondarytypes = new ArrayList<>();
            for (SecondarytypeVO s :
                    secondarytypeVOS) {
                if (s.getPid().equals(p.getId())) {
                    Secondarytype st = new Secondarytype();
                    st.setId(s.getId());
                    st.setPid(s.getPid());
                    st.setName(s.getName());
                    st.setImage(s.getImage());
                    st.setStatus(s.getStatus());
                    st.setTime(s.getTime());
                    secondarytypes.add(st);
                }
            }
            p.setSList(secondarytypes);
        }

        return primarytypeVOS;
    }

    /**
     * save 资源的基本信息
     */
    public Integer saveResourceBasic(Resources resources) {

        if (resources.getId() != null) {
            // 修改 资源基本信息
            return resourcesExtDao.updateResourcesBasic(resources);
        } else {
            // 新增 资源基本信息
            resources.setTime(new Date());
            return resourcesExtDao.insertResourcesBasic(resources);
        }
    }

    /**
     * 删除 资源的基本信息和配置信息
     */
    public Integer deleteResources(Integer id) {
        // 查出资源基本信息
        Resources resources = resourcesExtDao.selectByPrimaryKey(id);
        // 查出二级类型
        Secondarytype secondarytype = secondarytypeExtDao.selectByPrimaryKey(resources.getSid());
        // 删除配置信息
        if (secondarytype.getName().equals("redis")) {
            // 如果是redis配置，则删除redis表相关配置
            // 根据 资源id 删除 redis配置
            redisExtDao.deleteRedis(resources.getId());
        } else if (secondarytype.getName().equals("rocketMQ")) {
            // 如果是rocketMQ配置，则删除rocketMQ表相关配置
            // 根据 资源id 删除 rocketMQ配置
            rocketmqExtDao.deleteRocketmq(resources.getId());
        }
        resources.setStatus(true);
        // 删除 基本信息
        return resourcesExtDao.updateResourcesBasic(resources);
    }
}
