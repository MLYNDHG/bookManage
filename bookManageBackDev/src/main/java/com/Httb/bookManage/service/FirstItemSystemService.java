package com.Httb.bookManage.service;

import com.Httb.bookManage.dao.FirstitemsystemExtDao;
import com.Httb.bookManage.mbg.dao.FirstitemsystemDao;
import com.Httb.bookManage.mbg.entity.Firstitemsystem;
import com.Httb.bookManage.mbg.entity.User;
import com.Httb.bookManage.util.ExcelUtils;
import com.Httb.bookManage.util.RequestPageData;
import com.Httb.bookManage.util.ResponsePageData;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class FirstItemSystemService  {
    @Resource
    private FirstitemsystemExtDao firstitemsystemExtDao;

    public ResponsePageData<Firstitemsystem>  selectSystemList(RequestPageData<Firstitemsystem> requestPageData) {
        // 分页查询
        Page<Object> page = PageHelper.startPage(requestPageData.getPageCondition().getPageNo(),requestPageData.getPageCondition().getPageSize());
        firstitemsystemExtDao.selectSystemList(requestPageData.getCondition());
        return new ResponsePageData<>(page);
    }
    public int insert(Firstitemsystem record) {

        return firstitemsystemExtDao.insertSelective(record);

    }
    public String insertSystemOfExcel(MultipartFile file) throws Exception{
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
            Firstitemsystem firstitemsystem=new Firstitemsystem();
            firstitemsystem.setSystemcode((String) ((List<Object>)list.get(i)).get(1));
            firstitemsystem.setSystemname( (String) ((List<Object>)list.get(i)).get(0));
            firstitemsystemExtDao.insert(firstitemsystem);
        }
        return "导入成功！";
    }
}
