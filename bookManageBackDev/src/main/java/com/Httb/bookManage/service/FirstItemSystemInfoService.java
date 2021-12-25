package com.Httb.bookManage.service;

import com.Httb.bookManage.dao.FirstitemsysteminfoExtDao;
import com.Httb.bookManage.mbg.entity.Firstitemsysteminfo;
import com.Httb.bookManage.util.ExcelUtils;
import com.Httb.bookManage.util.RequestPageData;
import com.Httb.bookManage.util.ResponsePageData;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;

@Service
public class FirstItemSystemInfoService {
    @Resource
    private FirstitemsysteminfoExtDao firstitemsysteminfoExtDao;

    public ResponsePageData<Firstitemsysteminfo> selectSystemInfoList(RequestPageData<Firstitemsysteminfo> requestPageData) {
        // 分页查询
        Page<Object> page = PageHelper.startPage(requestPageData.getPageCondition().getPageNo(), requestPageData.getPageCondition().getPageSize());
        firstitemsysteminfoExtDao.selectSystemInfoList(requestPageData.getCondition());
        return new ResponsePageData<>(page);
    }

    public int insert(Firstitemsysteminfo record) {
        return firstitemsysteminfoExtDao.insertSelective(record);
    }

    public int deleteByPrimaryKey(Integer wid) {
        return firstitemsysteminfoExtDao.deleteByPrimaryKey(wid);
    }
    //导入多个
    public String insertSystemInfoOfExcel(int mid,MultipartFile file)throws Exception{
            //解析文件  调用接口即可
            String name = file.getOriginalFilename();
            if (name.length() < 6 || !name.substring(name.length() - 5).equals(".xlsx")) {
                return "文件格式错误,请按模板填写";
            }
            List<Object> list = ExcelUtils.excelToList(file.getInputStream());
            if(list.size() <=1){
                return "文件数据为空,请按模板填写";
            }
            for (int i = 1; i < list.size(); i++) {
                Firstitemsysteminfo firstitemsysteminfo=new Firstitemsysteminfo();
                firstitemsysteminfo.setCode( (String) ((List<Object>)list.get(i)).get(0));
                firstitemsysteminfo.setChinese((String) ((List<Object>)list.get(i)).get(1));
                firstitemsysteminfo.setEnglish((String) ((List<Object>)list.get(i)).get(2));
                firstitemsysteminfo.setFanti((String) ((List<Object>)list.get(i)).get(3));
                firstitemsysteminfo.setInfodate((String) ((List<Object>)list.get(i)).get(4));
                firstitemsysteminfo.setMid(mid);
                firstitemsysteminfoExtDao.insert(firstitemsysteminfo);
            }
            return "导入成功！";
        }

    //删除多个
    public int deleteSystemInfoOfList(List widList) {
        for (int i = 0; i < widList.size(); i++) {
            firstitemsysteminfoExtDao.deleteByPrimaryKey((Integer)widList.get(i));
        }
        return 1;
    }
}
