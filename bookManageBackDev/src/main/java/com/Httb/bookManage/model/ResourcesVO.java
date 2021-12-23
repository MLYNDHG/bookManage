package com.Httb.bookManage.model;

import com.Httb.bookManage.mbg.entity.Resources;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ResourcesVO extends Resources {

    /**
     * 一级类型id
     */
    private Integer pid;

    /**
     * 二级类型名称
     */
    private String sname;

    /**
     * 二级类型图片
     */
    private String image;

    /**
     * 一级类型名称
     */
    private String pname;
}
