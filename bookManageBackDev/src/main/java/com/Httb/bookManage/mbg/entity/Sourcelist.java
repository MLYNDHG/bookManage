package com.Httb.bookManage.mbg.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * sourcelist
 * @author 
 */
@ApiModel(value="com.Httb.bookManage.mbg.entity.Sourcelist")
@Data
public class Sourcelist implements Serializable {
    /**
     * 源id
     */
    @ApiModelProperty(value="源id")
    private Integer id;

    /**
     * 源名称
     */
    @ApiModelProperty(value="源名称")
    private String name;

    /**
     * 源是否删除
     */
    @ApiModelProperty(value="源是否删除")
    private Boolean status;

    /**
     * 源创建时间
     */
    @ApiModelProperty(value="源创建时间")
    private Date time;

    private static final long serialVersionUID = 1L;
}