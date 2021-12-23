package com.Httb.bookManage.mbg.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * secondarytype
 * @author 
 */
@ApiModel(value="com.Httb.bookManage.mbg.entity.Secondarytype")
@Data
public class Secondarytype implements Serializable {
    /**
     * 二级类型id
     */
    @ApiModelProperty(value="二级类型id")
    private Integer id;

    /**
     * 一级类型id
     */
    @ApiModelProperty(value="一级类型id")
    private Integer pid;

    /**
     * 类型名称
     */
    @ApiModelProperty(value="类型名称")
    private String name;

    /**
     * 类型照片
     */
    @ApiModelProperty(value="类型照片")
    private String image;

    /**
     * 添加时间
     */
    @ApiModelProperty(value="添加时间")
    private Date time;

    /**
     * 二级类型是否删除
     */
    @ApiModelProperty(value="二级类型是否删除")
    private Boolean status;

    private static final long serialVersionUID = 1L;
}