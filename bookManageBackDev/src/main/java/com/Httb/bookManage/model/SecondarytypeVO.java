package com.Httb.bookManage.model;

import com.Httb.bookManage.mbg.entity.Secondarytype;
import lombok.Data;

import java.util.Date;

@Data
public class SecondarytypeVO extends Secondarytype {

    /**
     * 一级类型名称
     */
    private String pname;

    /**
     * 一级类型状态
     */
    private Boolean pstatus;

    /**
     * 一级类型添加时间
     */
    private Date ptime;
}
