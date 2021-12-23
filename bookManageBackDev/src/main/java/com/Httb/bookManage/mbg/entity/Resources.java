package com.Httb.bookManage.mbg.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * resources
 * @author 
 */
@ApiModel(value="com.Httb.bookManage.mbg.entity.Resources")
@Data
public class Resources implements Serializable {
    /**
     * 资源id
     */
    @ApiModelProperty(value="资源id")
    private Integer id;

    /**
     * 二级类型id
     */
    @ApiModelProperty(value="二级类型id")
    private Integer sid;

    /**
     * 英文名称
     */
    @ApiModelProperty(value="英文名称")
    private String englishname;

    /**
     * 中文名称
     */
    @ApiModelProperty(value="中文名称")
    private String chinesename;

    /**
     * 描述
     */
    @ApiModelProperty(value="描述")
    private String description;

    /**
     * 添加时间
     */
    @ApiModelProperty(value="添加时间")
    private Date time;

    /**
     * 资源是否删除
     */
    @ApiModelProperty(value="资源是否删除")
    private Boolean status;

    private static final long serialVersionUID = 1L;
}