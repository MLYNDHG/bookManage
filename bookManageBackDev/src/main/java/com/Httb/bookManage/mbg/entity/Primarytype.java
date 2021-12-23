package com.Httb.bookManage.mbg.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * primarytype
 * @author 
 */
@ApiModel(value="com.Httb.bookManage.mbg.entity.Primarytype")
@Data
public class Primarytype implements Serializable {
    /**
     * 一级类型id
     */
    @ApiModelProperty(value="一级类型id")
    private Integer id;

    /**
     * 一级类型名称
     */
    @ApiModelProperty(value="一级类型名称")
    private String name;

    /**
     * 添加时间
     */
    @ApiModelProperty(value="添加时间")
    private Date time;

    /**
     * 一级类型是否删除
     */
    @ApiModelProperty(value="一级类型是否删除")
    private Boolean status;

    private static final long serialVersionUID = 1L;
}