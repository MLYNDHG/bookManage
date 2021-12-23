package com.Httb.bookManage.mbg.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * rocketmq
 * @author 
 */
@ApiModel(value="com.Httb.bookManage.mbg.entity.Rocketmq")
@Data
public class Rocketmq implements Serializable {
    /**
     * RocketMQid
     */
    @ApiModelProperty(value="RocketMQid")
    private Integer id;

    /**
     * 资源id
     */
    @ApiModelProperty(value="资源id")
    private Integer rid;

    /**
     * 服务地址名称
     */
    @ApiModelProperty(value="服务地址名称")
    private String nameserver;

    /**
     * 配置是否删除
     */
    @ApiModelProperty(value="配置是否删除")
    private Boolean status;

    /**
     * 配置添加时间
     */
    @ApiModelProperty(value="配置添加时间")
    private Date time;

    private static final long serialVersionUID = 1L;
}