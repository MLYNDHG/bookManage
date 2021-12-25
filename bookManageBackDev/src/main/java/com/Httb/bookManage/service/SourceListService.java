package com.Httb.bookManage.service;

import com.Httb.bookManage.dao.SourceListExtDao;
import com.Httb.bookManage.mbg.entity.Sourcelist;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SourceListService {

    @Resource
    private SourceListExtDao sourceListExtDao;

    /**
     * 源列表
     */
    public List<Sourcelist> selectSourcelist() {

        return sourceListExtDao.selectSourceList();
    }

}
