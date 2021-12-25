package com.Httb.bookManage.mbg.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * flowrule
 * @author 
 */
@ApiModel(value="com.Httb.bookManage.mbg.entity.Flowrule")
@Data
public class Flowrule implements Serializable {
    /**
     * 流控id
     */
    @ApiModelProperty(value="流控id")
    private Integer id;

    /**
     * 规则名称
     */
    @ApiModelProperty(value="规则名称")
    private String name;

    /**
     * 流控模式
     */
    @ApiModelProperty(value="流控模式")
    private String flowpattern;

    /**
     * 阈值类型
     */
    @ApiModelProperty(value="阈值类型")
    private String thresholdtype;

    /**
     * 阈值
     */
    @ApiModelProperty(value="阈值")
    private Integer threshold;

    /**
     * 阈值模式
     */
    @ApiModelProperty(value="阈值模式")
    private String thresholdpattern;

    /**
     * 流控效果
     */
    @ApiModelProperty(value="流控效果")
    private String floweffect;

    /**
     * 请求来源
     */
    @ApiModelProperty(value="请求来源")
    private String requestsource;

    /**
     * 流控是否删除
     */
    @ApiModelProperty(value="流控是否删除")
    private Boolean status;

    /**
     * 流控创建时间
     */
    @ApiModelProperty(value="流控创建时间")
    private Date time;

    private static final long serialVersionUID = 1L;
}