package com.Httb.bookManage.mbg.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * redis
 * @author 
 */
@ApiModel(value="com.Httb.bookManage.mbg.entity.Redis")
@Data
public class Redis implements Serializable {
    /**
     * redisid
     */
    @ApiModelProperty(value="redisid")
    private Integer id;

    /**
     * 资源id
     */
    @ApiModelProperty(value="资源id")
    private Integer rid;

    /**
     * 集群主机地址
     */
    @ApiModelProperty(value="集群主机地址")
    private String cluteraddress;

    /**
     * 最大跳转次数
     */
    @ApiModelProperty(value="最大跳转次数")
    private Integer maxjumps;

    /**
     * 密码
     */
    @ApiModelProperty(value="密码")
    private String password;

    /**
     * 超时时间
     */
    @ApiModelProperty(value="超时时间")
    private Integer timeout;

    /**
     * 线程最大活动数
     */
    @ApiModelProperty(value="线程最大活动数")
    private Integer maxthreadactivities;

    /**
     * 线程最大空闲数
     */
    @ApiModelProperty(value="线程最大空闲数")
    private Integer maxthreadidles;

    /**
     * 线程最小空闲数
     */
    @ApiModelProperty(value="线程最小空闲数")
    private Integer minthreadidles;

    /**
     * 线程最大等待数
     */
    @ApiModelProperty(value="线程最大等待数")
    private Integer maxthreadwaits;

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